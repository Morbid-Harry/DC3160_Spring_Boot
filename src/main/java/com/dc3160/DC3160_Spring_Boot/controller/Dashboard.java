package com.dc3160.DC3160_Spring_Boot.controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.dc3160.DC3160_Spring_Boot.Service.SleepService;
import com.dc3160.DC3160_Spring_Boot.Service.StepService;
import com.dc3160.DC3160_Spring_Boot.Service.UserService;
import com.dc3160.DC3160_Spring_Boot.beans.MealRecord;
import com.dc3160.DC3160_Spring_Boot.beans.Preference;
import com.dc3160.DC3160_Spring_Boot.beans.SleepRecord;
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
		@Autowired
		SleepService sleepService;

		@GetMapping
		protected ModelAndView doGet(Model model, @SessionAttribute("userPreferences") Preference preferences, @SessionAttribute("user") User user){
			
			//Used for weekly graphs data
			//Gets the current day of the week
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(System.currentTimeMillis());
			
			//Set start of week
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			
			List<Date> datesOfWeek = new ArrayList<>();
			
			//Get all 7 dates of the week
			for(int i = 0; i < 7; i++)
			{
				//get the util date
				java.util.Date utilDate = calendar.getTime();
				
				//convert to sql date
				Date date = new java.sql.Date(utilDate.getTime());
				
				datesOfWeek.add(date);
				//Get the next day of the week
				calendar.add(Calendar.DATE, 1);
				
			}
			
			
			
			
			//-------------------------DAILY STEPS GRAPH--------------//
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
			
			//-------------------------WEEKLY GRAPHS--------------//
			
			//Make list for each weekly graph record
			List<Integer> weeklyStepsCount = new ArrayList<>();
			List<Integer> weeklyCalorieCount = new ArrayList<>();
			List<Double> weeklySleepCount = new ArrayList<>();
			
			for(Date dateOfWeek : datesOfWeek)
			{
				//Get all records for that day 
				List<StepRecord> stepsThatDay = stepService.getStepsTodaysDate(user.getUserID(), dateOfWeek);
				List<MealRecord> caloriesThatDay = mealService.getCaloriesTodaysDate(user.getUserID(), dateOfWeek);
				List<SleepRecord> sleepThatDay = sleepService.getSleepTodaysDate(user.getUserID(), dateOfWeek);
				
				//Sums all calories eaten, steps done, sleep taken for that day
				int stepCountThatDay = 0;
				int calorieCountThatDay = 0;
				double sleepCountThatDay = 0;
				
				for(StepRecord step : stepsThatDay)
				{
					stepCountThatDay += step.getStepCount();
				}
				
				//Add the steps for that day to list
				weeklyStepsCount.add(stepCountThatDay);
				
				for(MealRecord meal : caloriesThatDay)
				{
					calorieCountThatDay += meal.getMealCalories();
				}
				
				//Add calories for that day to list
				weeklyCalorieCount.add(calorieCountThatDay);
				
				for(SleepRecord sleep : sleepThatDay)
				{
					sleepCountThatDay += sleep.getSleepHours();
				}
				
				weeklySleepCount.add(sleepCountThatDay);
				
			}
			
			
			model.addAttribute("weeklySteps", weeklyStepsCount);
			model.addAttribute("weeklyCalories", weeklyCalorieCount);
			model.addAttribute("weeklySleep", weeklySleepCount);
			//--------------------------------------------------//
			
			
			
			//-------------------------DAILY CALORIES GRAPH-----------//
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
