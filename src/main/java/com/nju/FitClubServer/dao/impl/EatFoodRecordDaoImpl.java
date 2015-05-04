package com.nju.FitClubServer.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import com.nju.FitClubServer.dao.EatFoodRecordDao;
import com.nju.FitClubServer.model.EatFoodRecord;

public class EatFoodRecordDaoImpl implements EatFoodRecordDao {

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

	public boolean addEatFoodRecord(EatFoodRecord eatFoodRecord)
			throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;

		String query = "insert into eatFoodRecord values(?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, eatFoodRecord.getEatFoodRecordID());
		stmt.setString(2, eatFoodRecord.getEatRecordID());
		stmt.setString(3, eatFoodRecord.getFoodID());
		stmt.setDouble(4, eatFoodRecord.getEatAmount());
		stmt.execute();

		return true;
	}

	public boolean deleteEatFoodRecord(String eatFoodRecordID) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;
		if (!existRecord(eatFoodRecordID))
			return false;
		String query = "delete from eatFoodRecord where eatFoodRecordID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, eatFoodRecordID);
		stmt.execute();
		return true;
	}

	private boolean existRecord(String eatFoodRecordID) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;
		String query = "select * from eatFoodRecord where eatFoodRecordID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, eatFoodRecordID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			return true;
		}
		return false;
	}

	public ArrayList<EatFoodRecord> getEatFoodRecordsByEatRecordID(
			String eatRecordID) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<EatFoodRecord> eatFoodRecordList = new ArrayList<EatFoodRecord>();
		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from eatFoodRecord where eatRecordID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, eatRecordID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			EatFoodRecord eatFoodRecord = new EatFoodRecord();
			eatFoodRecord.setEatFoodRecordID(set.getString("eatFoodRecordID"));
			eatFoodRecord.setEatRecordID(set.getString("eatRecordID"));
			eatFoodRecord.setFoodID(set.getString("foodID"));
			eatFoodRecord.setEatAmount(set.getDouble("amount"));
			eatFoodRecordList.add(eatFoodRecord);
		}
		return eatFoodRecordList;
	}

	public EatFoodRecord getEatFoodRecordByID(String eatFoodRecordID)
			throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		EatFoodRecord eatFoodRecord = new EatFoodRecord();
		if (con == null)
			return null;
		String query = "select * from eatFoodRecord where eatFoodRecordID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, eatFoodRecordID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			eatFoodRecord.setEatFoodRecordID(set.getString("eatFoodRecordID"));
			eatFoodRecord.setEatRecordID(set.getString("eatRecordID"));
			eatFoodRecord.setFoodID(set.getString("foodID"));
			eatFoodRecord.setEatAmount(set.getDouble("amount"));
			return eatFoodRecord;
		}
		return null;
	}

}
