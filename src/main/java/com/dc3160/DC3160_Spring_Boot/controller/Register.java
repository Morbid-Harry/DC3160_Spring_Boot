package com.dc3160.DC3160_Spring_Boot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dc3160.DC3160_Spring_Boot.Service.PreferenceService;
import com.dc3160.DC3160_Spring_Boot.Service.UserService;
import com.dc3160.DC3160_Spring_Boot.beans.*;
/**
 * Servlet implementation class registerServlet
 */
@RequestMapping("Register")
@Controller
@SessionAttributes({"user", "userPreferences"})
public class Register {
	@Autowired
	UserService userService;
	@Autowired
	PreferenceService preferenceService;

	@ModelAttribute("user")
	public User setSession() {
		return new User();
	}
	
	@ModelAttribute("userPreferences")
	public Preference getPreference() {
		return new Preference();
	}

	@PostMapping
	protected ModelAndView doPost(Model model, @RequestParam("register-email") String email, @RequestParam("register-first-name") String firstName, @RequestParam("register-last-name") String lastName, @RequestParam("register-password") String password ){
		
		//Validation has already been handled via JS and bootstrap
			
			User newUser = new User();
		
			//Add form data to potential new user object
			newUser.setEmail(email);
			newUser.setFirstName(firstName);
			newUser.setLastName(lastName);
			newUser.setPassword(password);
			
			//Check if the user does not already exist by email
			if(userService.getUserByEmail(email) == null)
			{
				userService.addUser(newUser);
				
				User createdUser = userService.getUserByEmailAndPassword(email, password);
				
				Preference preference = new Preference();
				
				//Set up their initial default preferences
				preference.setUserID(createdUser.getUserID());
				preference.setAge(18);
				preference.setWeightStone(10);
				preference.setWeightPounds(6);
				preference.setHeightFeet(5);
				preference.setHeightInches(7);
				preference.setStepTarget(10000);
				preference.setCalorieTarget(2000);
				preference.setExerciseTarget(0);
				
				
				preferenceService.addPreference(preference);
				
				model.addAttribute("user", createdUser);
				model.addAttribute("userPreferences", preference);
				
				//redirect to dashboard
				return new ModelAndView("redirect:/Dashboard");
			}
			else {
				//Error message to send back
				String failed = "Email already in use! Please try logging in!";
				//redirect back to main page with message
				ModelAndView modelAndView = new ModelAndView("mainPage.html");
				modelAndView.addObject("exists", failed);
				//send back to main page with exists message
				return modelAndView;
			}
			
	}

}
