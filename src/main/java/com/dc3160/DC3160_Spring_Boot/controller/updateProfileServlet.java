package com.dc3160.DC3160_Spring_Boot.controller;


import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.dc3160.DC3160_Spring_Boot.beans.*;
import com.dc3160.DC3160_Spring_Boot.Service.PreferenceService;
import com.dc3160.DC3160_Spring_Boot.Service.UserService;

/**
 * Servlet implementation class updateProfileServlet
 */
@RequestMapping(value = "/updateProfileServlet", produces="text/plain")
@Controller
public class updateProfileServlet{
	@Autowired
	UserService userService;
	@Autowired
	PreferenceService preferenceService;   

	@PostMapping
	protected ResponseEntity<String> doPost(Model model, @SessionAttribute("user") User user, @SessionAttribute("userPreferences") Preference preference, @RequestParam Map<String, String> formData){
		
		String formId = formData.get("form");
		
		//Check which form in profile requested the update
		if(formId.equalsIgnoreCase("profile-form"))
		{
			
			String firstName = formData.get("profile-first-name");
			String lastName = formData.get("profile-last-name");
			
			//Create new user object to replace one in existing DB
			User updatedUser = new User();
			updatedUser.setUserID(user.getUserID());
			updatedUser.setFirstName(firstName);
			updatedUser.setLastName(lastName);
			updatedUser.setEmail(user.getEmail());
			updatedUser.setPassword(user.getPassword());
			
			//Replace old user record in DB with update one
			userService.addUser(updatedUser);
			
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
			
			//Create new preference object to replace one in existing DB
			Preference updatedPreference = new Preference();
			updatedPreference.setId(preference.getId());
			updatedPreference.setUserID(user.getUserID());
			updatedPreference.setAge(age);
			updatedPreference.setWeightStone(weightStone);
			updatedPreference.setWeightPounds(weightPounds);
			updatedPreference.setHeightFeet(heightFeet);
			updatedPreference.setHeightInches(heightInches);
			updatedPreference.setStepTarget(preference.getStepTarget());
			updatedPreference.setCalorieTarget(preference.getCalorieTarget());
			updatedPreference.setExerciseTarget(preference.getExerciseTarget());
			
			preferenceService.addPreference(updatedPreference);
			
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
			
			//Create new preference object to replace one in existing DB
			Preference updatedPreference = new Preference();
			updatedPreference.setId(preference.getId());
			updatedPreference.setUserID(user.getUserID());
			updatedPreference.setAge(preference.getAge());
			updatedPreference.setWeightStone(preference.getWeightStone());
			updatedPreference.setWeightPounds(preference.getWeightPounds());
			updatedPreference.setHeightFeet(preference.getHeightFeet());
			updatedPreference.setHeightInches(preference.getHeightInches());
			updatedPreference.setStepTarget(stepTarget);
			updatedPreference.setCalorieTarget(calorieTarget);
			updatedPreference.setExerciseTarget(exerciseTarget);
			
			preferenceService.addPreference(updatedPreference);
			
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
