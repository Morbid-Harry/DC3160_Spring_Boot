package com.dc3160.DC3160_Spring_Boot.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "preference_table")
public class Preference{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="preference_id")
	private int id;
	
	@Column(name ="fk_user_id")
	private int userID;
	
	private int age, weightStone,weightPounds, heightFeet, heightInches, stepTarget, calorieTarget;
	
	private double exerciseTarget;
	
	public Preference()
	{
		
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getWeightStone() {
		return weightStone;
	}

	public void setWeightStone(int weightStone) {
		this.weightStone = weightStone;
	}

	public int getWeightPounds() {
		return weightPounds;
	}

	public void setWeightPounds(int weightPounds) {
		this.weightPounds = weightPounds;
	}

	public int getHeightFeet() {
		return heightFeet;
	}

	public void setHeightFeet(int heightFeet) {
		this.heightFeet = heightFeet;
	}

	public int getHeightInches() {
		return heightInches;
	}

	public void setHeightInches(int heightInches) {
		this.heightInches = heightInches;
	}

	public int getStepTarget() {
		return stepTarget;
	}

	public void setStepTarget(int stepTarget) {
		this.stepTarget = stepTarget;
	}

	public int getCalorieTarget() {
		return calorieTarget;
	}

	public void setCalorieTarget(int calorieTarget) {
		this.calorieTarget = calorieTarget;
	}

	public double getExerciseTarget() {
		return exerciseTarget;
	}

	public void setExerciseTarget(double exerciseTarget) {
		this.exerciseTarget = exerciseTarget;
	}
	

}
