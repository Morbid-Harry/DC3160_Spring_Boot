package com.dc3160.DC3160_Spring_Boot.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;



@RequestMapping("Dashboard")
@SessionAttributes({"user", "userPreferences"})
@Controller    
public class Dashboard {
	

		@GetMapping
		protected String doGet(){
			//Ends the session and destroys all session attributes
			return "dashboard.html";
		}
	
}
