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
	
	private static final int SCALING_FACTOR = 2;
	
	@Autowired
	private IPreferencesService preferencesService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/preferences")
	public ModelAndView getLocation() {
		ModelAndView model = new ModelAndView("preferences");
		return model; 
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/savepreferences")
    public String saveUserLocations(HttpServletRequest request) {
		
		int museumPreference = Integer.parseInt(request.getParameter("museum")) * SCALING_FACTOR;
		int nightLifePreference = Integer.parseInt(request.getParameter("nightlife")) * SCALING_FACTOR;
		int foodPreference = Integer.parseInt(request.getParameter("food")) * SCALING_FACTOR;
		int naturePreference = Integer.parseInt(request.getParameter("nature")) * SCALING_FACTOR;
		int musicPreference = Integer.parseInt(request.getParameter("music")) * SCALING_FACTOR;
		int shoppingPreference = Integer.parseInt(request.getParameter("shopping")) * SCALING_FACTOR;
		
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
