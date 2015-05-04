package com.nju.FitClubServer.service.impl;

import java.util.ArrayList;
import java.util.Calendar;

import com.nju.FitClubServer.dao.GetRunMateRecordDao;
import com.nju.FitClubServer.dao.InviteMessageDao;
import com.nju.FitClubServer.dao.impl.GetRunMateRecordDaoImpl;
import com.nju.FitClubServer.dao.impl.InviteMessageDaoImpl;
import com.nju.FitClubServer.model.GetRunMateRecord;
import com.nju.FitClubServer.model.InviteMessage;
import com.nju.FitClubServer.service.MessageService;

public class MessageServiceImpl implements MessageService {

	private InviteMessageDao inviteMessageDao = new InviteMessageDaoImpl();
	private GetRunMateRecordDao getRunMateRecordDao = new GetRunMateRecordDaoImpl();

	public boolean sendGetRunMateMessage(GetRunMateRecord getRunMateRecord) {
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
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean sendInviteMessage(InviteMessage inviteMessage) {
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
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<GetRunMateRecord> getGetRunMateRecord() {
		// TODO Auto-generated method stub
		ArrayList<GetRunMateRecord> recordList = new ArrayList<GetRunMateRecord>();
		try {
			recordList = getRunMateRecordDao.getAllGetRunMateRecord();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recordList;
	}

	public ArrayList<InviteMessage> getReceiveMessage(String userID) {
		// TODO Auto-generated method stub
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
		return messages;
	}

	public ArrayList<InviteMessage> getSendMessage(String userID) {
		ArrayList<InviteMessage> messages = new ArrayList<InviteMessage>();
		try {
			messages = inviteMessageDao.getInviteMessageListByUserID(userID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messages;
	}

	private String getNewID() {
		Calendar cal = Calendar.getInstance();
		String year = cal.get(Calendar.YEAR) + "";
		String month = cal.get(Calendar.MONTH) + "";
		String day = cal.get(Calendar.DAY_OF_MONTH) + "";
		String hour = cal.get(Calendar.HOUR_OF_DAY) + "";
		String minutes = cal.get(Calendar.MINUTE) + "";
		String sec = cal.get(Calendar.SECOND) + "";
		return year + month + day + hour + minutes + sec;
	}

	public boolean readMessage(String inviteMessageID,String newState) {
		// TODO Auto-generated method stub
		try {
			InviteMessage message = inviteMessageDao
					.getInviteMessage(inviteMessageID);
			message.setState(newState);
			boolean result = inviteMessageDao.updateInviteMessage(message);
			if (result)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
