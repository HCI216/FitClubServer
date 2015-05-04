package com.nju.FitClubServer.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nju.FitClubServer.dao.PlanDao;
import com.nju.FitClubServer.model.Plan;

public class PlanDaoImpl implements PlanDao {

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

	public boolean addPlan(Plan plan) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;
		String query = "insert into plan values(?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, plan.getPlanID());
		stmt.setString(2, plan.getFromUserID());
		stmt.setString(3, plan.getToUserID());
		stmt.setString(4, plan.getStartTime());
		stmt.setString(5, plan.getEndTime());
		stmt.setString(6, plan.getState());
		stmt.execute();
		return true;
	}

	public boolean deletePlan(String planID) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;
		String query = "delete from plan where planID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, planID);
		stmt.execute();
		return true;
	}

	public boolean updatePlan(Plan plan) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;
		String query = "update plan set state=? where planID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, plan.getState());
		stmt.setString(2, plan.getPlanID());
		stmt.execute();
		return true;
	}

	public ArrayList<Plan> getPlanByUserID(String userID) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Plan> planList = new ArrayList<Plan>();
		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from plan where userID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			Plan plan = new Plan();
			plan.setPlanID(set.getString("planID"));
			plan.setFromUserID(set.getString("fromUserID"));
			plan.setToUserID(set.getString("toUserID"));
			plan.setStartTime(set.getString("startTime"));
			plan.setEndTime(set.getString("endTime"));
			plan.setState(set.getString("state"));
			planList.add(plan);
		}
		return planList;
	}

}
