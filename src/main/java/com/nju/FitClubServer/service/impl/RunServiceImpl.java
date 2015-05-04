package com.nju.FitClubServer.service.impl;

import java.util.ArrayList;
import java.util.Calendar;

import com.nju.FitClubServer.dao.CoordinateRecordDao;
import com.nju.FitClubServer.dao.RunRecordDao;
import com.nju.FitClubServer.dao.impl.CoordinateRecordDaoImpl;
import com.nju.FitClubServer.dao.impl.RunRecordDaoImpl;
import com.nju.FitClubServer.model.CoordinateRecord;
import com.nju.FitClubServer.model.EatRecord;
import com.nju.FitClubServer.model.RunRecord;
import com.nju.FitClubServer.service.RunService;

public class RunServiceImpl implements RunService {

	private RunRecordDao runRecordDao = new RunRecordDaoImpl();
	private CoordinateRecordDao coordinateRecordDao = new CoordinateRecordDaoImpl();

	public boolean addRunRecord(RunRecord runRecord) {

		try {
			String runRecordID = getNewID();
			while (runRecordDao.getRunRecordByID(runRecordID) != null) {
				new Thread().sleep(1000);
				runRecordID = getNewID();
			}
			runRecord.setRunRecordID(runRecordID);
			if (!runRecordDao.addRunRecord(runRecord))
				return false;
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
					return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public ArrayList<RunRecord> getRunRecord(String userID, String time) {
		// TODO Auto-generated method stub
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return runRecordList;
	}

	private String getNewID() {
		Calendar cal = Calendar.getInstance();
		String year = cal.get(Calendar.YEAR) + "";
		String month = cal.get(Calendar.MONTH) + "";
		String day = cal.get(Calendar.DAY_OF_MONTH) + "";
		String hour = cal.get(Calendar.HOUR_OF_DAY) + "";
		String minutes = cal.get(Calendar.MINUTE) + "";
		String sec = cal.get(Calendar.SECOND) + "";
		return year + month + day + hour + minutes + sec;
	}

}
