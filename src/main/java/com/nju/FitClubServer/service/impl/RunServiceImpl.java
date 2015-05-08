package com.nju.FitClubServer.service.impl;

import java.util.ArrayList;
import java.util.Calendar;

import javax.ws.rs.core.Response;

import com.nju.FitClubServer.dao.CoordinateRecordDao;
import com.nju.FitClubServer.dao.RunRecordDao;
import com.nju.FitClubServer.dao.impl.CoordinateRecordDaoImpl;
import com.nju.FitClubServer.dao.impl.RunRecordDaoImpl;
import com.nju.FitClubServer.model.CoordinateRecord;
import com.nju.FitClubServer.model.EatRecord;
import com.nju.FitClubServer.model.RunRecord;
import com.nju.FitClubServer.model.RunRecordList;
import com.nju.FitClubServer.service.RunService;

public class RunServiceImpl implements RunService {

	private RunRecordDao runRecordDao = new RunRecordDaoImpl();
	private CoordinateRecordDao coordinateRecordDao = new CoordinateRecordDaoImpl();

	public Response addRunRecord(RunRecord runRecord) {

		try {
			String runRecordID = getNewID();
			while (runRecordDao.getRunRecordByID(runRecordID) != null) {
				new Thread().sleep(1000);
				runRecordID = getNewID();
			}
			runRecord.setRunRecordID(runRecordID);
			if (!runRecordDao.addRunRecord(runRecord))
				return Response.ok(false).build();
			ArrayList<CoordinateRecord> recordList = runRecord
					.getCoordinateRecordList();
			for (int i = 0; i < recordList.size(); i++) {
				CoordinateRecord record = recordList.get(i);
				String recordID = getNewID();
				while (coordinateRecordDao.getCoordinateByID(recordID) != null) {
					new Thread().sleep(1000);
					recordID = getNewID();
				}
				record.setRunRecordID(runRecordID);
				record.setCoordinateRecordID(recordID);
				if (!coordinateRecordDao.addCoordinateRecord(record))
					return Response.ok(false).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(true).build();
	}

	public RunRecordList getRunRecord(String userID, String time) {
		// TODO Auto-generated method stub

		RunRecordList list = new RunRecordList();


		ArrayList<RunRecord> runRecordList = new ArrayList<RunRecord>();
		try {
			runRecordList = runRecordDao.getRunRecordsByUserIDAndTime(userID,
					time);


			for (int i = 0; i < runRecordList.size(); i++) {
				RunRecord record = runRecordList.get(i);
				record.setCoordinateRecordList(coordinateRecordDao
						.getCoordinateRecordByRunRecordID(record
								.getRunRecordID()));
			}
			list.setRecords(runRecordList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getNewID() {
		Calendar cal = Calendar.getInstance();
		String sYear = cal.get(Calendar.YEAR) + "";
		String sMonth = getNewStr(cal.get(Calendar.MONTH) + 1);
		String sDay = getNewStr(cal.get(Calendar.DAY_OF_MONTH));
		String hour = getNewStr(cal.get(Calendar.HOUR_OF_DAY));
		String minutes = getNewStr(cal.get(Calendar.MINUTE));
		String sec = getNewStr(cal.get(Calendar.SECOND));
		return sYear + sMonth + sDay + hour + minutes + sec;
	}

	public String getNewStr(int i) {
		if (i < 10)
			return "0" + i;
		return "" + i;
	}

}
