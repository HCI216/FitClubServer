package com.nju.FitClubServer.service.impl;

import java.util.ArrayList;
import java.util.Calendar;

import com.nju.FitClubServer.dao.EatFoodRecordDao;
import com.nju.FitClubServer.dao.EatRecordDao;
import com.nju.FitClubServer.dao.impl.EatFoodRecordDaoImpl;
import com.nju.FitClubServer.dao.impl.EatRecordDaoImpl;
import com.nju.FitClubServer.model.EatFoodRecord;
import com.nju.FitClubServer.model.EatRecord;
import com.nju.FitClubServer.model.EatRecordList;
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

	public EatRecordList getEatRecord(String userID, String time) {
		// TODO Auto-generated method stub
		EatRecordList list = new EatRecordList();
		ArrayList<EatRecord> eatRecordList = new ArrayList<EatRecord>();
		try {
			eatRecordList = eatRecordDao.getEatRecordByUserIDAndDay(userID,
					time);

			for (int i = 0; i < eatRecordList.size(); i++) {
				EatRecord record = eatRecordList.get(i);
				record.setFoodList(eatFoodRecordDao
						.getEatFoodRecordsByEatRecordID(record.getEatRecordID()));
			}
			list.setEatRecordList(eatRecordList);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public static void main(String[] args) {
		EatServiceImpl impl = new EatServiceImpl();
		System.out.println(impl.getNewID());
	}

}
