package com.nju.FitClubServer.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "GetRunMateRecordList")
public class GetRunMateRecordList {
	private ArrayList<GetRunMateRecord> getRunMateRecordList;

	public ArrayList<GetRunMateRecord> getGetRunMateRecordList() {
		return getRunMateRecordList;
	}

	public void setGetRunMateRecordList(
			ArrayList<GetRunMateRecord> getRunMateRecordList) {
		this.getRunMateRecordList = getRunMateRecordList;
	}

}
