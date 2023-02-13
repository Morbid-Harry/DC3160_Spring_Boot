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

import com.dc3160.DC3160_Spring_Boot.Service.ExerciseService;
import com.dc3160.DC3160_Spring_Boot.Service.StepService;
import com.dc3160.DC3160_Spring_Boot.beans.ExerciseRecord;
import com.dc3160.DC3160_Spring_Boot.beans.StepRecord;
import com.dc3160.DC3160_Spring_Boot.beans.User;



@RequestMapping("Exercise")
@SessionAttributes({"user", "userPreferences"})
@Controller    
public class ExercisePage {
		@Autowired
		private ExerciseService exerciseService;
		
		@Autowired
		private StepService stepService;

		@GetMapping
		protected ModelAndView doGet(Model model ,@SessionAttribute("user") User user){
			
			//Get all exercise records of user
			List<ExerciseRecord> exerciseRecords = exerciseService.getExerciseByUser(user.getUserID());
			//Get all step records of user
			List<StepRecord> stepRecords = stepService.getStepByUser(user.getUserID());
			
			ModelAndView modelAndView = new ModelAndView("Exercise.html");
			
			model.addAttribute("exerciseRecords", exerciseRecords);
			model.addAttribute("stepRecords", stepRecords);
			
			return modelAndView;
		}
	
}
