package com.dc3160.DC3160_Spring_Boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LandingPage {
	@RequestMapping("/")
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView("mainPage");
		return model;
	}
}
