package de.tum.in.aics.thesis.project.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import de.tum.in.aics.thesis.project.models.UsersLocation;
import de.tum.in.aics.thesis.project.services.interfaces.ILocationsService;

@Controller
public class LocationsController {
	
	@Autowired
	private ILocationsService locationService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/location")
	public ModelAndView getLocation(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("location");
		return model; 
	}
		
	@RequestMapping(method = RequestMethod.POST, value = "/savelocation")
    public @ResponseBody String saveUserLocations(HttpServletRequest request, @RequestParam(value = "sourceLat" ) double sourceLat, @RequestParam(value = "sourceLng" ) double sourceLng, @RequestParam(value = "destinationLat" ) double destinationLat, @RequestParam(value = "destinationLng" ) double destinationLng) {
		UsersLocation userLocation = new UsersLocation();
		userLocation.setSourcelat(sourceLat);
		userLocation.setSourcelong(sourceLng);
		userLocation.setDestinationlat(destinationLat);
		userLocation.setDestinationlong(destinationLng);
		int locationId = locationService.saveLocaion(userLocation);	
		WebUtils.setSessionAttribute(request, "locationId", locationId);		
		return "preferences";
	}
}
