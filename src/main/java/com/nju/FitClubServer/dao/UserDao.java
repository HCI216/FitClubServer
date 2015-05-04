package com.nju.FitClubServer.dao;

import java.sql.SQLException;

import com.nju.FitClubServer.model.User;

public interface UserDao {
	
	public boolean addUser(User userNew) throws SQLException;
	public boolean deleteUser(String userID) throws SQLException;
	public boolean updateUser(User userUpdate) throws SQLException;
	public User searchUserByName(String userName) throws SQLException;
	public User searchUserByID(String userID) throws SQLException;
}
