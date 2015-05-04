package com.nju.FitClubServer.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nju.FitClubServer.dao.UserDao;
import com.nju.FitClubServer.model.User;

public class UserDaoImpl implements UserDao {

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

	/*
	 * Add User to Sql
	 * 
	 * @see
	 * com.nju.FitClubServer.dao.UserDao#addUser(com.nju.FitClubServer.model
	 * .User)
	 */

	public boolean addUser(User userNew) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;
		if (searchUserByName(userNew.getUserName()) != null)
			return false;

		String query = "insert into user values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userNew.getUserID());
		stmt.setString(2, userNew.getUserName());
		stmt.setString(3, userNew.getPassword());
		stmt.setString(4, userNew.getEmail());
		stmt.setString(5, userNew.getSex());
		stmt.setString(6, userNew.getBirthDay());
		stmt.setInt(7, userNew.getHeight());
		stmt.setInt(8, userNew.getSportDayAWeek());
		stmt.setDouble(9, userNew.getLoseWeight());
		stmt.setInt(10, userNew.getLoseWeightTime());
		stmt.setBoolean(11, userNew.isLoginOrNot());
		stmt.execute();

		return true;
	}

	public boolean deleteUser(String userID) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;
		if (searchUserByID(userID) == null)
			return false;
		String query = "delete from user where userID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userID);
		stmt.execute();
		return true;
	}

	public boolean updateUser(User userUpdate) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;
		if (searchUserByName(userUpdate.getUserName()) == null)
			return false;
		String query = "update user set password=?,email=?,sex=?,birthDay=?,height=?,sportDayAWeek=?,loseWeight=?,loseWeightTime=?,loginOrNot=? where userName=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userUpdate.getPassword());
		stmt.setString(2, userUpdate.getEmail());
		stmt.setString(3, userUpdate.getSex());
		stmt.setString(4, userUpdate.getBirthDay());
		stmt.setInt(5, userUpdate.getHeight());
		stmt.setInt(6, userUpdate.getSportDayAWeek());
		stmt.setDouble(7, userUpdate.getLoseWeight());
		stmt.setInt(8, userUpdate.getLoseWeightTime());
		stmt.setBoolean(9, userUpdate.isLoginOrNot());
		stmt.setString(10, userUpdate.getUserName());
		stmt.execute();
		return true;
	}

	public User searchUserByName(String userName) throws SQLException {
		// TODO Auto-generated method stub
		User user = null;
		Connection con = getCon();
		String query = "select * from user where userName=?";

		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userName);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			user = new User();
			user.setUserID(rs.getString("userID"));
			user.setUserName(rs.getString("userName"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setSex(rs.getString("sex"));
			user.setBirthDay(rs.getString("birthDay"));
			user.setHeight(rs.getInt("height"));
			user.setSportDayAWeek(rs.getInt("sportDayAWeek"));
			user.setLoseWeight(rs.getDouble("loseWeight"));
			user.setLoseWeightTime(rs.getInt("loseWeightTime"));
			user.setLoginOrNot(rs.getBoolean("loginOrNot"));
		}
		return user;
	}


	public User searchUserByID(String userID) throws SQLException {
		// TODO Auto-generated method stub
		User user = null;
		Connection con = getCon();
		if(con == null) return null;
		String query = "select * from user where userID=?";

		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userID);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			user = new User();
			user.setUserID(rs.getString("userID"));
			user.setUserName(rs.getString("userName"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setSex(rs.getString("sex"));
			user.setBirthDay(rs.getString("birthDay"));
			user.setHeight(rs.getInt("height"));
			user.setSportDayAWeek(rs.getInt("sportDayAWeek"));
			user.setLoseWeight(rs.getDouble("loseWeight"));
			user.setLoseWeightTime(rs.getInt("loseWeightTime"));
			user.setLoginOrNot(rs.getBoolean("loginOrNot"));
		}
		return user;
	}
}
