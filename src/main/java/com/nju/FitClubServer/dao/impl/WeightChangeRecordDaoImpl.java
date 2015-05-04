package com.nju.FitClubServer.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nju.FitClubServer.dao.WeightChangeRecordDao;
import com.nju.FitClubServer.model.WeightChangeRecord;

public class WeightChangeRecordDaoImpl implements WeightChangeRecordDao {

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

	public boolean addWeightChangeRecordDao(
			WeightChangeRecord weightChangeRecord) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;
		String query = "insert into weightChangeRecord values(?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, weightChangeRecord.getWeightChangeRecordRecordID());
		stmt.setString(2, weightChangeRecord.getUserID());
		stmt.setString(3, weightChangeRecord.getStartTime());
		stmt.setString(4, weightChangeRecord.getEndTime());
		stmt.setDouble(5, weightChangeRecord.getStartWeight());
		stmt.setDouble(6, weightChangeRecord.getEndWeight());
		stmt.execute();
		return true;
	}

	public ArrayList<WeightChangeRecord> getAllWeightChangeRecord()
			throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return null;
		ArrayList<WeightChangeRecord> weightChangeRecordList = new ArrayList<WeightChangeRecord>();
		String query = "select * from weightChangeRecord";
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			WeightChangeRecord record = new WeightChangeRecord();
			record.setWeightChangeRecordRecordID(set
					.getString("weightChangeRecordID"));
			record.setUserID(set.getString("userID"));
			record.setStartTime(set.getString("startTime"));
			record.setEndTime(set.getString("endTime"));
			record.setStartWeight(set.getDouble("startWeight"));
			record.setEndWeight(set.getDouble("endWeight"));
			weightChangeRecordList.add(record);
		}
		return weightChangeRecordList;
	}

	public WeightChangeRecord getWeightChangeRecordByID(String id)
			throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from weightChangeRecord where weightChangeRecordID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, id);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			WeightChangeRecord record = new WeightChangeRecord();
			record.setWeightChangeRecordRecordID(set
					.getString("weightChangeRecordID"));
			record.setUserID(set.getString("userID"));
			record.setStartTime(set.getString("startTime"));
			record.setEndTime(set.getString("endTime"));
			record.setStartWeight(set.getDouble("startWeight"));
			record.setEndWeight(set.getDouble("endWeight"));
			return record;

		}
		return null;
	}

}
