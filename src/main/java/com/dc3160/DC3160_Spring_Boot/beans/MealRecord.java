package com.dc3160.DC3160_Spring_Boot.beans;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "meal_table")
public class MealRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="meal_id")
	private int id;
	
	@Column(name ="fk_user_id")
	private int userID;
	
	private String mealName;
	
	private int mealCalories;
	
	private Date mealDate;

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

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public int getMealCalories() {
		return mealCalories;
	}

	public void setMealCalories(int calories) {
		this.mealCalories = calories;
	}

	public Date getMealDate() {
		return mealDate;
	}

	public void setMealDate(Date mealDate) {
		this.mealDate = mealDate;
	}
	
	
	
	
}
