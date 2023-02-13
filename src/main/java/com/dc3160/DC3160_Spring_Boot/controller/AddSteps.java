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

import com.dc3160.DC3160_Spring_Boot.Service.StepService;
import com.dc3160.DC3160_Spring_Boot.beans.StepRecord;
import com.dc3160.DC3160_Spring_Boot.beans.User;

@RequestMapping("AddSteps")
@SessionAttributes({"user", "userPreferences"})
@Controller 
public class AddSteps {
	@Autowired
	StepService stepService;
	
	
	@PostMapping
	protected RedirectView doPost(@SessionAttribute("user") User user, @RequestParam("exercise-step-count") int stepCount, @RequestParam("exercise-step-date") String stepDate) {
		
		//Convert date to actual date
		Date dateAsDate = Date.valueOf(stepDate);
		
		//Get userId
		int userID = user.getUserID();
		
		StepRecord record = new StepRecord();
		
		record.setUserID(userID);
		record.setStepCount(stepCount);
		record.setStepDate(dateAsDate);
		
		stepService.addStep(record);
		
		return new RedirectView("Exercise");
	}
	
}
