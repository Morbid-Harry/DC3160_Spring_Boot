package com.dc3160.DC3160_Spring_Boot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dc3160.DC3160_Spring_Boot.Service.MealService;
import com.dc3160.DC3160_Spring_Boot.Service.PreferenceService;
import com.dc3160.DC3160_Spring_Boot.Service.StepService;
import com.dc3160.DC3160_Spring_Boot.Service.UserService;
import com.dc3160.DC3160_Spring_Boot.beans.MealRecord;
import com.dc3160.DC3160_Spring_Boot.beans.Preference;
import com.dc3160.DC3160_Spring_Boot.beans.StepRecord;
import com.dc3160.DC3160_Spring_Boot.beans.User;



@RequestMapping("Dashboard")
@SessionAttributes({"user", "userPreferences"})
@Controller    
public class Dashboard {
		@Autowired
		PreferenceService preferenceService;
		@Autowired
		StepService stepService;
		@Autowired
		UserService userService;
		@Autowired
		MealService mealService;

		@GetMapping
		protected ModelAndView doGet(Model model, @SessionAttribute("userPreferences") Preference preferences, @SessionAttribute("user") User user){
			
			
			//-------------------------DAILY STEPS--------------//
			//Calculate Steps done today
			List<StepRecord> allStepsDoneToday = stepService.getStepsTodaysDate(user.getUserID());
			int stepCount = 0;
			
			for(StepRecord step : allStepsDoneToday)
			{
				stepCount += step.getStepCount();
			}
			
			ModelAndView modelAndView = new ModelAndView("dashboard.html");
			
			model.addAttribute("stepsDone", stepCount);
			int stepsLeft = preferences.getStepTarget() - stepCount;
			
			if(stepsLeft < 0)
			{
				stepsLeft = 0;
			}
			
			model.addAttribute("stepsLeft", stepsLeft);
			//--------------------------------------------------//
			
			//-------------------------DAILY CALORIES-----------//
			//Calculate Calories Eaten Today
			List<MealRecord> allCaloriesEatenToday = mealService.getCaloriesTodaysDate(user.getUserID());
			int calorieCount = 0;
			
			for(MealRecord meal : allCaloriesEatenToday)
			{
				calorieCount += meal.getMealCalories();
			}
			
			model.addAttribute("caloriesEaten", calorieCount);
			
			int caloriesLeft = preferences.getCalorieTarget() - calorieCount;
			
			if(caloriesLeft < 0)
			{
				caloriesLeft = 0;
			}
			
			model.addAttribute("caloriesLeft", caloriesLeft);
			//--------------------------------------------------//
			
			
			
			return modelAndView;
		}
	
}
