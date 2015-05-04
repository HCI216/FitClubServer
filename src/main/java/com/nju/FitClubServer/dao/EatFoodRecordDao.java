package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.model.EatFoodRecord;

public interface EatFoodRecordDao {

	public boolean addEatFoodRecord(EatFoodRecord eatFoodRecord) throws Exception;
	public boolean deleteEatFoodRecord(String eatFoodRecordID) throws Exception;
	public EatFoodRecord getEatFoodRecordByID(String eatFoodRecordID) throws Exception;
	public ArrayList<EatFoodRecord> getEatFoodRecordsByEatRecordID(String eatRecordID) throws Exception;
}
