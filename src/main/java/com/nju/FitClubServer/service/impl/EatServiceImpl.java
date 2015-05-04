package com.nju.FitClubServer.service.impl;

import java.util.ArrayList;
import java.util.Calendar;

import com.nju.FitClubServer.dao.EatFoodRecordDao;
import com.nju.FitClubServer.dao.EatRecordDao;
import com.nju.FitClubServer.dao.impl.EatFoodRecordDaoImpl;
import com.nju.FitClubServer.dao.impl.EatRecordDaoImpl;
import com.nju.FitClubServer.model.EatFoodRecord;
import com.nju.FitClubServer.model.EatRecord;
import com.nju.FitClubServer.service.EatService;

public class EatServiceImpl implements EatService {

	private EatRecordDao eatRecordDao = new EatRecordDaoImpl();
	private EatFoodRecordDao eatFoodRecordDao = new EatFoodRecordDaoImpl();

	public boolean addEatRecord(EatRecord eatRecord) {
		// TODO Auto-generated method stub
		try {
			String eatRecordID = getNewID();
			while (eatRecordDao.getEatRecordByID(eatRecordID) != null) {
				new Thread().sleep(1000);
				eatRecordID = getNewID();
			}
			eatRecord.setEatRecordID(eatRecordID);
			if (!eatRecordDao.addEatRecord(eatRecord))
				return false;
			ArrayList<EatFoodRecord> foodList = eatRecord.getFoodList();
			for (int i = 0; i < foodList.size(); i++) {
				String eatFoodRecordID = getNewID();
				while (eatFoodRecordDao.getEatFoodRecordByID(eatFoodRecordID) != null) {
					new Thread().sleep(1000);
					eatFoodRecordID = getNewID();
				}
				EatFoodRecord eatFoodRecord = foodList.get(i);
				eatFoodRecord.setEatFoodRecordID(eatFoodRecordID);
				eatFoodRecord.setEatRecordID(eatRecordID);
				if (!eatFoodRecordDao.addEatFoodRecord(eatFoodRecord))
					return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public ArrayList<EatRecord> getEatRecord(String userID, String time) {
		// TODO Auto-generated method stub
		ArrayList<EatRecord> eatRecordList = new ArrayList<EatRecord>();
		try {
			eatRecordList = eatRecordDao.getEatRecordByUserIDAndDay(userID,
					time);

			for (int i = 0; i < eatRecordList.size(); i++) {
				EatRecord record = eatRecordList.get(i);
				record.setFoodList(eatFoodRecordDao
						.getEatFoodRecordsByEatRecordID(record.getEatRecordID()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eatRecordList;
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

}
