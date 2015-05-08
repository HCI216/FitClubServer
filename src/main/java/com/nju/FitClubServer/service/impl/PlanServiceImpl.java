package com.nju.FitClubServer.service.impl;

import java.util.ArrayList;
import java.util.Calendar;

import javax.ws.rs.core.Response;

import com.nju.FitClubServer.dao.PlanDao;
import com.nju.FitClubServer.dao.UserDao;
import com.nju.FitClubServer.dao.WeightChangeRecordDao;
import com.nju.FitClubServer.dao.impl.PlanDaoImpl;
import com.nju.FitClubServer.dao.impl.UserDaoImpl;
import com.nju.FitClubServer.dao.impl.WeightChangeRecordDaoImpl;
import com.nju.FitClubServer.model.Plan;
import com.nju.FitClubServer.model.User;
import com.nju.FitClubServer.model.WeightChangeRecord;
import com.nju.FitClubServer.service.PlanService;

public class PlanServiceImpl implements PlanService {

	private PlanDao dao = new PlanDaoImpl();
	private WeightChangeRecordDao weightChangeDao = new WeightChangeRecordDaoImpl();
	private UserDao userDao = new UserDaoImpl(); 
	
	public Response askForPlan(String userID) {
		// TODO Auto-generated method stub
		Plan plan = new Plan();
		try {
			User user = userDao.searchUserByID(userID);
			PlanMahoutHelper helper = new PlanMahoutHelper(user);
			String weightChangeID = helper.run();
			WeightChangeRecord record = weightChangeDao.getWeightChangeRecordByID(weightChangeID);
			String startDate = record.getStartTime();
			String endDate = record.getEndTime();
			
			plan.setFromUserID(record.getUserID());
			plan.setStartTime(startDate);
			plan.setEndTime(endDate);
			plan.setState("process");
			plan.setToUserID(userID);
			String planID = getNewID();
			while(dao.getPlanByID(planID) != null) planID = getNewID();
			plan.setPlanID(planID);
			dao.addPlan(plan);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(plan).build();
	}

	public Plan getPlan(String userID) {
		// TODO Auto-generated method stub
		ArrayList<Plan> plans = new ArrayList<Plan>();
		Plan plan = new Plan();
		try {
			plans = dao.getPlanByUserID(userID);
			for (int i = 0; i < plans.size(); i++) {
				if (plans.get(i).getState().equals("process")) {
					plan = plans.get(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plan;
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
	
}
