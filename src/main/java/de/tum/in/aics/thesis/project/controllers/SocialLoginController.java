package de.tum.in.aics.thesis.project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.spring.bean.SocialAuthTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import de.tum.in.aics.thesis.project.models.User;
import de.tum.in.aics.thesis.project.services.interfaces.IUserService;

@Controller
public class SocialLoginController {

        @Autowired
        private SocialAuthTemplate socialAuthTemplate;

        @Autowired
    	private IUserService userService; 
        
	@RequestMapping(value = "/authSuccess")
	public String authenticateUser(final HttpServletRequest request)throws Exception {

		SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
		AuthProvider provider = manager.getCurrentAuthProvider();
		Profile userProfile = provider.getUserProfile();
		User user = userService.setUser(userProfile);
		long userId = user.getUserId();
		List<User> userCount = userService.findUser(userId);

		if(userCount.isEmpty()){
			userService.saveUser(user);
			WebUtils.setSessionAttribute(request, "user", user);
		}
		else
			WebUtils.setSessionAttribute(request, "user", user);

		return "redirect:/location";
	}
}
