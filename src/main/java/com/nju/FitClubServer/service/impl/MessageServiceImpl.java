package com.nju.FitClubServer.service.impl;

import java.util.ArrayList;
import java.util.Calendar;

import javax.ws.rs.core.Response;

import com.nju.FitClubServer.dao.GetRunMateRecordDao;
import com.nju.FitClubServer.dao.InviteMessageDao;
import com.nju.FitClubServer.dao.impl.GetRunMateRecordDaoImpl;
import com.nju.FitClubServer.dao.impl.InviteMessageDaoImpl;
import com.nju.FitClubServer.model.GetRunMateRecord;
import com.nju.FitClubServer.model.GetRunMateRecordList;
import com.nju.FitClubServer.model.InviteMessage;
import com.nju.FitClubServer.model.InviteMessageList;
import com.nju.FitClubServer.service.MessageService;

public class MessageServiceImpl implements MessageService {

	private InviteMessageDao inviteMessageDao = new InviteMessageDaoImpl();
	private GetRunMateRecordDao getRunMateRecordDao = new GetRunMateRecordDaoImpl();

	public Response sendGetRunMateMessage(GetRunMateRecord getRunMateRecord) {
		try {
			String recordID = getNewID();
			while (getRunMateRecordDao.getGetRunMateRecordByID(recordID) != null) {
				new Thread().sleep(1000);
				recordID = getNewID();
			}
			getRunMateRecord.setGetRunMateRecordID(recordID);
			boolean result = getRunMateRecordDao
					.addGetRunMateRecord(getRunMateRecord);
			if (result)
				return Response.ok(true).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(false).build();
	}

	public Response sendInviteMessage(InviteMessage inviteMessage) {
		// TODO Auto-generated method stub
		try {
			String recordID = getNewID();
			while (inviteMessageDao.getInviteMessage(recordID) != null) {
				new Thread().sleep(1000);
				recordID = getNewID();
			}
			inviteMessage.setInviteMessageID(recordID);
			boolean result = inviteMessageDao.addInviteMessage(inviteMessage);
			if (result)
				return Response.ok(true).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(false).build();
	}

	public GetRunMateRecordList getGetRunMateRecord() {
		// TODO Auto-generated method stub
		GetRunMateRecordList list = new GetRunMateRecordList();
		ArrayList<GetRunMateRecord> recordList = new ArrayList<GetRunMateRecord>();
		try {
			recordList = getRunMateRecordDao.getAllGetRunMateRecord();
		} catch (Exception e) {
			e.printStackTrace();
		}
		list.setGetRunMateRecordList(recordList);
		return list;
	}

	public InviteMessageList getReceiveMessage(String userID) {
		// TODO Auto-generated method stub
		InviteMessageList list = new InviteMessageList();
		ArrayList<InviteMessage> messages = new ArrayList<InviteMessage>();
		try {
			ArrayList<GetRunMateRecord> records = getRunMateRecordDao
					.getGetRunMateRecordByUserID(userID);
			for (int i = 0; i < records.size(); i++) {
				ArrayList<InviteMessage> tmp = inviteMessageDao
						.getInviteMessageListByGetRunMateRecordID(records
								.get(i).getGetRunMateRecordID());
				for (int j = 0; j < tmp.size(); j++) {
					messages.add(tmp.get(j));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		list.setInviteMessageList(messages);
		return list;
	}

	public InviteMessageList getSendMessage(String userID) {
		InviteMessageList list = new InviteMessageList();
		ArrayList<InviteMessage> messages = new ArrayList<InviteMessage>();
		try {
			messages = inviteMessageDao.getInviteMessageListByUserID(userID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		list.setInviteMessageList(messages);
		return list;
	}

	private String getNewID() {
		Calendar cal = Calendar.getInstance();
		String sYear = cal.get(Calendar.YEAR) + "";
		String sMonth = getNewStr(cal.get(Calendar.MONTH) + 1);
		String sDay = getNewStr(cal.get(Calendar.DAY_OF_MONTH));
		String hour = getNewStr(cal.get(Calendar.HOUR_OF_DAY));
		String minutes = getNewStr(cal.get(Calendar.MINUTE));
		String sec = getNewStr(cal.get(Calendar.SECOND));
		return sYear + sMonth + sDay + hour + minutes + sec;
	}

	public String getNewStr(int i) {
		if (i < 10)
			return "0" + i;
		return "" + i;
	}

	public Response readMessage(String inviteMessageID, String newState) {
		// TODO Auto-generated method stub
		try {
			InviteMessage message = inviteMessageDao
					.getInviteMessage(inviteMessageID);
			message.setState(newState);
			boolean result = inviteMessageDao.updateInviteMessage(message);
			if (result)
				return Response.ok(true).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(false).build();
	}
}
