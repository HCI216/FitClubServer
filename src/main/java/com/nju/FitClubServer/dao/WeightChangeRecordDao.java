package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.model.WeightChangeRecord;

public interface WeightChangeRecordDao {

	public WeightChangeRecord getWeightChangeRecordByID(String id)
			throws Exception;

	public boolean addWeightChangeRecordDao(
			WeightChangeRecord weightChangeRecord) throws Exception;

	public ArrayList<WeightChangeRecord> getAllWeightChangeRecord()
			throws Exception;

}
