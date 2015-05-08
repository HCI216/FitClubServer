package com.nju.FitClubServer.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "RunRecordList")
public class RunRecordList {
	private ArrayList<RunRecord> records;

	public ArrayList<RunRecord> getRecords() {
		return records;
	}

	public void setRecords(ArrayList<RunRecord> records) {
		this.records = records;
	}

}
