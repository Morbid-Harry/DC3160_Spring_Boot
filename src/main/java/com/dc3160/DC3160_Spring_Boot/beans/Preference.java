package com.dc3160.DC3160_Spring_Boot.beans;

import java.io.Serializable;

public class Preference implements Serializable{
	
	private int preferenceID, fkUserID, age, weightStone,weightPounds, 
					heightFeet, heightInches, stepTarget, calorieTarget;
	
	private double exerciseTarget;
	
	public Preference()
	{
		
	}

	public int getPreferenceID() {
		return preferenceID;
	}

	public void setPreferenceID(int preferenceID) {
		this.preferenceID = preferenceID;
	}

	public int getFkUserID() {
		return fkUserID;
	}

	public void setFkUserID(int fkUserID) {
		this.fkUserID = fkUserID;
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
