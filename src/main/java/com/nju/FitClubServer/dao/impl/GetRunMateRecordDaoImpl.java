package com.nju.FitClubServer.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nju.FitClubServer.dao.GetRunMateRecordDao;
import com.nju.FitClubServer.model.GetRunMateRecord;

public class GetRunMateRecordDaoImpl implements GetRunMateRecordDao {

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

	public boolean addGetRunMateRecord(GetRunMateRecord getRunMateRecord)
			throws Exception {
		Connection con = getCon();
		if (con == null)
			return false;
		String query = "insert into getRunMateRecord values(?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, getRunMateRecord.getGetRunMateRecordID());
		stmt.setString(2, getRunMateRecord.getUserID());
		stmt.setString(3, getRunMateRecord.getTime());
		stmt.setString(4, getRunMateRecord.getPlace());
		stmt.setString(5, getRunMateRecord.getContent());
		stmt.setString(6, getRunMateRecord.getState());
		stmt.execute();
		return true;
	}

	public boolean deleteGetMateRecord(String getRunMateRecordID)
			throws Exception {
		Connection con = getCon();
		if (con == null)
			return false;
		String query = "delete from getRunMateRecord where getRunMateRecordID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, getRunMateRecordID);
		stmt.execute();
		return true;
	}

	public ArrayList<GetRunMateRecord> getGetRunMateRecordByUserID(String userID)
			throws Exception {
		// TODO Auto-generated method stub
		ArrayList<GetRunMateRecord> getRunMateRecordList = new ArrayList<GetRunMateRecord>();
		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from getRunMateRecord where userID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			GetRunMateRecord getRunMateRecord = new GetRunMateRecord();
			getRunMateRecord.setGetRunMateRecordID(set
					.getString("getRunMateRecordID"));
			getRunMateRecord.setUserID(set.getString("userID"));
			getRunMateRecord.setTime(set.getString("time"));
			getRunMateRecord.setPlace(set.getString("place"));
			getRunMateRecord.setContent(set.getString("content"));
			getRunMateRecord.setState(set.getString("state"));
			getRunMateRecordList.add(getRunMateRecord);
		}
		return getRunMateRecordList;
	}

	public GetRunMateRecord getGetRunMateRecordByID(String getRunMateRecordID)
			throws Exception {
		// TODO Auto-generated method stub
		GetRunMateRecord getRunMateRecord = new GetRunMateRecord();
		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from getRunMateRecord where userID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, getRunMateRecordID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			getRunMateRecord.setGetRunMateRecordID(set
					.getString("getRunMateRecordID"));
			getRunMateRecord.setUserID(set.getString("userID"));
			getRunMateRecord.setTime(set.getString("time"));
			getRunMateRecord.setPlace(set.getString("place"));
			getRunMateRecord.setContent(set.getString("content"));
			getRunMateRecord.setState(set.getString("state"));
			return getRunMateRecord;
		}
		return null;
	}

	public ArrayList<GetRunMateRecord> getAllGetRunMateRecord()
			throws Exception {
		ArrayList<GetRunMateRecord> getRunMateRecordList = new ArrayList<GetRunMateRecord>();
		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from getRunMateRecord";
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			GetRunMateRecord getRunMateRecord = new GetRunMateRecord();
			getRunMateRecord.setGetRunMateRecordID(set
					.getString("getRunMateRecordID"));
			getRunMateRecord.setUserID(set.getString("userID"));
			getRunMateRecord.setTime(set.getString("time"));
			getRunMateRecord.setPlace(set.getString("place"));
			getRunMateRecord.setContent(set.getString("content"));
			getRunMateRecord.setState(set.getString("state"));
			getRunMateRecordList.add(getRunMateRecord);
		}
		return getRunMateRecordList;
	}
}
