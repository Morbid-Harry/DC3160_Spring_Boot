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

import com.dc3160.DC3160_Spring_Boot.beans.Preference;
import com.dc3160.DC3160_Spring_Boot.beans.User;
import com.dc3160.DC3160_Spring_Boot.Service.PreferenceService;
import com.dc3160.DC3160_Spring_Boot.Service.UserService;


/**
 * Spring boot implementation class login
 */
@RequestMapping("/loginServlet")
@Controller
@SessionAttributes({"user", "userPreferences"})
public class loginServlet{
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
	protected ModelAndView doPost(Model model, @ModelAttribute("user") User user,@ModelAttribute("userPreferences") Preference preference, @RequestParam("login-email") String email, @RequestParam("login-password") String password) {
		
		user = userService.getUserByEmailAndPassword(email, password);
			
		if(user != null)
		{
			//Get the users preferences
			preference = preferenceService.getPreferenceByUser(user.getUserID());
			//Set the user from query as user session
			model.addAttribute("user", user);
			model.addAttribute("userPreferences", preference);
			
			//Redirect to dashboard
			return new ModelAndView("redirect:/Dashboard");
			
		}
		else {
			
			String errorMessage = "Invalid username or password";
			//redirect back to the page now message will display
			ModelAndView modelAndView = new ModelAndView("mainPage.html");
        	modelAndView.addObject("error", errorMessage);
        	return modelAndView;
		}
		
	}

}
