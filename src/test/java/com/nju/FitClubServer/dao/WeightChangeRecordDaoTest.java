package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.dao.impl.WeightChangeRecordDaoImpl;
import com.nju.FitClubServer.model.WeightChangeRecord;

public class WeightChangeRecordDaoTest {

	private WeightChangeRecordDao dao = new WeightChangeRecordDaoImpl();

	public void addWeightChangeRecordDao() throws Exception {
		WeightChangeRecord weightChangeRecord = new WeightChangeRecord();
		weightChangeRecord.setWeightChangeRecordRecordID("002");
		weightChangeRecord.setStartTime("002");
		weightChangeRecord.setEndTime("003");
		weightChangeRecord.setStartWeight(90.1);
		weightChangeRecord.setEndWeight(80.2);
		weightChangeRecord.setUserID("001");
//		dao.addWeightChangeRecordDao(weightChangeRecord);
	}

	public void getAllWeightChangeRecord() throws Exception {
		ArrayList<WeightChangeRecord> weightChangeRecordList = dao
				.getAllWeightChangeRecord();
		for (int i = 0; i < weightChangeRecordList.size(); i++) {
			System.out.println(weightChangeRecordList.get(i).getStartWeight()
					+ weightChangeRecordList.get(i).getEndWeight());
		}
	}

	public static void main(String[] args) throws Exception {
		WeightChangeRecordDaoTest test = new WeightChangeRecordDaoTest();
//		test.addWeightChangeRecordDao();
//		test.getAllWeightChangeRecord();
	}

}
