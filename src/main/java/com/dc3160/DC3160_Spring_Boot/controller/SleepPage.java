package com.dc3160.DC3160_Spring_Boot.controller;


import java.sql.Date;
import java.util.ArrayList;



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

import com.dc3160.DC3160_Spring_Boot.DAOs.*;
import com.dc3160.DC3160_Spring_Boot.beans.*;

@RequestMapping("Sleep")
@SessionAttributes("user")
@Controller
public class SleepPage {
	
	@PostMapping
	protected RedirectView doPost(@SessionAttribute("user") User user, @RequestParam("sleep-taken-hours") String sleepHours, @RequestParam("sleep-date") String sleepDate)
	{
		//Convert hours and date to correct values
		Double hoursAsDouble = Double.parseDouble(sleepHours);
		//Convert date string to actual date
		Date dateAsDate = Date.valueOf(sleepDate);
		
		int userID = user.getUserID();
		
		//SleepDAO to insert data
		SleepDAO dao = new SleepDAO();
		
		dao.insert(userID, hoursAsDouble, dateAsDate);
			
		return new RedirectView("Sleep");
	}
	
	@GetMapping
	protected ModelAndView doGet(Model model, @SessionAttribute("user") User user )
	{
		//SleepDAO to get data
		SleepDAO dao = new SleepDAO();
		
		int userID = user.getUserID();
				
		ArrayList<SleepRecord> records = dao.getUserSleepRecords(userID);
		
		ModelAndView modelAndView = new ModelAndView("Sleep.html");
		
		model.addAttribute("sleepRecords", records);
			
		return modelAndView;
	}
}
