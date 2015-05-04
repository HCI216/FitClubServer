package com.nju.FitClubServer.service;

import java.util.ArrayList;

import com.nju.FitClubServer.model.EatFoodRecord;
import com.nju.FitClubServer.model.EatRecord;
import com.nju.FitClubServer.service.impl.EatServiceImpl;

public class EatServiceTest {

	private EatServiceImpl service = new EatServiceImpl();

	public void addEatRecord() {
		EatRecord eatRecord = new EatRecord();
		eatRecord.setTime("20150908");
		eatRecord.setTimeInDay("晚餐");
		eatRecord.setUserID("001");
		eatRecord.setCalorie(900);

		EatFoodRecord eatFoodRecord = new EatFoodRecord();
		eatFoodRecord.setEatAmount(2);
		eatFoodRecord.setFoodID("11");
		EatFoodRecord eatFoodRecord1 = new EatFoodRecord();
		eatFoodRecord1.setEatAmount(2);
		eatFoodRecord1.setFoodID("12");
		EatFoodRecord eatFoodRecord2 = new EatFoodRecord();
		eatFoodRecord2.setEatAmount(2);
		eatFoodRecord2.setFoodID("13");

		ArrayList<EatFoodRecord> eatFoodRecordList = new ArrayList<EatFoodRecord>();
		eatFoodRecordList.add(eatFoodRecord);
		eatFoodRecordList.add(eatFoodRecord1);
		eatFoodRecordList.add(eatFoodRecord2);

		eatRecord.setFoodList(eatFoodRecordList);

		service.addEatRecord(eatRecord);
	}

	public void testGetEatRecord() {
		ArrayList<EatRecord> eatRecordsList = service.getEatRecord("001",
				"20150908");
		for (int i = 0; i < eatRecordsList.size(); i++) {
			ArrayList<EatFoodRecord> foodList = eatRecordsList.get(i)
					.getFoodList();
			for (int j = 0; j < foodList.size(); j++) {
				System.out.println(foodList.get(j).getFoodID() + " ; " + foodList.get(j).getEatAmount());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		EatServiceTest test = new EatServiceTest();
//		test.addEatRecord();
		test.testGetEatRecord();
	}

}
