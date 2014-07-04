package de.tum.in.aics.thesis.project.services.implementations;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import de.tum.in.aics.thesis.project.models.Place;
import de.tum.in.aics.thesis.project.services.interfaces.IScoringService;

@Component
public class ScoringByOpeningTimeImpl implements IScoringService {
	
	@Override
	public Map< String , Map<Place, Float> > scorePlaces(Map<String, List<Place>> sortedCategorisedPlaces) {

		float score;
		Map<Place, Float> placesWithScore = new LinkedHashMap<Place, Float>();
		Map< String , Map<Place, Float> > scoredPlacesWithCat = new LinkedHashMap<String, Map<Place, Float>>();

		for (Entry<String, List<Place>> entry : sortedCategorisedPlaces.entrySet()) {
			
			List<Place> places  = entry.getValue();

			for (Place p : places) {
				if(p.getOpenNow())
					score = (float) 1.0;
				else
					score  = (float) 0.0;
				placesWithScore.put(p, score);
			}
	
			scoredPlacesWithCat.put(entry.getKey(), new LinkedHashMap<Place, Float>(placesWithScore));
			placesWithScore.clear();

		}
		return scoredPlacesWithCat;
	}	
}
