package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.dao.impl.EatRecordDaoImpl;
import com.nju.FitClubServer.model.EatRecord;

public class EatRecordDaoTest {

	private EatRecordDao dao = new EatRecordDaoImpl();

	public void addEatRecord() throws Exception {
		EatRecord eatRecord = new EatRecord();
		eatRecord.setEatRecordID("4");
		eatRecord.setTime("1");
		eatRecord.setTimeInDay("3");
		eatRecord.setCalorie(0.9);
		eatRecord.setUserID("1");
		dao.addEatRecord(eatRecord);
	}

	public void updateEatRecord() throws Exception {
		EatRecord eatRecord = new EatRecord();
		eatRecord.setEatRecordID("1");
		eatRecord.setTime("1");
		eatRecord.setTimeInDay("1");
		eatRecord.setCalorie(0.99);
		eatRecord.setUserID("1");
		dao.updateEatRecord(eatRecord);
	}

	public void getEatRecordByUserID() throws Exception {
		ArrayList<EatRecord> eatRecordList = null;
		eatRecordList = dao.getEatRecordByUserID("1");
		for (int i = 0; i < eatRecordList.size(); i++) {
			System.out.println(eatRecordList.get(i).getEatRecordID());
			System.out.println(eatRecordList.get(i).getUserID());
			System.out.println(eatRecordList.get(i).getCalorie());
		}
	}

	public void getEatRecordByUserIDAndDay() throws Exception {
		ArrayList<EatRecord> eatRecordList = null;
		eatRecordList = dao.getEatRecordByUserIDAndDay("1", "1");
		for (int i = 0; i < eatRecordList.size(); i++) {
			System.out.println(eatRecordList.get(i).getEatRecordID());
			System.out.println(eatRecordList.get(i).getUserID());
			System.out.println(eatRecordList.get(i).getCalorie());
		}
	}

	public void getEatRecordByUserIDAndDayAndTime() throws Exception {

		EatRecord eatRecordList = dao.getEatRecordByUserIDAndDayAndTime("1",
				"1", "1");
		System.out.println(eatRecordList.getEatRecordID());
		System.out.println(eatRecordList.getUserID());
		System.out.println(eatRecordList.getCalorie());
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		EatRecordDaoTest test = new EatRecordDaoTest();
//		 test.addEatRecord();
		// test.updateEatRecord();
//		test.getEatRecordByUserID();
//		test.getEatRecordByUserIDAndDay();
//		test.getEatRecordByUserIDAndDayAndTime();

	}

}
