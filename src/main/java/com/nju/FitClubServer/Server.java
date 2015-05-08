package com.nju.FitClubServer;

import java.awt.List;
import java.util.ArrayList;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import com.nju.FitClubServer.Enum.LoginResult;
import com.nju.FitClubServer.Enum.RegisterResult;
import com.nju.FitClubServer.model.EatRecordList;
import com.nju.FitClubServer.model.FoodCategoryList;
import com.nju.FitClubServer.model.FoodList;
import com.nju.FitClubServer.model.GetRunMateRecordList;
import com.nju.FitClubServer.model.ImageHelperModel;
import com.nju.FitClubServer.model.InviteMessageList;
import com.nju.FitClubServer.model.Plan;
import com.nju.FitClubServer.model.RunRecord;
import com.nju.FitClubServer.model.RunRecordList;
import com.nju.FitClubServer.model.User;
import com.nju.FitClubServer.model.WeightChangeRecord;
import com.nju.FitClubServer.model.WeightRecord;
import com.nju.FitClubServer.model.WeightRecordList;
import com.nju.FitClubServer.service.EatService;
import com.nju.FitClubServer.service.FoodService;
import com.nju.FitClubServer.service.MessageService;
import com.nju.FitClubServer.service.PlanService;
import com.nju.FitClubServer.service.RunService;
import com.nju.FitClubServer.service.UserService;
import com.nju.FitClubServer.service.impl.EatServiceImpl;
import com.nju.FitClubServer.service.impl.FoodServiceImpl;
import com.nju.FitClubServer.service.impl.MessageServiceImpl;
import com.nju.FitClubServer.service.impl.PlanServiceImpl;
import com.nju.FitClubServer.service.impl.RunServiceImpl;
import com.nju.FitClubServer.service.impl.UserServiceImpl;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserService userService = new UserServiceImpl();
		RunService runService = new RunServiceImpl();
		EatService eatService = new EatServiceImpl();
		FoodService foodService = new FoodServiceImpl();
		MessageService messageService = new MessageServiceImpl();
		PlanService planService = new PlanServiceImpl();
		JAXRSServerFactoryBean server = new JAXRSServerFactoryBean();
		server.setResourceClasses(User.class, LoginResult.class,
				RegisterResult.class, ImageHelperModel.class,
				WeightChangeRecord.class, WeightRecord.class,
				WeightRecordList.class, RunRecordList.class,
				EatRecordList.class, FoodCategoryList.class, FoodList.class,
				InviteMessageList.class, GetRunMateRecordList.class,Plan.class);
		ArrayList<Object> serviceList = new ArrayList<Object>();
		serviceList.add(userService);
		serviceList.add(runService);
		serviceList.add(eatService);
		serviceList.add(planService);
		serviceList.add(messageService);
		serviceList.add(foodService);
		server.setServiceBeans(serviceList);
		server.setAddress("http://localhost:9999/");
		server.create();
	}

}
