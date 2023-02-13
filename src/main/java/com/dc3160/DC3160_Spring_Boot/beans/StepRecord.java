package com.dc3160.DC3160_Spring_Boot.beans;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "step_table")
public class StepRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="step_id")
	private int id;
	
	@Column(name ="fk_user_id")
	private int userID;
	
	private int stepCount;
	
	private Date stepDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getStepCount() {
		return stepCount;
	}

	public void setStepCount(int stepCount) {
		this.stepCount = stepCount;
	}

	public Date getStepDate() {
		return stepDate;
	}

	public void setStepDate(Date stepDate) {
		this.stepDate = stepDate;
	}
	
	
}
