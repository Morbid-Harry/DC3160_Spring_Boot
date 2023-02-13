package com.dc3160.DC3160_Spring_Boot.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.dc3160.DC3160_Spring_Boot.Service.ExerciseService;
import com.dc3160.DC3160_Spring_Boot.beans.ExerciseRecord;
import com.dc3160.DC3160_Spring_Boot.beans.ExerciseType;
import com.dc3160.DC3160_Spring_Boot.beans.User;

@RequestMapping("AddExercise")
@SessionAttributes({"user", "userPreferences"})
@Controller 
public class AddExercise {
	@Autowired
	ExerciseService exerciseService;
	
	
	@PostMapping
	protected RedirectView doPost(@SessionAttribute("user") User user, @RequestParam("exercise-type-done") String exerciseType, @RequestParam("exercise-duration-hours") double exerciseDuration, @RequestParam("exercise-date-done") String exerciseDate) {
		
		//Convert date to actual date
		Date dateAsDate = Date.valueOf(exerciseDate);
		
		//Set value of string to enum
		ExerciseType type = ExerciseType.valueOf(exerciseType);
		
		//Get userId
		int userID = user.getUserID();
		
		ExerciseRecord record = new ExerciseRecord();
		
		record.setUserID(userID);
		record.setExerciseType(type);
		record.setExerciseDuration(exerciseDuration);
		record.setExerciseDate(dateAsDate);
		
		exerciseService.addExercise(record);
		
		return new RedirectView("Exercise");
	}
	
}
