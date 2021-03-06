package de.tum.in.aics.thesis.project.daos.interfaces;

import java.util.List;

import de.tum.in.aics.thesis.project.models.UsersLocation;
import de.tum.in.aics.thesis.project.models.UsersPreferences;

public interface PreferencesDao {
	
	public void savePreferences(UsersPreferences preferences);
	List<UsersPreferences>  getCurrentPrefernces(UsersLocation location);

}
