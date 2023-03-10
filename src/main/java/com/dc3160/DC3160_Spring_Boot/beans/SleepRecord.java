package com.dc3160.DC3160_Spring_Boot.beans;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "sleep_table")
public class SleepRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sleep_id")
	private int id;
	
	@Column(name ="fk_user_id")
	private int userID;
	
	private Double sleepHours;
	
	private Date sleepDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
