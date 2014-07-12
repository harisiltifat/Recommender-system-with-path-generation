package de.tum.in.aics.thesis.project.services.interfaces;

import java.util.List;

import de.tum.in.aics.thesis.project.models.UsersPreferences;


public interface IPreferencesService {
	void savePreferences(UsersPreferences preferences);
	List<UsersPreferences>  getCurrentPrefernces(int locationId);
}
