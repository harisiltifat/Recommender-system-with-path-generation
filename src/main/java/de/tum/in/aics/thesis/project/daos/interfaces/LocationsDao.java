package de.tum.in.aics.thesis.project.daos.interfaces;

import java.util.List;

import de.tum.in.aics.thesis.project.models.UsersLocation;

public interface LocationsDao {
	
	public int saveLocation(UsersLocation location);
	List<UsersLocation>  findCurrentLocation(int locationId);

}
