package com.nju.FitClubServer.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "InviteMessage")
public class InviteMessage {

	private String inviteMessageID;
	private String getRunMateRecordID;
	private String userID; // 发出邀请的人
	private String content;
	private String telephone;
	private String state;

	public String getInviteMessageID() {
		return inviteMessageID;
	}

	public void setInviteMessageID(String inviteMessageID) {
		this.inviteMessageID = inviteMessageID;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
