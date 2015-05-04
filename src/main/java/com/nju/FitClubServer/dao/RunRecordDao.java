package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.model.RunRecord;

public interface RunRecordDao {

	public boolean addRunRecord(RunRecord runRecord) throws Exception;

	public ArrayList<RunRecord> getRunRecordsByUserID(String userID)
			throws Exception;

	public ArrayList<RunRecord> getRunRecordsByUserIDAndTime(String userID,
			String time) throws Exception;

	public RunRecord getRunRecordByID(String runRecordID) throws Exception;
}
