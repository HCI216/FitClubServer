package com.nju.FitClubServer.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "WeightRecord")
public class WeightRecord {

	private String weightRecordID;
	private String userID;
	private String time;
	private double weight;
	
	public String getWeightRecordID() {
		return weightRecordID;
	}

	public void setWeightRecordID(String weightRecordID) {
		this.weightRecordID = weightRecordID;
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

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
