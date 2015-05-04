package com.nju.FitClubServer.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "EatFoodRecord")
public class EatFoodRecord {

	private String eatFoodRecordID;
	private String eatRecordID;
	private String foodID;
	private double eatAmount;

	public String getEatFoodRecordID() {
		return eatFoodRecordID;
	}

	public void setEatFoodRecordID(String eatFoodRecordID) {
		this.eatFoodRecordID = eatFoodRecordID;
	}

	public String getEatRecordID() {
		return eatRecordID;
	}

	public void setEatRecordID(String eatRecordID) {
		this.eatRecordID = eatRecordID;
	}

	public String getFoodID() {
		return foodID;
	}

	public void setFoodID(String foodID) {
		this.foodID = foodID;
	}

	public double getEatAmount() {
		return eatAmount;
	}

	public void setEatAmount(double eatAmount) {
		this.eatAmount = eatAmount;
	}

}
