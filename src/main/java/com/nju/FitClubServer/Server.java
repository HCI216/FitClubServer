package com.nju.FitClubServer;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import com.nju.FitClubServer.Enum.LoginResult;
import com.nju.FitClubServer.Enum.RegisterResult;
import com.nju.FitClubServer.model.ImageHelperModel;
import com.nju.FitClubServer.model.User;
import com.nju.FitClubServer.service.UserService;
import com.nju.FitClubServer.service.impl.UserServiceImpl;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserService userService = new UserServiceImpl();
		JAXRSServerFactoryBean server = new JAXRSServerFactoryBean();
		server.setResourceClasses(User.class,LoginResult.class,RegisterResult.class,ImageHelperModel.class);
		server.setServiceBean(userService);
		server.setAddress("http://localhost:9999/");
		server.create();
	}

}
