package com.dc3160.DC3160_Spring_Boot.beans;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "exercise_table")
public class ExerciseRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exercise_id")
	private int id;
	
	@Column(name ="fk_user_id")
	private int userID;
	
	@Enumerated(EnumType.STRING)
	private ExerciseType exerciseType;
	
	private double exerciseDuration;
	
	private Date exerciseDate;

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

	public ExerciseType getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(ExerciseType exerciseType) {
		this.exerciseType = exerciseType;
	}

	public double getExerciseDuration() {
		return exerciseDuration;
	}

	public void setExerciseDuration(double exerciseDuration) {
		this.exerciseDuration = exerciseDuration;
	}

	public Date getExerciseDate() {
		return exerciseDate;
	}

	public void setExerciseDate(Date exerciseDate) {
		this.exerciseDate = exerciseDate;
	}
	
	
}

