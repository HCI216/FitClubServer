package com.nju.FitClubServer.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nju.FitClubServer.dao.RunRecordDao;
import com.nju.FitClubServer.model.Plan;
import com.nju.FitClubServer.model.RunRecord;

public class RunRecordDaoImpl implements RunRecordDao {

	String url = "jdbc:mysql://localhost:3306/fitclub";

	public Connection getCon() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, "root", "nzbbzlks");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public boolean addRunRecord(RunRecord runRecord) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;
		String query = "insert into runRecord values(?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareCall(query);
		stmt.setString(1, runRecord.getRunRecordID());
		stmt.setString(2, runRecord.getUserID());
		stmt.setString(3, runRecord.getTime());
		stmt.setDouble(4, runRecord.getRunDistance());
		stmt.setDouble(5, runRecord.getRunTime());
		stmt.setDouble(6, runRecord.getCalorie());
		stmt.execute();
		return true;
	}

	public ArrayList<RunRecord> getRunRecordsByUserID(String userID)
			throws Exception {
		// TODO Auto-generated method stub
		ArrayList<RunRecord> runRecordList = new ArrayList<RunRecord>();
		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from runRecord where userID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			RunRecord runRecord = new RunRecord();
			runRecord.setRunRecordID(set.getString("runRecordID"));
			runRecord.setUserID(set.getString("userID"));
			runRecord.setTime(set.getString("time"));
			runRecord.setRunDistance(set.getDouble("runDistance"));
			runRecord.setRunTime(set.getDouble("runTime"));
			runRecord.setCalorie(set.getDouble("calorie"));
			runRecordList.add(runRecord);
		}
		return runRecordList;
	}

	public ArrayList<RunRecord> getRunRecordsByUserIDAndTime(String userID,
			String time) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArrayList<RunRecord> runRecordList = new ArrayList<RunRecord>();
		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from runRecord where userID=? and time=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userID);
		stmt.setString(2, time);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			RunRecord runRecord = new RunRecord();
			runRecord.setRunRecordID(set.getString("runRecordID"));
			runRecord.setUserID(set.getString("userID"));
			runRecord.setTime(set.getString("time"));
			runRecord.setRunDistance(set.getDouble("runDistance"));
			runRecord.setRunTime(set.getDouble("runTime"));
			runRecord.setCalorie(set.getDouble("calorie"));
			runRecordList.add(runRecord);
		}
		return runRecordList;
	}

	public RunRecord getRunRecordByID(String runRecordID) throws Exception {
		// TODO Auto-generated method stub
		RunRecord runRecord = new RunRecord();
		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from runRecord where runRecordID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, runRecordID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			runRecord.setRunRecordID(set.getString("runRecordID"));
			runRecord.setUserID(set.getString("userID"));
			runRecord.setTime(set.getString("time"));
			runRecord.setRunDistance(set.getDouble("runDistance"));
			runRecord.setRunTime(set.getDouble("runTime"));
			runRecord.setCalorie(set.getDouble("calorie"));
			return runRecord;
		}
		return null;
	}
}
