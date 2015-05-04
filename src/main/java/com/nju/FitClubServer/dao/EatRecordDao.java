package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.model.EatRecord;

public interface EatRecordDao {

	public boolean addEatRecord(EatRecord eatRecord) throws Exception;

	public boolean updateEatRecord(EatRecord eatRecord) throws Exception;

	public EatRecord getEatRecordByID(String eatRecordID) throws Exception;

	public ArrayList<EatRecord> getEatRecordByUserID(String userID)
			throws Exception;

	public ArrayList<EatRecord> getEatRecordByUserIDAndDay(String userID,
			String day) throws Exception;

	public EatRecord getEatRecordByUserIDAndDayAndTime(String userID,
			String day, String timeInDay) throws Exception;

}
