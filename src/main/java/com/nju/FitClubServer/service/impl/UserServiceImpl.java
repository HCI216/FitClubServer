package com.nju.FitClubServer.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.nju.FitClubServer.Enum.LoginResult;
import com.nju.FitClubServer.Enum.LogoutResult;
import com.nju.FitClubServer.Enum.RegisterResult;
import com.nju.FitClubServer.dao.UserDao;
import com.nju.FitClubServer.dao.impl.UserDaoImpl;
import com.nju.FitClubServer.model.ImageHelperModel;
import com.nju.FitClubServer.model.User;
import com.nju.FitClubServer.service.UserService;

public class UserServiceImpl implements UserService {

	private static UserDao userdao = new UserDaoImpl();

	public Response login(String userName, User userlogin) {
		try {
			User user = userdao.searchUserByName(userName);
			if (user == null)
				return Response.ok(LoginResult.NO_SUCH_USER).build();
			if (!user.getPassword().equals(userlogin.getPassword()))
				return Response.ok(LoginResult.USER_PASSWORD_NOT_MATCH).build();
			if (user.isLoginOrNot())
				return Response.ok(LoginResult.Login_ALREADY).build();
			userlogin.setLoginOrNot(true);
			userdao.updateUser(userlogin);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.ok(LoginResult.SUCCESS).build();
	}

	public Response register(User user) {
		try {
			if (userdao.searchUserByName(user.getUserName()) != null)
				return Response.ok(RegisterResult.NAME_DULP).build();
			userdao.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.ok(RegisterResult.SUCCESS).build();
	}

	public void uploadImage(String username, ImageHelperModel imageHelper) {
		OutputStream os = null;
		String path = "/home/xxd/ibeyondy/server/" + username + ".png";
		File pic = new File(path);
		if (!pic.exists()) {
			try {
				pic.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			if (imageHelper.getPosition() != 0) {
				os = FileUtils.openOutputStream(pic, true);
			} else {
				os = FileUtils.openOutputStream(pic, false);
			}
			os.write(imageHelper.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(os);
		}
	}

	public ImageHelperModel downloadImage(String username, long position) {
		InputStream ins = null;
		ImageHelperModel myfile = new ImageHelperModel();
		String path = "/home/xxd/ibeyondy/server/" + username + ".png";
		try {
			ins = new FileInputStream(path);
			ins.skip(position);
			byte[] bytes = new byte[1024 * 1024];
			int size = ins.read(bytes);
			if (size > 0) {
				byte[] fixBytes = Arrays.copyOfRange(bytes, 0, size);
				myfile.setBytes(fixBytes);
			} else {
				myfile.setBytes(new byte[0]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(ins);
		}
		return myfile;
	}

	public Response logout(String userName, User userLogout) {
		try {
			User user = userdao.searchUserByName(userName);
			if (user == null)
				return Response.ok(LogoutResult.NO_SUCH_USER).build();
			if (!user.isLoginOrNot())
				return Response.ok(LogoutResult.Logout_ALREADY).build();
			user.setLoginOrNot(false);
			userdao.updateUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.ok(LogoutResult.SUCCESS).build();
	}

}
