package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.dao.impl.GetRunMateRecordDaoImpl;
import com.nju.FitClubServer.model.GetRunMateRecord;

public class GetRunMateRecordDaoTest {

	private GetRunMateRecordDao getRunMateRecordDao = new GetRunMateRecordDaoImpl();

	public void addGetRunMateRecord() throws Exception {
		GetRunMateRecord getRunMateRecord = new GetRunMateRecord();
		getRunMateRecord.setGetRunMateRecordID("002");
		getRunMateRecord.setUserID("001");
		getRunMateRecord.setPlace("901");
		getRunMateRecord.setTime("1090");
		getRunMateRecord.setState("pp");
		getRunMateRecord.setContent("pppp");
		getRunMateRecordDao.addGetRunMateRecord(getRunMateRecord);
	}

	public void deleteGetMateRecord() throws Exception {
		getRunMateRecordDao.deleteGetMateRecord("001");
	}

	public void getGetRunMateRecordByUserID() throws Exception {
		ArrayList<GetRunMateRecord> getGetRunMateRecordList = getRunMateRecordDao
				.getGetRunMateRecordByUserID("001");
		for (int i = 0; i < getGetRunMateRecordList.size(); i++) {
			GetRunMateRecord iu = getGetRunMateRecordList.get(i);
			System.out.println(iu.getGetRunMateRecordID());
			System.out.println(iu.getPlace() + "," + iu.getTime() + ","
					+ iu.getUserID());
		}
	}

	public void getGetRunMateRecordByID() throws Exception {
		GetRunMateRecord getRunMateRecord = getRunMateRecordDao
				.getGetRunMateRecordByID("001");
		System.out.println(getRunMateRecord.getGetRunMateRecordID());
		System.out.println(getRunMateRecord.getPlace() + ","
				+ getRunMateRecord.getTime() + ","
				+ getRunMateRecord.getUserID());
	}

	public static void main(String[] args) throws Exception {
		GetRunMateRecordDaoTest test = new GetRunMateRecordDaoTest();
		// test.addGetRunMateRecord();
		 test.deleteGetMateRecord();
//		test.getGetRunMateRecordByUserID();
//		 test.getGetRunMateRecordByID();
	}

}
