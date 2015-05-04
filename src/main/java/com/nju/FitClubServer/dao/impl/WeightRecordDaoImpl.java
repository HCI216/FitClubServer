package com.nju.FitClubServer.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nju.FitClubServer.dao.WeightRecordDao;
import com.nju.FitClubServer.model.WeightChangeRecord;
import com.nju.FitClubServer.model.WeightRecord;

public class WeightRecordDaoImpl implements WeightRecordDao {

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

	public boolean addWeightRecordDao(WeightRecord weightRecord)
			throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;
		String query = "insert into weightRecord values(?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, weightRecord.getWeightRecordID());
		stmt.setString(2, weightRecord.getUserID());
		stmt.setString(3, weightRecord.getTime());
		stmt.setDouble(4, weightRecord.getWeight());
		stmt.execute();
		return true;
	}

	public ArrayList<WeightRecord> getWeightRecordByUserID(String userID)
			throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return null;
		ArrayList<WeightRecord> weightRecordList = new ArrayList<WeightRecord>();
		String query = "select * from weightRecord where userID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			WeightRecord record = new WeightRecord();
			record.setWeightRecordID(set.getString("weightRecordID"));
			record.setUserID(set.getString("userID"));
			record.setTime(set.getString("time"));
			record.setWeight(set.getDouble("weight"));
			weightRecordList.add(record);
		}
		return weightRecordList;
	}

	public WeightRecord getWeightRecordByID(String id) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from weightRecord where weightRecordID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, id);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			WeightRecord record = new WeightRecord();
			record.setWeightRecordID(set.getString("weightRecordID"));
			record.setUserID(set.getString("userID"));
			record.setTime(set.getString("time"));
			record.setWeight(set.getDouble("weight"));
			return record;
		}
		return null;
	}

}
