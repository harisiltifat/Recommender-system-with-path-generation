package de.tum.in.aics.thesis.project.services.interfaces;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.brickred.socialauth.Profile;

import de.tum.in.aics.thesis.project.models.User;


public interface IUserService {

	public Map< String, Integer> setUserPreferences(HttpServletRequest request);
	public Map< String, Integer> sortPreferences(Map< String, Integer> preferences);
	public User getUserById(Integer userId);
	public List<User> findUser(long socialId);
	public User setUser(Profile userProfile);
	void saveUser(User user);
}
