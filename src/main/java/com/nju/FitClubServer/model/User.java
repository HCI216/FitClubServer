package com.nju.FitClubServer.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "User")
public class User {

	private String userID;
	private String userName;
	private String password;
	private String email;
	private String sex;
	private String birthDay;
	private int height;
	private ArrayList<WeightRecord> weightList;
	private int sportDayAWeek;
	private double loseWeight;
	private int loseWeightTime;
	private boolean loginOrNot;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSportDayAWeek() {
		return sportDayAWeek;
	}

	public void setSportDayAWeek(int sportDayAWeek) {
		this.sportDayAWeek = sportDayAWeek;
	}

	public double getLoseWeight() {
		return loseWeight;
	}

	public void setLoseWeight(double loseWeight) {
		this.loseWeight = loseWeight;
	}

	public int getLoseWeightTime() {
		return loseWeightTime;
	}

	public void setLoseWeightTime(int loseWeightTime) {
		this.loseWeightTime = loseWeightTime;
	}

	public ArrayList<WeightRecord> getWeightList() {
		return weightList;
	}

	public void setWeightList(ArrayList<WeightRecord> weightList) {
		this.weightList = weightList;
	}
}
