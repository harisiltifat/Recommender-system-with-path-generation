package de.tum.in.aics.thesis.project.services.implementations;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import de.tum.in.aics.thesis.project.models.Place;
import de.tum.in.aics.thesis.project.services.interfaces.IScoringService;

@Component
public class ScoringByMaxLikesImpl implements IScoringService {
	
	private final int maxScore = 1;
	
	@Override
	public Map< String , Map<Place, Float> > scorePlaces(Map<String, List<Place>> placesByMaxLikes) {
		
		int placeWithMaxLikes = 0;
		float score = 0F;
		float distanceFromMax = 0F;

		Map<Place, Float> placesWithScore = new LinkedHashMap<Place, Float>();
		Map<String, Map<Place, Float>> scoredPlacesWithCat = new LinkedHashMap<String, Map<Place, Float>>();

		for (Entry<String, List<Place>> entry : placesByMaxLikes.entrySet()) {
			List<Place> places = entry.getValue();
			if(places.size() != 0 && !places.isEmpty()){
				for (Place p : places) {
					if (p.getLikes() != null && p.getLikes() != 0 && p.getLikes() >= placeWithMaxLikes)
						placeWithMaxLikes = p.getLikes();
				}
	
				for (Place p : places) {
					if(p.getLikes() != null && p.getLikes() != 0){
						if (p.getStats() == placeWithMaxLikes) {
							score = maxScore;
							placesWithScore.put(p, score);
						} else {
							distanceFromMax = (placeWithMaxLikes - p.getLikes());
							score = (distanceFromMax / placeWithMaxLikes);
							score = (maxScore - score);
							placesWithScore.put(p, score);
						}
					}
					else{
						score = 0;
						placesWithScore.put(p, score);
					}
				}
				scoredPlacesWithCat.put(entry.getKey(),new LinkedHashMap<Place, Float>(placesWithScore));
			}
			placesWithScore.clear();
			placeWithMaxLikes = 0;
			score = 0F;
			distanceFromMax = 0F;
		}
		return scoredPlacesWithCat;
	}
}
