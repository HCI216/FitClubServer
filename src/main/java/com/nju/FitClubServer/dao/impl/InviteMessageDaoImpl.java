package com.nju.FitClubServer.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nju.FitClubServer.dao.InviteMessageDao;
import com.nju.FitClubServer.model.InviteMessage;

public class InviteMessageDaoImpl implements InviteMessageDao {

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

	public boolean addInviteMessage(InviteMessage message) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;
		String query = "insert into inviteMessage values(?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, message.getInviteMessageID());
		stmt.setString(2, message.getGetRunMateRecordID());
		stmt.setString(3, message.getUserID());
		stmt.setString(4, message.getContent());
		stmt.setString(5, message.getTelephone());
		stmt.setString(6, message.getState());
		stmt.execute();
		return false;
	}

	public boolean deleteInviteMessage(String inviteMessageID) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;
		String query = "delete from inviteMessage where inviteMessageID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, inviteMessageID);
		stmt.execute();
		return true;
	}

	public InviteMessage getInviteMessage(String inviteMessageID)
			throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return null;
		InviteMessage message = new InviteMessage();
		String query = "select * from inviteMessage where inviteMessageID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, inviteMessageID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			message.setInviteMessageID(set.getString("inviteMessageID"));
			message.setGetRunMateRecordID(set.getString("getRunMateRecordID"));
			message.setState(set.getString("state"));
			message.setTelephone(set.getString("telephone"));
			message.setUserID(set.getString("userID"));
			message.setContent(set.getString("content"));
			return message;
		}
		return null;
	}

	public ArrayList<InviteMessage> getInviteMessageListByGetRunMateRecordID(
			String getRunMateRecordID) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<InviteMessage> messages = new ArrayList<InviteMessage>();
		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from inviteMessage where getRunMateRecordID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, getRunMateRecordID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			InviteMessage message = new InviteMessage();
			message.setInviteMessageID(set.getString("inviteMessageID"));
			message.setGetRunMateRecordID(set.getString("getRunMateRecordID"));
			message.setState(set.getString("state"));
			message.setTelephone(set.getString("telephone"));
			message.setUserID(set.getString("userID"));
			message.setContent(set.getString("content"));
			messages.add(message);
		}
		return messages;
	}

	public ArrayList<InviteMessage> getInviteMessageListByUserID(String userID)
			throws Exception {
		// TODO Auto-generated method stub
		ArrayList<InviteMessage> messages = new ArrayList<InviteMessage>();
		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from inviteMessage where userID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			InviteMessage message = new InviteMessage();
			message.setInviteMessageID(set.getString("inviteMessageID"));
			message.setGetRunMateRecordID(set.getString("getRunMateRecordID"));
			message.setState(set.getString("state"));
			message.setTelephone(set.getString("telephone"));
			message.setUserID(set.getString("userID"));
			message.setContent(set.getString("content"));
			messages.add(message);
		}
		return messages;
	}

	public boolean updateInviteMessage(InviteMessage message) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return false;
		String query = "update inviteMessage set state=? where inviteMessageID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, message.getState());
		stmt.setString(2, message.getInviteMessageID());
		stmt.execute();
		return true;
	}

}
