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
	
	@Override
	public Map< String , Map<Place, Float> > scorePlaces(Map<String, List<Place>> placesByMaxLikes) {
		
		float totalLikesOfCat = 0;
		float score = 0F;
		Map<Place, Float> placesWithScore = new LinkedHashMap<Place, Float>();
		Map< String , Map<Place, Float> > scoredPlacesWithCat = new LinkedHashMap<String, Map<Place, Float>>();

		for (Entry<String, List<Place>> entry : placesByMaxLikes.entrySet()) {
			
			List<Place> places  = entry.getValue();
			
			for (Place p : places){
				//if(placesCounter < TOP_LIMIT){
					totalLikesOfCat = totalLikesOfCat + p.getLikes();
					/*placesCounter++;
				}else {
					placesCounter = 0;
					break;
				}*/
			}
			
			for(Place p : places){
				//if(scoringCounter < TOP_LIMIT){
					score = util.round(( p.getLikes() / totalLikesOfCat) , DECIMAL_PLACE);
					placesWithScore.put(p, score);
					/*scoringCounter++;
				}else{
					scoringCounter = 0;
					break;
				}*/
			}
			scoredPlacesWithCat.put(entry.getKey(), new LinkedHashMap<Place, Float>(placesWithScore));
			placesWithScore.clear();
			totalLikesOfCat = 0;
			//placesCounter = 0;
			//scoringCounter = 0;
		}
		return scoredPlacesWithCat;
	}
}
