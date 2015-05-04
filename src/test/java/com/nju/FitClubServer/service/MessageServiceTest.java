package com.nju.FitClubServer.service;

import java.util.ArrayList;

import javax.ws.rs.PathParam;

import com.nju.FitClubServer.model.GetRunMateRecord;
import com.nju.FitClubServer.model.InviteMessage;
import com.nju.FitClubServer.service.impl.MessageServiceImpl;

public class MessageServiceTest {

	private MessageService service = new MessageServiceImpl();

	public void sendGetRunMateMessage() {
		GetRunMateRecord getRunMateRecord = new GetRunMateRecord();
		getRunMateRecord.setContent("hello");
		getRunMateRecord.setPlace("pl");
		getRunMateRecord.setState("test");
		getRunMateRecord.setTime("20150809");
		getRunMateRecord.setUserID("001");
		service.sendGetRunMateMessage(getRunMateRecord);
	}

	public void sendInviteMessage() {

		InviteMessage message = new InviteMessage();
		message.setContent("hello");
		message.setState("test");
		message.setGetRunMateRecordID("001");
		message.setTelephone("15805197466");
		message.setUserID("001");

		service.sendInviteMessage(message);
	}

	public void getGetRunMateRecord() {
		ArrayList<GetRunMateRecord> records = service.getGetRunMateRecord();

		for (int i = 0; i < records.size(); i++) {
			System.out.println(records.get(i).getTime() + ","
					+ records.get(i).getPlace() + " , "
					+ records.get(i).getContent());
		}

	}

	public void getReceiveMessage() {
		ArrayList<InviteMessage> message = service.getReceiveMessage("001");
		for (int i = 0; i < message.size(); i++) {
			System.out.println(message.get(i).getTelephone() + ","
					+ message.get(i).getUserID() + " , "
					+ message.get(i).getContent());
		}
	}

	public void getSendMessage() {
		ArrayList<InviteMessage> message = service.getSendMessage("001");
		for (int i = 0; i < message.size(); i++) {
			System.out.println(message.get(i).getTelephone() + ","
					+ message.get(i).getUserID() + " , "
					+ message.get(i).getContent());
		}
	}
	
	public void testRead(){
		service.readMessage("2015431925","read");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MessageServiceTest test = new MessageServiceTest();
		// test.sendGetRunMateMessage();
		// test.sendInviteMessage();
		// test.getGetRunMateRecord();

		// test.getReceiveMessage();
//		test.getSendMessage();
		test.testRead();
	}

}
