package de.tum.in.aics.thesis.project.services.implementations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.brickred.socialauth.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tum.in.aics.thesis.project.comparators.PreferencesComparator;
import de.tum.in.aics.thesis.project.daos.interfaces.UserDao;
import de.tum.in.aics.thesis.project.models.User;
import de.tum.in.aics.thesis.project.services.interfaces.IPlacesService;
import de.tum.in.aics.thesis.project.services.interfaces.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private IPlacesService placesService;

	

	@Override
	public Map<String, Integer> sortPreferences(final Map<String, Integer> preferences) {
		PreferencesComparator comparator = new PreferencesComparator(preferences);
		Map<String, Integer> sortedPreferences = new TreeMap<String, Integer>(comparator);
		sortedPreferences.putAll(preferences);
		return sortedPreferences;
	}

	@Override
	public Map<String, Integer> setUserPreferences(HttpServletRequest request) {
		
		Map< String, Integer> userPreferences = new HashMap< String, Integer>();
		userPreferences.put("Museum", Integer.parseInt(request.getParameter("museum")));
		userPreferences.put("Night Life", Integer.parseInt(request.getParameter("nightlife")));
		userPreferences.put("Food", Integer.parseInt(request.getParameter("food")));
		userPreferences.put("Nature", Integer.parseInt(request.getParameter("nature")));
		userPreferences.put("Music", Integer.parseInt(request.getParameter("music")));
		userPreferences.put("Shopping", Integer.parseInt(request.getParameter("shopping")));
		
		return userPreferences;
	}

	@Override
	public User getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public List<User> findUser(long userId) {
		return userDao.findUser(userId);
	}

	@Override
	public User setUser(Profile userProfile) {
		User user = new User();
		user.setUserId(Long.parseLong(userProfile.getValidatedId()));
		user.setEmail(userProfile.getEmail());
		user.setFirstName(userProfile.getFirstName());
		user.setLastName(userProfile.getLastName());
		user.setLocation(userProfile.getLocation());
		return user;
	}
}
