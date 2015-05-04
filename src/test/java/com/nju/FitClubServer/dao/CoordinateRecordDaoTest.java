package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.dao.impl.CoordinateRecordDaoImpl;
import com.nju.FitClubServer.model.CoordinateRecord;

public class CoordinateRecordDaoTest {

	private CoordinateRecordDao coordinateDaoImpl = new CoordinateRecordDaoImpl();

	public void addCoordinateRecord() throws Exception {
		CoordinateRecord coordinateRecord = new CoordinateRecord();
		coordinateRecord.setCoordinateRecordID("2");
		coordinateRecord.setRunRecordID("2");
		coordinateRecord.setX(1.901);
		coordinateRecord.setY(1.32);
		coordinateDaoImpl.addCoordinateRecord(coordinateRecord);
	}

	public void getCoordinateRecordByRunRecordID() throws Exception {
		ArrayList<CoordinateRecord> coordinateRecordList = coordinateDaoImpl
				.getCoordinateRecordByRunRecordID("2");
		for (int i = 0; i < coordinateRecordList.size(); i++) {
			System.out.println(coordinateRecordList.get(i).getCoordinateRecordID());
			System.out.println(coordinateRecordList.get(i).getRunRecordID());
			System.out.println(coordinateRecordList.get(i).getX() + "," + coordinateRecordList.get(i).getY());
		}
	}

	public static void main(String[] args) throws Exception{
		CoordinateRecordDaoTest test = new CoordinateRecordDaoTest();
//		test.addCoordinateRecord();
//		test.getCoordinateRecordByRunRecordID();
	}

}
