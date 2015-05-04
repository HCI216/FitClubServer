package com.nju.FitClubServer.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="CoordinateRecord")
public class CoordinateRecord {

	private String coordinateRecordID;
	private String runRecordID;
	private double x;
	private double y;

	public String getCoordinateRecordID() {
		return coordinateRecordID;
	}

	public void setCoordinateRecordID(String coordinateRecordID) {
		this.coordinateRecordID = coordinateRecordID;
	}

	public String getRunRecordID() {
		return runRecordID;
	}

	public void setRunRecordID(String runRecordID) {
		this.runRecordID = runRecordID;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
