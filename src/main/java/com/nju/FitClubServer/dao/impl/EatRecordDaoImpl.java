package com.nju.FitClubServer.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nju.FitClubServer.dao.EatRecordDao;
import com.nju.FitClubServer.model.EatRecord;
import com.nju.FitClubServer.model.Food;

public class EatRecordDaoImpl implements EatRecordDao {

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

	public boolean addEatRecord(EatRecord eatRecord) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;

		String query = "insert into eatRecord values(?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, eatRecord.getEatRecordID());
		stmt.setString(2, eatRecord.getUserID());
		stmt.setString(3, eatRecord.getTime());
		stmt.setString(4, eatRecord.getTimeInDay());
		stmt.setDouble(5, eatRecord.getCalorie());
		stmt.execute();
		return true;
	}

	public boolean updateEatRecord(EatRecord eatRecord) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;

		String query = "update eatRecord set calorie = ? where eatRecordID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setDouble(1, eatRecord.getCalorie());
		stmt.setString(2, eatRecord.getEatRecordID());
		stmt.execute();

		return true;
	}

	public ArrayList<EatRecord> getEatRecordByUserID(String userID)
			throws Exception {
		ArrayList<EatRecord> eatRecordList = new ArrayList<EatRecord>();

		String query = "select * from eatRecord where userID=?";
		Connection con = getCon();
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			EatRecord eatRecord = new EatRecord();
			eatRecord.setEatRecordID(set.getString("eatRecordID"));
			eatRecord.setUserID(set.getString("userID"));
			eatRecord.setTime(set.getString("time"));
			eatRecord.setTimeInDay(set.getString("timeInDay"));
			eatRecord.setCalorie(set.getDouble("calorie"));
			eatRecordList.add(eatRecord);
		}
		return eatRecordList;
	}

	public ArrayList<EatRecord> getEatRecordByUserIDAndDay(String userID,
			String day) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<EatRecord> eatRecordList = new ArrayList<EatRecord>();

		String query = "select * from eatRecord where userID=? and time=?";
		Connection con = getCon();
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userID);
		stmt.setString(2, day);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			EatRecord eatRecord = new EatRecord();
			eatRecord.setEatRecordID(set.getString("eatRecordID"));
			eatRecord.setUserID(set.getString("userID"));
			eatRecord.setTime(set.getString("time"));
			eatRecord.setTimeInDay(set.getString("timeInDay"));
			eatRecord.setCalorie(set.getDouble("calorie"));
			eatRecordList.add(eatRecord);
		}
		return eatRecordList;
	}

	public EatRecord getEatRecordByUserIDAndDayAndTime(String userID,
			String day, String timeInDay) throws Exception {
		// TODO Auto-generated method stub
		EatRecord eatRecord = new EatRecord();

		String query = "select * from eatRecord where userID=? and time=? and timeInDay=?";
		Connection con = getCon();
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userID);
		stmt.setString(2, day);
		stmt.setString(3, timeInDay);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			eatRecord.setEatRecordID(set.getString("eatRecordID"));
			eatRecord.setUserID(set.getString("userID"));
			eatRecord.setTime(set.getString("time"));
			eatRecord.setTimeInDay(set.getString("timeInDay"));
			eatRecord.setCalorie(set.getDouble("calorie"));
		}
		return eatRecord;
	}

	public EatRecord getEatRecordByID(String eatRecordID) throws Exception {
		// TODO Auto-generated method stub
		EatRecord eatRecord = new EatRecord();

		String query = "select * from eatRecord where eatRecordID=?";
		Connection con = getCon();
		if(con == null) return null;
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, eatRecordID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			eatRecord.setEatRecordID(set.getString("eatRecordID"));
			eatRecord.setUserID(set.getString("userID"));
			eatRecord.setTime(set.getString("time"));
			eatRecord.setTimeInDay(set.getString("timeInDay"));
			eatRecord.setCalorie(set.getDouble("calorie"));
			return eatRecord;
		}
		return null;
	}

}
