package com.nju.FitClubServer.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "EatRecord")
public class EatRecord {

	private String eatRecordID;
	private String userID;
	private String time;
	private String timeInDay; // 早餐，中餐，晚餐，零食
	private ArrayList<EatFoodRecord> foodList;
	private double calorie;

	public String getEatRecordID() {
		return eatRecordID;
	}

	public void setEatRecordID(String eatRecordID) {
		this.eatRecordID = eatRecordID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimeInDay() {
		return timeInDay;
	}

	public void setTimeInDay(String timeInDay) {
		this.timeInDay = timeInDay;
	}

	public ArrayList<EatFoodRecord> getFoodList() {
		return foodList;
	}

	public void setFoodList(ArrayList<EatFoodRecord> foodList) {
		this.foodList = foodList;
	}

	public double getCalorie() {
		return calorie;
	}

	public void setCalorie(double calorie) {
		this.calorie = calorie;
	}

}
