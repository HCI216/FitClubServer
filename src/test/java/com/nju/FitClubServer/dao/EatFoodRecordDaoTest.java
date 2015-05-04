package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.dao.impl.EatFoodRecordDaoImpl;
import com.nju.FitClubServer.model.EatFoodRecord;

public class EatFoodRecordDaoTest {

	private static EatFoodRecordDao eatFoodRecordDao = new EatFoodRecordDaoImpl();

	public void addEatFoodRecord() throws Exception {
		EatFoodRecord eatFoodRecord = new EatFoodRecord();
		eatFoodRecord.setEatAmount(1.01);
		eatFoodRecord.setEatRecordID("2");
		eatFoodRecord.setFoodID("2");
		eatFoodRecordDao.addEatFoodRecord(eatFoodRecord);
	}

	public void deleteEatFoodRecord(String eatFoodRecordID) throws Exception {
		eatFoodRecordDao.deleteEatFoodRecord(eatFoodRecordID);
	}

	public void searchEatFoodRecordsByEatRecordID(String eatRecordID) throws Exception{
		ArrayList<EatFoodRecord> list = eatFoodRecordDao
				.getEatFoodRecordsByEatRecordID(eatRecordID);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getEatRecordID());

			System.out.println(list.get(i).getEatFoodRecordID());

			System.out.println(list.get(i).getFoodID());
			System.out.println(list.get(i).getEatAmount());
		}
	}

	public static void main(String[] args) throws Exception {
		EatFoodRecordDaoTest test = new EatFoodRecordDaoTest();
		// test.addEatFoodRecord();
		// test.deleteEatFoodRecord("1");
//		test.searchEatFoodRecordsByEatRecordID("2");
	}

}
