package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.model.Plan;

public interface PlanDao {

	public boolean addPlan(Plan plan) throws Exception;

	public boolean deletePlan(String planID) throws Exception;

	public boolean updatePlan(Plan plan) throws Exception;

	public ArrayList<Plan> getPlanByUserID(String userID) throws Exception;

	public Plan getPlanByID(String planID) throws Exception;
	
}
