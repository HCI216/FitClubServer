package com.nju.FitClubServer.Enum;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="LogoutResult")
public enum LogoutResult {
	SQL_ERROR,NO_SUCH_USER,SUCCESS,Logout_ALREADY
}
