package com.nju.FitClubServer.dao;

import com.nju.FitClubServer.dao.impl.UserDaoImpl;
import com.nju.FitClubServer.model.User;

public class UserDaoTest {

	private static UserDao userDao ;
	
	static {
		userDao = new UserDaoImpl();
	}

	public static void addUserTest() throws Exception{
		User user = new User();
		user.setUserID("201504301441");
		user.setUserName("xxd");
		user.setPassword("12345");
		user.setEmail("597896577@qq.com");
		user.setHeight(173);
		user.setBirthDay("19940511");
		user.setLoginOrNot(false);
		user.setLoseWeight(21);
		user.setLoseWeightTime(2);
		user.setSex("男");
		user.setSportDayAWeek(7);
		userDao.addUser(user);
	}
	
	public static void deleteUserTest() throws Exception{
		userDao.deleteUser("201504301441");
	}
	
	public static void updateUserTest() throws Exception{
		User user = new User();
		user.setUserID("201504301427");
		user.setUserName("admin");
		user.setPassword("12345");
		user.setEmail("597896577@qq.com");
		user.setHeight(173);
		user.setBirthDay("19940511");
		user.setLoginOrNot(false);
		user.setLoseWeight(21);
		user.setLoseWeightTime(2);
		user.setSex("男");
		user.setSportDayAWeek(7);
		userDao.updateUser(user);
	}
	
	public static void searchUserByNameTest() throws Exception{
		User user = userDao.searchUserByName("admin");
		System.out.println(user.getUserID());
	}

	public static void searchUserByIDTest() throws Exception{
		User user= userDao.searchUserByID("201504301427");
		System.out.println(user.getUserName());
	}
	
	
	public static void main(String[] args) throws Exception{
//		addUserTest();
//		deleteUserTest();
//		updateUserTest();
//		searchUserByIDTest();
//		searchUserByNameTest();
	}

}
