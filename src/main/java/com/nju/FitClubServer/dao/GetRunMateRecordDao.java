package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.model.GetRunMateRecord;

public interface GetRunMateRecordDao {

	public boolean addGetRunMateRecord(GetRunMateRecord getRunMateRecord)
			throws Exception;

	public boolean deleteGetMateRecord(String getRunMateRecordID)
			throws Exception;

	public ArrayList<GetRunMateRecord> getGetRunMateRecordByUserID(String userID)
			throws Exception;

	public GetRunMateRecord getGetRunMateRecordByID(String getRunMateRecordID)
			throws Exception;

	public ArrayList<GetRunMateRecord> getAllGetRunMateRecord()
			throws Exception;

}
