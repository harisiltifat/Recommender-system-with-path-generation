package de.tum.in.aics.thesis.project.services.interfaces;

import java.util.List;
import java.util.Map;

import de.tum.in.aics.thesis.project.models.Place;
import de.tum.in.aics.thesis.project.util.Utilities;

public interface IScoringService {

	Utilities util = new Utilities();
	
	public static int TOP_LIMIT = 5; 

	public Map<String, Map<Place, Float>> scorePlaces(Map< String, List<Place>> sortedPlaces);
	
}
