package com.dc3160.DC3160_Spring_Boot.controller;


import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.dc3160.DC3160_Spring_Boot.beans.*;
import com.dc3160.DC3160_Spring_Boot.DAOs.*;

/**
 * Servlet implementation class updateProfileServlet
 */
@RequestMapping(value = "/updateProfileServlet", produces="text/plain")
@Controller
public class updateProfileServlet{
       

	@PostMapping
	protected ResponseEntity<String> doPost(Model model, @SessionAttribute("user") User user, @SessionAttribute("userPreferences") Preference preference, @RequestParam Map<String, String> formData) throws IOException {
		
		
		//DAOs needed to update preferences
		PreferenceDAO preferenceDAO = new PreferenceDAO();
		UserDAO userDAO = new UserDAO();
		
		String formId = formData.get("form");
		
		//Check which form in profile requested the update
		if(formId.equalsIgnoreCase("profile-form"))
		{
			
			String firstName = formData.get("profile-first-name");
			String lastName = formData.get("profile-last-name");
			
			userDAO.update(firstName, lastName, user.getUserID());
			
			//Add changes to session of user
			user.setFirstName(firstName);
			user.setLastName(lastName);
			
			model.addAttribute("user", user);
			
			//Not used spring request params in here as it would be unreadable in the do post method parameters
			Integer age = Integer.parseInt(formData.get("profile-age"));
			Integer weightStone = Integer.parseInt(formData.get("profile-weight-st"));
			Integer weightPounds = Integer.parseInt(formData.get("profile-weight-lbs"));
			Integer heightFeet = Integer.parseInt(formData.get("profile-height-feet"));
			Integer heightInches = Integer.parseInt(formData.get("profile-height-inch"));
			
			
			preferenceDAO.update(preference.getPreferenceID(), age, weightStone, weightPounds, heightFeet, heightInches, preference.getStepTarget(), preference.getCalorieTarget(), preference.getExerciseTarget());
			
			preference.setAge(age);
			preference.setWeightStone(weightStone);
			preference.setWeightPounds(weightPounds);
			preference.setHeightFeet(heightFeet);
			preference.setHeightInches(heightInches);
			
			model.addAttribute("userPreferences", preference);
		}
		else if(formId.equalsIgnoreCase("profile-target-form"))
		{
			//Update Target goals and reuse what is already in preferences
			Integer stepTarget = Integer.parseInt(formData.get("profile-steps-target"));
			Integer calorieTarget = Integer.parseInt(formData.get("profile-calorie-target"));
			Double exerciseTarget = Double.parseDouble(formData.get("profile-exercise-target"));
			
			preferenceDAO.update(preference.getPreferenceID(), preference.getAge(), preference.getWeightStone(), preference.getWeightPounds(), preference.getHeightFeet(),preference.getHeightInches() , stepTarget, calorieTarget, exerciseTarget);
			
			//Update preference session attribute
			preference.setStepTarget(stepTarget);
			preference.setCalorieTarget(calorieTarget);
			preference.setExerciseTarget(exerciseTarget);
			
			//Update userPreferences session attriubte to reflect db update
			model.addAttribute("userPreferences", preference);
		}
		
		
		String html = "Profile Updated Successfully!";
		
		//Return Profile Update successsfully text
		return new ResponseEntity<String>(html, HttpStatus.OK);
		

	}

}
