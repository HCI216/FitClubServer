package com.nju.FitClubServer.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "GetRunMateRecord")
public class GetRunMateRecord {

	private String getRunMateRecordID;
	private String userID;
	private String time;
	private String place;
	private String content;
	private String state;

	public String getGetRunMateRecordID() {
		return getRunMateRecordID;
	}

	public void setGetRunMateRecordID(String getRunMateRecordID) {
		this.getRunMateRecordID = getRunMateRecordID;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
