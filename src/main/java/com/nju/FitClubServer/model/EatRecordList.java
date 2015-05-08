package com.nju.FitClubServer.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "EatRecordList")
public class EatRecordList {
	private ArrayList<EatRecord> eatRecordList;

	public ArrayList<EatRecord> getEatRecordList() {
		return eatRecordList;
	}

	public void setEatRecordList(ArrayList<EatRecord> eatRecordList) {
		this.eatRecordList = eatRecordList;
	}
}
