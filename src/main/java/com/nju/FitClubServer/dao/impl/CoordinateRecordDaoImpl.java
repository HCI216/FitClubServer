package com.nju.FitClubServer.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nju.FitClubServer.dao.CoordinateRecordDao;
import com.nju.FitClubServer.model.CoordinateRecord;

public class CoordinateRecordDaoImpl implements CoordinateRecordDao {

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

	public boolean addCoordinateRecord(CoordinateRecord coordinateRecord)
			throws Exception {
		// TODO Auto-generated method stub

		Connection con = getCon();
		if (con == null)
			return false;
		String query = "insert into coordinateRecord values(?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, coordinateRecord.getCoordinateRecordID());
		stmt.setString(2, coordinateRecord.getRunRecordID());
		stmt.setDouble(3, coordinateRecord.getX());
		stmt.setDouble(4, coordinateRecord.getY());
		stmt.execute();
		return true;
	}

	public ArrayList<CoordinateRecord> getCoordinateRecordByRunRecordID(
			String runRecordID) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<CoordinateRecord> coordinateRecordList = new ArrayList<CoordinateRecord>();
		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from coordinateRecord where runRecordID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, runRecordID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			CoordinateRecord coordinateRecord = new CoordinateRecord();
			coordinateRecord.setCoordinateRecordID(set
					.getString("coordinateRecordID"));
			coordinateRecord.setRunRecordID(set.getString("runRecordID"));
			coordinateRecord.setX(set.getDouble("x"));
			coordinateRecord.setY(set.getDouble("y"));
			coordinateRecordList.add(coordinateRecord);
		}
		return coordinateRecordList;
	}

	public CoordinateRecord getCoordinateByID(String coordinateID)
			throws Exception {
		// TODO Auto-generated method stub
		CoordinateRecord coordinateRecord = new CoordinateRecord();

		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from coordinateRecord where coordinateRecordID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, coordinateID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			coordinateRecord.setCoordinateRecordID(set
					.getString("coordinateRecordID"));
			coordinateRecord.setRunRecordID(set.getString("runRecordID"));
			coordinateRecord.setX(set.getDouble("x"));
			coordinateRecord.setY(set.getDouble("y"));
			return coordinateRecord;
		}
		return null;
	}

}
