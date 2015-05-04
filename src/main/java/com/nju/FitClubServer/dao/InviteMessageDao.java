package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.model.InviteMessage;

public interface InviteMessageDao {

	public boolean addInviteMessage(InviteMessage message) throws Exception;

	public boolean deleteInviteMessage(String inviteMessageID) throws Exception;
	
	public boolean updateInviteMessage(InviteMessage message) throws Exception;

	public InviteMessage getInviteMessage(String inviteMessageID)
			throws Exception;

	public ArrayList<InviteMessage> getInviteMessageListByGetRunMateRecordID(
			String getRunMateRecordID) throws Exception;
	
	public ArrayList<InviteMessage> getInviteMessageListByUserID(String userID) throws Exception;
}
