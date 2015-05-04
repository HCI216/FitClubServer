package com.nju.FitClubServer.service.impl;

import java.util.ArrayList;

import com.nju.FitClubServer.dao.PlanDao;
import com.nju.FitClubServer.dao.impl.PlanDaoImpl;
import com.nju.FitClubServer.model.Plan;
import com.nju.FitClubServer.service.PlanService;

public class PlanServiceImpl implements PlanService {

	private PlanDao dao = new PlanDaoImpl();

	public Plan askForPlan(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Plan getPlan(String userID) {
		// TODO Auto-generated method stub
		ArrayList<Plan> plans = new ArrayList<Plan>();
		Plan plan = new Plan();
		try {
			plans = dao.getPlanByUserID(userID);
			for (int i = 0; i < plans.size(); i++) {
				if(plans.get(i).getState().equals("process")){
					plan = plans.get(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plan;
	}
}
