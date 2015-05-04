package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.model.CoordinateRecord;

public interface CoordinateRecordDao {

	public boolean addCoordinateRecord(CoordinateRecord coordinateRecord)
			throws Exception;

	public ArrayList<CoordinateRecord> getCoordinateRecordByRunRecordID(
			String runRecordID) throws Exception;

	public CoordinateRecord getCoordinateByID(String coordinateID)
			throws Exception;
}
