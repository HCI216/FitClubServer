package com.nju.FitClubServer.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "InviteMessageList")
public class InviteMessageList {
	private ArrayList<InviteMessage> inviteMessageList;

	public ArrayList<InviteMessage> getInviteMessageList() {
		return inviteMessageList;
	}

	public void setInviteMessageList(ArrayList<InviteMessage> inviteMessageList) {
		this.inviteMessageList = inviteMessageList;
	}

}
