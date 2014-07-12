package de.tum.in.aics.thesis.project.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import de.tum.in.aics.thesis.project.models.User;
import de.tum.in.aics.thesis.project.models.UsersPreferences;
import de.tum.in.aics.thesis.project.services.interfaces.IPreferencesService;

@Controller
public class PreferencesController {
	
	@Autowired
	private IPreferencesService preferencesService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/preferences")
	public ModelAndView getLocation() {
		ModelAndView model = new ModelAndView("preferences");
		return model; 
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/savepreferences")
    public String saveUserLocations(HttpServletRequest request) {
		
		int museumPreference = 0, nightLifePreference = 0, foodPreference = 0, naturePreference = 0, musicPreference = 0, shoppingPreference = 0;
		
		if(request.getParameter("museum") != null && !request.getParameter("museum").isEmpty())
			museumPreference = Integer.parseInt(request.getParameter("museum"));
		else 
			museumPreference = 0;
		
		if(request.getParameter("nightlife") != null && !request.getParameter("nightlife").isEmpty())
			nightLifePreference = Integer.parseInt(request.getParameter("nightlife"));
		else
			nightLifePreference = 0;
		
		if(request.getParameter("food") != null && !request.getParameter("food").isEmpty())
			foodPreference = Integer.parseInt(request.getParameter("food"));
		else
			foodPreference = 0;
		
		if(request.getParameter("nature") != null && !request.getParameter("nature").isEmpty())
			naturePreference = Integer.parseInt(request.getParameter("nature"));
		else
			naturePreference = 0;
		
		if(request.getParameter("music") != null && !request.getParameter("music").isEmpty())
			musicPreference = Integer.parseInt(request.getParameter("music"));
		else
			musicPreference = 0;
		
		if(request.getParameter("shopping") != null && !request.getParameter("shopping").isEmpty())
			shoppingPreference = Integer.parseInt(request.getParameter("shopping"));
		else
			shoppingPreference = 0;
		
		UsersPreferences userPreferences = new UsersPreferences();
		userPreferences.setMuseumPreference(museumPreference);
		userPreferences.setNightlifePreference(nightLifePreference);
		userPreferences.setFoodPreference(foodPreference);
		userPreferences.setNaturePreference(naturePreference);
		userPreferences.setMusicPreference(musicPreference);
		userPreferences.setShoppingPreference(shoppingPreference);
		userPreferences.setUser((User) WebUtils.getSessionAttribute(request, "user"));
		userPreferences.setLocationId((Integer) WebUtils.getSessionAttribute(request, "locationId"));
		preferencesService.savePreferences(userPreferences);
		return "redirect:/places"; 
	}
}
