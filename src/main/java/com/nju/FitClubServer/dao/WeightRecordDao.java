package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.model.WeightRecord;

public interface WeightRecordDao {

	public WeightRecord getWeightRecordByID(String id) throws Exception;

	public boolean addWeightRecordDao(WeightRecord weightRecord)
			throws Exception;

	public ArrayList<WeightRecord> getWeightRecordByUserID(String userID)
			throws Exception;

}
