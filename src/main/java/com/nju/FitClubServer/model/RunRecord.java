package com.nju.FitClubServer.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "RunRecord")
public class RunRecord {

	private String runRecordID;
	private String userID;
	private String time;
	private double runDistance;
	private double runTime;
	private double calorie;
	private double[][] coordinate;

	public String getRunRecordID() {
		return runRecordID;
	}

	public void setRunRecordID(String runRecordID) {
		this.runRecordID = runRecordID;
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

	public double getRunDistance() {
		return runDistance;
	}

	public void setRunDistance(double runDistance) {
		this.runDistance = runDistance;
	}

	public double getRunTime() {
		return runTime;
	}

	public void setRunTime(double runTime) {
		this.runTime = runTime;
	}

	public double getCalorie() {
		return calorie;
	}

	public void setCalorie(double calorie) {
		this.calorie = calorie;
	}

	public double[][] getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(double[][] coordinate) {
		this.coordinate = coordinate;
	}

}
