package com.nju.FitClubServer.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="WeightRecordList")
public class WeightRecordList {
	private ArrayList<WeightRecord> records;

	public ArrayList<WeightRecord> getRecords() {
		return records;
	}

	public void setRecords(ArrayList<WeightRecord> records) {
		this.records = records;
	}
	
	
}
