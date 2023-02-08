package com.dc3160.DC3160_Spring_Boot.beans;

import java.sql.Date;

public class SleepRecord {
	
	private int sleepID;
	private int userID;
	private Double sleepHours;
	private Date sleepDate;
	public int getSleepID() {
		return sleepID;
	}
	public void setSleepID(int sleepID) {
		this.sleepID = sleepID;
	}
	public Double getSleepHours() {
		return sleepHours;
	}
	public void setSleepHours(Double sleepHours) {
		this.sleepHours = sleepHours;
	}
	public Date getSleepDate() {
		return sleepDate;
	}
	public void setSleepDate(Date sleepDate) {
		this.sleepDate = sleepDate;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	

}
