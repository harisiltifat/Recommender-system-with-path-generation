package de.tum.in.aics.thesis.project.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/userpreferences")
	public ModelAndView getPreferences(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("preferences");
		return model; 
	}
}
