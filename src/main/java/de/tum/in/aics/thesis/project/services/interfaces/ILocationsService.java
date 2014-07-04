package de.tum.in.aics.thesis.project.services.interfaces;

import java.util.List;

import de.tum.in.aics.thesis.project.models.UsersLocation;


public interface ILocationsService {

	int saveLocaion(UsersLocation location);
	
	List<UsersLocation> findCurrentLocation(int locationId);

}
