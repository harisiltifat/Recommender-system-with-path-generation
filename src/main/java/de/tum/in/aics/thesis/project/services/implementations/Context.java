package de.tum.in.aics.thesis.project.services.implementations;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.tum.in.aics.thesis.project.models.Place;
import de.tum.in.aics.thesis.project.services.interfaces.IScoringService;

public class Context {

	private IScoringService scoringService;
	
	Map< String , Map<Place, Float> > scoredPlaces = new LinkedHashMap<String, Map<Place, Float>>();
	
	
	public Context(IScoringService scoringService){
		this.scoringService = scoringService;
	}
	
	public Map< String , Map<Place, Float> > executeStrategy(Map<String, List<Place>> sortedCategorisedPlaces){
		scoredPlaces = scoringService.scorePlaces(sortedCategorisedPlaces);
		return scoredPlaces;
	}
}
