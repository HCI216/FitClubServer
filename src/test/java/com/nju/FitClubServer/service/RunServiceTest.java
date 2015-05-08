package com.nju.FitClubServer.service;

import java.util.ArrayList;

import com.nju.FitClubServer.model.CoordinateRecord;
import com.nju.FitClubServer.model.RunRecord;
import com.nju.FitClubServer.service.impl.RunServiceImpl;

public class RunServiceTest {

	private RunService service = new RunServiceImpl();

	public void addRunRecord() {
		RunRecord runRecord = new RunRecord();
		runRecord.setRunDistance(1000);
		runRecord.setRunTime(120);
		runRecord.setTime("20150908");
		runRecord.setUserID("001");
		runRecord.setCalorie(100);

		ArrayList<CoordinateRecord> coordinateRecordList = new ArrayList<CoordinateRecord>();

		CoordinateRecord record = new CoordinateRecord();
		record.setX(1);
		record.setY(2);

		CoordinateRecord record1 = new CoordinateRecord();
		record1.setX(2);
		record1.setY(3);

		CoordinateRecord record2 = new CoordinateRecord();
		record2.setX(3);
		record2.setY(4);

		coordinateRecordList.add(record2);
		coordinateRecordList.add(record1);
		coordinateRecordList.add(record);

		runRecord.setCoordinateRecordList(coordinateRecordList);

		service.addRunRecord(runRecord);
	}

	public void getRunRecordByUserIDAndTime() throws Exception {
		ArrayList<RunRecord> runRecordList = service.getRunRecord("201504301427",
				"20150504").getRecords();

		for (int i = 0; i < runRecordList.size(); i++) {
			ArrayList<CoordinateRecord> records = runRecordList.get(i)
					.getCoordinateRecordList();

			for (int j = 0; j < records.size(); j++) {
				System.out.println(records.get(j).getX() + " ; " + records.get(j).getY());
			}

		}

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		RunServiceTest test = new RunServiceTest();
//		test.addRunRecord();
		test.getRunRecordByUserIDAndTime();
	}

}
