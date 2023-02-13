package com.dc3160.DC3160_Spring_Boot.controller;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dc3160.DC3160_Spring_Boot.Service.MealService;
import com.dc3160.DC3160_Spring_Boot.beans.MealRecord;
import com.dc3160.DC3160_Spring_Boot.beans.User;



@RequestMapping("Diet")
@SessionAttributes({"user", "userPreferences"})
@Controller    
public class DietPage {
		@Autowired
		private MealService mealService;

		@GetMapping
		protected ModelAndView doGet(Model model ,@SessionAttribute("user") User user){
			
			List<MealRecord> records = mealService.getMealsByUser(user.getUserID());
			
			ModelAndView modelAndView = new ModelAndView("Diet.html");
			
			model.addAttribute("dietRecords", records);
			
			return modelAndView;
		}
		
		@PostMapping
		protected RedirectView doPost(@SessionAttribute("user") User user, @RequestParam("diet-meal-name") String mealName, @RequestParam("diet-meal-calories") int mealCalories, @RequestParam("diet-meal-date") String mealDate) {
			
			//Convert date to actual date
			Date dateAsDate = Date.valueOf(mealDate);
			
			//Get userId
			int userID = user.getUserID();
			
			MealRecord record = new MealRecord();
			
			record.setUserID(userID);
			record.setMealName(mealName);
			record.setMealCalories(mealCalories);
			record.setMealDate(dateAsDate);
			
			mealService.addMeal(record);
			
			return new RedirectView("Diet");
		}
	
}
