package com.nju.FitClubServer.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "WeightChangeRecord")
public class WeightChangeRecord {

	private String weightChangeRecordRecordID;
	private String startTime;
	private String endTime;
	private String userID;
	private double startWeight;
	private double endWeight;

	public String getWeightChangeRecordRecordID() {
		return weightChangeRecordRecordID;
	}

	public void setWeightChangeRecordRecordID(String weightChangeRecordRecordID) {
		this.weightChangeRecordRecordID = weightChangeRecordRecordID;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public double getStartWeight() {
		return startWeight;
	}

	public void setStartWeight(double startWeight) {
		this.startWeight = startWeight;
	}

	public double getEndWeight() {
		return endWeight;
	}

	public void setEndWeight(double endWeight) {
		this.endWeight = endWeight;
	}

	public int duringTime() {
		char[] st = this.startTime.toCharArray();
		char[] et = this.endTime.toCharArray();
		int sYear = Integer.parseInt("" + st[0] + st[1] + st[2] + st[3]);
		int eYear = Integer.parseInt("" + et[0] + st[1] + st[2] + st[3]);
		int sMonth = Integer.parseInt("" + st[4] + st[5]);
		int eMonth = Integer.parseInt("" + et[4] + et[5]);
		int sDay = Integer.parseInt("" + st[6] + st[7]);
		int eDay = Integer.parseInt("" + et[6] + et[7]);
		return (eYear - sYear) * 365 + (eMonth - sMonth) * 30 + (eDay - sDay);
	}

}
