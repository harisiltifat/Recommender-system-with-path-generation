package de.tum.in.aics.thesis.project.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tum.in.aics.thesis.project.daos.interfaces.PreferencesDao;
import de.tum.in.aics.thesis.project.models.UsersLocation;
import de.tum.in.aics.thesis.project.models.UsersPreferences;
import de.tum.in.aics.thesis.project.services.interfaces.IPreferencesService;


@Component
public class PreferencesServiceImpl implements IPreferencesService {

	
	@Autowired
	private PreferencesDao preferencesDao;

	@Override
	public void savePreferences(UsersPreferences preferences) {
		preferencesDao.savePreferences(preferences);
	}

	@Override
	public List<UsersPreferences> getCurrentPrefernces(UsersLocation location) {
		return preferencesDao.getCurrentPrefernces(location);
	}
}
