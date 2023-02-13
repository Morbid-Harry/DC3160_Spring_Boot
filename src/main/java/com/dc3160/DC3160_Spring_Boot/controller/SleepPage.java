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

import com.dc3160.DC3160_Spring_Boot.Service.SleepService;
import com.dc3160.DC3160_Spring_Boot.beans.*;

@RequestMapping("Sleep")
@SessionAttributes("user")
@Controller
public class SleepPage {
	@Autowired
	private SleepService sleepService;
	
	@PostMapping
	protected RedirectView doPost(@SessionAttribute("user") User user, @RequestParam("sleep-taken-hours") double sleepHours, @RequestParam("sleep-date") String sleepDate)
	{
		//Convert date string to actual date
		Date dateAsDate = Date.valueOf(sleepDate);

		SleepRecord record = new SleepRecord();
		
		record.setUserID(user.getUserID());
		record.setSleepHours(sleepHours);
		record.setSleepDate(dateAsDate);
		
		sleepService.addSleep(record);
			
		return new RedirectView("Sleep");
	}
	
	@GetMapping
	protected ModelAndView doGet(Model model, @SessionAttribute("user") User user )
	{
				
		List<SleepRecord> records = sleepService.getSleepByUser(user.getUserID());
		
		ModelAndView modelAndView = new ModelAndView("Sleep.html");
		
		model.addAttribute("sleepRecords", records);
			
		return modelAndView;
	}
}
