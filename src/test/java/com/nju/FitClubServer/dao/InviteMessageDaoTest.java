package com.nju.FitClubServer.dao;

import com.nju.FitClubServer.dao.impl.InviteMessageDaoImpl;
import com.nju.FitClubServer.model.InviteMessage;

public class InviteMessageDaoTest {

	private InviteMessageDao dao = new InviteMessageDaoImpl();
	
	public void addInviteMessage() throws Exception{
		InviteMessage message = new InviteMessage();
		message.setInviteMessageID("001");
		message.setGetRunMateRecordID("001");
		message.setState("state");
		message.setContent("content");
		message.setTelephone("158");
		message.setUserID("910");
		dao.addInviteMessage(message);
	}
	
	public void deleteInviteMessage(String inviteMessageID) throws Exception{
		dao.deleteInviteMessage(inviteMessageID);
	}
	
	public static void main(String[] args) throws Exception{
		InviteMessageDaoTest test = new InviteMessageDaoTest();
//		test.addInviteMessage();
//		test.deleteInviteMessage("001");
	}
	
}
