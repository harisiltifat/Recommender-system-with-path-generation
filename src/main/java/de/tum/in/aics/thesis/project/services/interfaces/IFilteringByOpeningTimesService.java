package de.tum.in.aics.thesis.project.services.interfaces;

import java.util.List;
import java.util.Map;

import de.tum.in.aics.thesis.project.models.Place;

public interface IFilteringByOpeningTimesService {

	public Map<String, List<Place>> filterPlaces(Map<String, List<Place>> categorisedPlaces);
}
