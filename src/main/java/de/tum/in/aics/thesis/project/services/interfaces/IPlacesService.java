package de.tum.in.aics.thesis.project.services.interfaces;

import java.util.List;
import java.util.Map;

import de.tum.in.aics.thesis.project.models.Place;
import de.tum.in.aics.thesis.project.models.UsersPreferences;

public interface IPlacesService {

	public Map< String, List<Place>> categorize(List<Place> places);
	public Map<String, List<Place>> sortPlacesByName(Map<String, List<Place>> categorisedPlaces);
	public Map<Place, Float> mergeScores(Map<Place, Float> maxCheckinsScoredPlaces, Map<Place, Float> maxLikesScoredPlaces,Map<Place, Float> maxRatingScoredPlaces);
	public Map< String , Map<Place, Float> > clusterPlaces(Map<Place, Float> finalScoredPlaces);
	public Map<String, Map<Place, Float>> sortPlacesByScore(Map<String, Map<Place, Float>> finalScoredPlacesWithCat);
	public Map<String, Map<Place, Float>> scaleByPreferences(Map<String, Map<Place, Float>> finalScoredPlacesWithCat, List<UsersPreferences> currentUserPreferences);
}