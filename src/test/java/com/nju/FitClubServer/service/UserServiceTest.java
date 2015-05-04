package com.nju.FitClubServer.service;

import javax.ws.rs.PathParam;

import com.nju.FitClubServer.service.impl.UserServiceImpl;

public class UserServiceTest {

	private UserService userService = new UserServiceImpl();

	
	public void recordWeight(){
		userService.recordWeight("201504301427", 80);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserServiceTest test = new UserServiceTest();
		test.recordWeight();
	}

}
