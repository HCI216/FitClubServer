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

		String query = "insert into user values(?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userNew.getUserID());
		stmt.setString(2, userNew.getUserName());
		stmt.setString(3, userNew.getPassword());
		stmt.setBoolean(4, userNew.isLoginOrNot());
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
		String query = "update user set password=?,loginOrNot=? where userName=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userUpdate.getPassword());
		stmt.setBoolean(2, userUpdate.isLoginOrNot());
		stmt.setString(3, userUpdate.getUserName());
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
			user.setLoginOrNot(rs.getBoolean("loginOrNot"));
		}
		return user;
	}

	public User searchUserByID(String userID) throws SQLException {
		// TODO Auto-generated method stub
		User user = null;
		Connection con = getCon();
		String query = "select * from user where userID=?";

		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userID);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			user = new User();
			user.setUserID(rs.getString("userID"));
			user.setUserName(rs.getString("userName"));
			user.setPassword(rs.getString("password"));
			user.setLoginOrNot(rs.getBoolean("loginOrNot"));
		}
		return user;
	}
}
