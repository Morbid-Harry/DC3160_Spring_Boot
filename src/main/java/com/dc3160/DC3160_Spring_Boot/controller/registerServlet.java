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

import com.dc3160.DC3160_Spring_Boot.DAOs.*;
import com.dc3160.DC3160_Spring_Boot.beans.*;
/**
 * Servlet implementation class registerServlet
 */
@RequestMapping("/registerServlet")
@Controller
@SessionAttributes({"user", "userPreferences"})
public class registerServlet {
    
	private UserDAO userDAO;
	private PreferenceDAO preferenceDAO;

	@ModelAttribute("user")
	public User setSession() {
		return new User();
	}
	
	@ModelAttribute("userPreferences")
	public Preference getPreference() {
		return new Preference();
	}

	@PostMapping
	protected void doPost(Model model, @ModelAttribute("user") User newUser,@ModelAttribute("userPreferences") Preference preference, @RequestParam("register-email") String email, @RequestParam("register-first-name") String firstName, @RequestParam("register-last-name") String lastName, @RequestParam("register-password") String password, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Validation has already been handled via JS and bootstrap
		
			//Use user dao to add new user to db 
			userDAO = new UserDAO();
			//Inserted user is returned to servlet to create bean and stored in session
			newUser = userDAO.insert(firstName, lastName, email, password);
			
			//Check we actually inserted and created a new user
			if(newUser != null)
			{
				//Set up their initial default preferences
				preferenceDAO = new PreferenceDAO();
				
				preference = preferenceDAO.insert(newUser.getUserID(), 18, 10, 6, 5, 7, 10000, 2000, 2);
				
				model.addAttribute("user", newUser);
				model.addAttribute("userPreferences", preference);
				
				
				//Since the user does not exist we can now remove the already exists attribute
				request.removeAttribute("exists");
				
				//Forward to dashboard
				response.sendRedirect("./jsp/dashboard.jsp");
				

			}
			else {
				//Error message to send back
				String failed = "Email already in use! Please try logging in!";
				//add to session
				request.setAttribute("exists", failed);
				//send back to main page with exists message
				request.getRequestDispatcher("./jsp/mainPage.jsp").forward(request, response);
			}
			



				
		
	}

}
