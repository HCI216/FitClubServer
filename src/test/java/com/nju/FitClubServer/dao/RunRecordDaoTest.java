package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.dao.impl.RunRecordDaoImpl;
import com.nju.FitClubServer.model.RunRecord;

public class RunRecordDaoTest {

	private RunRecordDao dao = new RunRecordDaoImpl();
	
	public void addRunRecord() throws Exception{
		RunRecord runRecord = new RunRecord();
		runRecord.setRunRecordID("003");
		runRecord.setRunDistance(100.1);
		runRecord.setCalorie(100.1);
		runRecord.setRunTime(101.11);
		runRecord.setTime("2131");
		runRecord.setUserID("001");
		dao.addRunRecord(runRecord);
	}

	public void getRunRecordsByUserID(String userID) throws Exception{
		ArrayList<RunRecord> runRecordList = dao.getRunRecordsByUserID(userID); 
		for (int i=0;i<runRecordList.size();i++) {
			System.out.println(runRecordList.get(i).getRunTime() + ":" + runRecordList.get(i).getRunDistance());
		}
	}
	
	public static void main(String[] args) throws Exception{
		RunRecordDaoTest test = new RunRecordDaoTest();
//		test.addRunRecord();
		test.getRunRecordsByUserID("001");
		
	}

}
