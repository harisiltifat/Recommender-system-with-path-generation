package de.tum.in.aics.thesis.project.services.implementations;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import de.tum.in.aics.thesis.project.models.Place;
import de.tum.in.aics.thesis.project.services.interfaces.IScoringService;

@Component
public class ScoringByMaxCheckinsImpl implements IScoringService {

	private final int maxScore = 1;

	@Override
	public Map<String, Map<Place, Float>> scorePlaces(Map<String, List<Place>> placesByMaxCheckins) {

		int placeWithMaxCheckins = 0;
		float score = 0F;
		float distanceFromMax = 0F;

		Map<Place, Float> placesWithScore = new LinkedHashMap<Place, Float>();
		Map<String, Map<Place, Float>> scoredPlacesWithCat = new LinkedHashMap<String, Map<Place, Float>>();

		for (Entry<String, List<Place>> entry : placesByMaxCheckins.entrySet()) {
			List<Place> places = entry.getValue();
			if(places.size() != 0 && !places.isEmpty()){
				for (Place p : places) {
					if (p.getStats() != null && p.getStats() != 0 && p.getStats() >= placeWithMaxCheckins)
						placeWithMaxCheckins = p.getStats();
				}
				for (Place p : places) {
					if(p.getStats() != null && p.getStats() != 0 ){
						if (p.getStats() == placeWithMaxCheckins) {
							score = maxScore;
							placesWithScore.put(p, score);
						} else {
							distanceFromMax = (placeWithMaxCheckins - p.getStats());
							score = (distanceFromMax / placeWithMaxCheckins);
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
			placeWithMaxCheckins = 0;
			score = 0F;
			distanceFromMax = 0F;
		}
		return scoredPlacesWithCat;
	}

}
