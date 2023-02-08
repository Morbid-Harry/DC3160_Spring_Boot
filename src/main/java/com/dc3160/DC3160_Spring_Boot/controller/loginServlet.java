package com.dc3160.DC3160_Spring_Boot.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.dc3160.DC3160_Spring_Boot.DAOs.*;


/**
 * Spring boot implementation class login
 */
@RequestMapping("/loginServlet")
@Controller
@SessionAttributes({"user", "userPreferences"})
public class loginServlet{
	
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
		
		
		//Dao to get the user
		UserDAO userDAO = new UserDAO();
		
		
		user = userDAO.getUserByEmailAndPassword(email, password);
			
		if(user != null)
		{
			//Get the users preferences
			PreferenceDAO preferenceDAO = new PreferenceDAO();
			
			preference = preferenceDAO.getUserPreferences(user.getUserID());
			
			//Set the user from query as user session
			model.addAttribute("user", user);
			model.addAttribute("userPreferences", preference);
			
			//Forward to dashboard
			return new ModelAndView("dashboard.html");
			
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
