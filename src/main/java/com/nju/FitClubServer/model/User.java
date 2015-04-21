package com.nju.FitClubServer.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="User")
public class User {

	private String userID;
	private String userName;
	private String password;
	private boolean loginOrNot;
	
	public User(){
		
	}
	
	public User(String userID,String userName,String password,boolean loginOrNot) {
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.loginOrNot = loginOrNot;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoginOrNot() {
		return loginOrNot;
	}

	public void setLoginOrNot(boolean loginOrNot) {
		this.loginOrNot = loginOrNot;
	}

	
}
