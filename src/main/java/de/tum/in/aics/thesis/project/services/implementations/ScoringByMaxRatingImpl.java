package de.tum.in.aics.thesis.project.services.implementations;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import de.tum.in.aics.thesis.project.models.Place;
import de.tum.in.aics.thesis.project.services.interfaces.IScoringService;
import de.tum.in.aics.thesis.project.util.Utilities;

@Component
public class ScoringByMaxRatingImpl implements IScoringService {
	
	private static final int NORMALIZING_FACTOR = 10;
	@Override
	public Map< String , Map<Place, Float> > scorePlaces(Map<String, List<Place>> placesByMaxRating) {
		
		float totalRatingOfCat = 0;
		int totalVotesOfCat = 0;
		float score = 0;  
		int totalPlaces = 0;
		float averageRatingOfCategory = 0;
		float averageVotesOfCateogory = 0;
		Map<Place, Float> placesWithScore = new LinkedHashMap<Place, Float>();
		Map< String , Map<Place, Float> > scoredPlacesWithCat = new LinkedHashMap<String, Map<Place, Float>>();
		
		for (Entry<String, List<Place>> entry : placesByMaxRating.entrySet()) {
			List<Place> places  = entry.getValue();
			totalPlaces = places.size();
			for (Place p : places){
				totalRatingOfCat = totalRatingOfCat + p.getRating();
				totalVotesOfCat = totalVotesOfCat + p.getRatingVotes();
			}
			averageRatingOfCategory = totalRatingOfCat / totalPlaces;
			averageVotesOfCateogory = totalVotesOfCat / totalPlaces;
			for(Place p : places){
				float rating = p.getRating();
				int votesOfplace = p.getRatingVotes();
				score = ((votesOfplace * rating) + (averageVotesOfCateogory * averageRatingOfCategory)) / (votesOfplace + averageVotesOfCateogory);
				score = Utilities.round((score / NORMALIZING_FACTOR), DECIMAL_PLACE);
				placesWithScore.put(p, score);
			}
			scoredPlacesWithCat.put(entry.getKey(), new LinkedHashMap<Place, Float>(placesWithScore));
			placesWithScore.clear();
			totalRatingOfCat = 0;
			totalVotesOfCat = 0;
			score = 0;
			totalPlaces = 0;
			averageRatingOfCategory = 0;
			averageVotesOfCateogory = 0;
		}
		return scoredPlacesWithCat;
	}
}
