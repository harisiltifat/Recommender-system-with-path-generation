package de.tum.in.aics.thesis.project.services.implementations;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import de.tum.in.aics.thesis.project.models.Place;
import de.tum.in.aics.thesis.project.services.interfaces.IScoringService;

@Component
public class ScoringByMaxRatingImpl implements IScoringService {
	
	private static final int NORMALIZING_FACTOR = 10;
	@Override
	public Map< String , Map<Place, Float> > scorePlaces(Map<String, List<Place>> placesByMaxRating) {
		
		float totalRatingOfCat = 0F;
		int totalVotesOfCat = 0;
		float score = 0F;  
		int totalPlaces = 0;
		float averageRatingOfCategory = 0F;
		float averageVotesOfCateogory = 0F;
		Map<Place, Float> placesWithScore = new LinkedHashMap<Place, Float>();
		Map< String , Map<Place, Float> > scoredPlacesWithCat = new LinkedHashMap<String, Map<Place, Float>>();
		
		for (Entry<String, List<Place>> entry : placesByMaxRating.entrySet()) {
			List<Place> places  = entry.getValue();			
			if(places.size() != 0 && !places.isEmpty()){
				totalPlaces = places.size();
				for (Place p : places){
					totalRatingOfCat = totalRatingOfCat + p.getRating();
					totalVotesOfCat = totalVotesOfCat + p.getRatingVotes();
				}
				averageRatingOfCategory = totalRatingOfCat / totalPlaces;
				averageVotesOfCateogory = totalVotesOfCat / totalPlaces;		
				for(Place p : places){
					if(p.getRating() != 0 && p.getRatingVotes() != null && p.getRatingVotes() != 0){
						float rating = p.getRating();
						int votesOfplace = p.getRatingVotes();
						score = ((votesOfplace * rating) + (averageVotesOfCateogory * averageRatingOfCategory)) / (votesOfplace + averageVotesOfCateogory);
						score = (score / NORMALIZING_FACTOR);
						placesWithScore.put(p, score);
					}
					else
					{
						score = 0;
						placesWithScore.put(p, score);
					}
				}
				scoredPlacesWithCat.put(entry.getKey(), new LinkedHashMap<Place, Float>(placesWithScore));
			}
			placesWithScore.clear();
			totalRatingOfCat = 0F;
			totalVotesOfCat = 0;
			score = 0F;
			totalPlaces = 0;
			averageRatingOfCategory = 0F;
			averageVotesOfCateogory = 0F;
		}
		return scoredPlacesWithCat;
	}
}
