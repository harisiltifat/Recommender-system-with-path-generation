package de.tum.in.aics.thesis.project.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tum.in.aics.thesis.project.daos.interfaces.LocationsDao;
import de.tum.in.aics.thesis.project.models.UsersLocation;
import de.tum.in.aics.thesis.project.services.interfaces.ILocationsService;


@Component
public class LocationsServiceImpl implements ILocationsService {
	
	@Autowired
	private LocationsDao locationsDao;
	
	@Override
	public int saveLocaion(UsersLocation location){
		return locationsDao.saveLocation(location);
	}

	@Override
	public List<UsersLocation> findCurrentLocation(int locationId) {
		return locationsDao.findCurrentLocation(locationId);
	}
}
