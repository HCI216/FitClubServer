package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.dao.impl.WeightRecordDaoImpl;
import com.nju.FitClubServer.model.WeightRecord;

public class WeightRecordDaoTest {

	private WeightRecordDao dao = new WeightRecordDaoImpl();

	public void addWeightRecordDao() throws Exception {
		WeightRecord weightRecord = new WeightRecord();
		weightRecord.setTime("001");
		weightRecord.setUserID("001");
		weightRecord.setWeight(80.1);
		weightRecord.setWeightRecordID("001");
		dao.addWeightRecordDao(weightRecord);
	}

	public void getWeightRecordByUserID(String userID) throws Exception {
		ArrayList<WeightRecord> recordLists = dao
				.getWeightRecordByUserID(userID);
		for (int i = 0; i < recordLists.size(); i++) {
			System.out.println(recordLists.get(i).getWeight());
		}
	}

	public static void main(String[] args) throws Exception {
		WeightRecordDaoTest test = new WeightRecordDaoTest();
		test.addWeightRecordDao();
		test.getWeightRecordByUserID("001");
	}

}
