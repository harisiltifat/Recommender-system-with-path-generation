package de.tum.in.aics.thesis.project.services.implementations;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import de.tum.in.aics.thesis.project.models.Place;
import de.tum.in.aics.thesis.project.models.UsersPreferences;
import de.tum.in.aics.thesis.project.services.interfaces.IPlacesService;

@Component
public class PlacesServiceImpl implements IPlacesService {

	
	public Map< String, List<Place> > categorize(List<Place> places){
	
		Map< String, List<Place>> categorisedPlaces = new HashMap< String, List<Place> >();
		List<Place> museums = new ArrayList<Place>();
		List<Place> nightlife = new ArrayList<Place>();
		List<Place> food = new ArrayList<Place>();
		List<Place> nature = new ArrayList<Place>();
		List<Place> music = new ArrayList<Place>();
		List<Place> shopping = new ArrayList<Place>();
			
		for (Place place : places) {
			
			  if(place.getTypes().contains("Museum") || place.getTypes().contains("Art Museum") || place.getTypes().contains("History") || 
					  place.getTypes().contains("Historic Site") || place.getTypes().contains("Art Gallery") || place.getTypes().contains("Arts") ||
					  place.getTypes().contains("Gallery")){
				  museums.add(place);
				  categorisedPlaces.put("Museum", museums);
			  }
			  
			  if(place.getTypes().contains("Brewery") || place.getTypes().contains("Pub") || place.getTypes().contains("Bar") || 
					  place.getTypes().contains("Strip Club") || place.getTypes().contains("Nightclub")){	
				  nightlife.add(place);
				  categorisedPlaces.put("Night Life", nightlife);	  
			  }
			  
			  if(place.getTypes().contains("Restaurant") || place.getTypes().contains("cafe") || place.getTypes().contains("Pizza") || 
			  place.getTypes().contains("Coffee Shop") || place.getTypes().contains("Hotel") || place.getTypes().contains("Steakhouse")){
				  food.add(place);
				  categorisedPlaces.put("Food", food);	  
			  }
			  
			  if(place.getTypes().contains("Garden") || place.getTypes().contains("Park")){
				  nature.add(place);
				  categorisedPlaces.put("Nature", nature);
			  }
			  
			  if(place.getTypes().contains("Entertainment") || place.getTypes().contains("Opera House") || place.getTypes().contains("Dance Studio") 
					  || place.getTypes().contains("Multiplex")|| place.getTypes().contains("Theater") || place.getTypes().contains("Music") || 
					  place.getTypes().contains("Concert")){
				  music.add(place);
				  categorisedPlaces.put("Music", music);
			  }
			  
			  if(place.getTypes().contains("City") || place.getTypes().contains("Landmark")|| place.getTypes().contains("Mall")|| 
					  place.getTypes().contains("Plaza") || place.getTypes().contains("Shopping")){
				  shopping.add(place);
				  categorisedPlaces.put("Shopping", shopping);
			  }
		}
		return categorisedPlaces;
	}
	
	@Override
	public Map<String, List<Place>> sortPlacesByName(Map< String, List<Place>> categorisedPlaces) {
		
		Map<String, List<Place>> sortedPlaces = new LinkedHashMap<String, List<Place>>();
		for (Entry<String, List<Place>> entry : categorisedPlaces.entrySet()) {
			List<Place> places  = entry.getValue();
			Collections.sort(places, new Comparator<Place>(){
				public int compare(Place p1, Place p2) {
		            return p1.getName().compareTo(p2.getName());
		        }
			});
			sortedPlaces.put(entry.getKey(), places);
		}
		return sortedPlaces;
	}
	
	public Map<Place, Float> mergeScores(Map<Place, Float> maxCheckinsScoredPlaces, Map<Place, Float> maxLikesScoredPlaces,Map<Place, Float> maxRatingScoredPlaces){		
		
		Map<Place, Float> finalScoredPlaces = new LinkedHashMap<Place, Float>();
		float checkinsScore = 0 , likesScore = 0 , ratingScore = 0 , finalScore;
		
		for(Entry<Place, Float> entry : maxCheckinsScoredPlaces.entrySet()){
			
			checkinsScore = (float) (entry.getValue());
			
			if(maxLikesScoredPlaces.containsKey(entry.getKey()))
				likesScore = (float) (maxLikesScoredPlaces.get(entry.getKey()));
			else
				likesScore = 0;
			
			if(maxRatingScoredPlaces.containsKey(entry.getKey()))
				ratingScore = (float) (maxRatingScoredPlaces.get(entry.getKey()));
			else
				ratingScore = 0;
			
			finalScore = (float) ( (checkinsScore + likesScore + ratingScore)/3.0);
			finalScoredPlaces.put(entry.getKey(), finalScore);
		}
		return finalScoredPlaces;		
	}

	@Override
	public Map<String, Map<Place, Float>> clusterPlaces(Map<Place, Float> finalScoredPlaces) {

		Map<Place, Float> museums = new HashMap<Place, Float>();
		Map<Place, Float> nightlife = new HashMap<Place, Float>();
		Map<Place, Float> food = new HashMap<Place, Float>();
		Map<Place, Float> music = new HashMap<Place, Float>();
		Map<Place, Float> nature = new HashMap<Place, Float>();
		Map<Place, Float> shopping = new HashMap<Place, Float>();
		Map< String , Map<Place, Float> > finalScoredPlacesWithCat = new LinkedHashMap<String, Map<Place, Float>>();
		
		for(Entry<Place, Float> entry : finalScoredPlaces.entrySet()){
			if(entry.getKey().getTypes().contains("Museum") || entry.getKey().getTypes().contains("Art Museum") || entry.getKey().getTypes().contains("History") || entry.getKey().getTypes().contains("Historic Site") || entry.getKey().getTypes().contains("Art Gallery") || entry.getKey().getTypes().contains("Arts") || entry.getKey().getTypes().contains("Gallery")){
				museums.put(entry.getKey(), entry.getValue());
				finalScoredPlacesWithCat.put("Museum", museums);
			}
			if(entry.getKey().getTypes().contains("Brewery") || entry.getKey().getTypes().contains("Pub") || entry.getKey().getTypes().contains("Bar") || entry.getKey().getTypes().contains("Strip Club") || entry.getKey().getTypes().contains("Nightclub")){
				nightlife.put(entry.getKey(), entry.getValue());
				finalScoredPlacesWithCat.put("Night Life", nightlife);
			}
			if(entry.getKey().getTypes().contains("Restaurant") || entry.getKey().getTypes().contains("cafe") || entry.getKey().getTypes().contains("Pizza") || entry.getKey().getTypes().contains("Coffee Shop") || entry.getKey().getTypes().contains("Hotel") || entry.getKey().getTypes().contains("Steakhouse")){
				food.put(entry.getKey(), entry.getValue());
				finalScoredPlacesWithCat.put("Food", food);
			}
			if(entry.getKey().getTypes().contains("Garden") || entry.getKey().getTypes().contains("Park")){
				nature.put(entry.getKey(), entry.getValue());
				finalScoredPlacesWithCat.put("Nature", nature);
			}
			if(entry.getKey().getTypes().contains("Entertainment") || entry.getKey().getTypes().contains("Opera House") || entry.getKey().getTypes().contains("Dance Studio") || entry.getKey().getTypes().contains("Multiplex")|| entry.getKey().getTypes().contains("Theater") || entry.getKey().getTypes().contains("Music") || entry.getKey().getTypes().contains("Concert")){
				music.put(entry.getKey(), entry.getValue());
				finalScoredPlacesWithCat.put("Music", music);
			}
			if(entry.getKey().getTypes().contains("City") || entry.getKey().getTypes().contains("Landmark")|| entry.getKey().getTypes().contains("Mall")|| entry.getKey().getTypes().contains("Plaza") || entry.getKey().getTypes().contains("Shopping")){
				shopping.put(entry.getKey(), entry.getValue());
				finalScoredPlacesWithCat.put("Shopping", shopping);
			}
		}
		return finalScoredPlacesWithCat;
	}
	
	@SuppressWarnings({"unchecked","rawtypes" })
	@Override
	public Map<String, Map<Place, Float>> sortPlacesByScore(Map<String, Map<Place, Float>> finalScoredPlacesWithCat){
		
		Map< String , Map<Place, Float> > sortedScoredPlacesWithCat = new LinkedHashMap<String, Map<Place, Float>>();
		for(Entry<String, Map<Place,Float>> entry : finalScoredPlacesWithCat.entrySet() ){
			Map<Place, Float> places = entry.getValue();
			List list = new LinkedList(places.entrySet());
			Collections.sort(list, new Comparator() {
				public int compare(Object o1, Object o2) {
					return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
				}
			});
			Map sortedMap = new LinkedHashMap();
			for (Iterator it = list.iterator(); it.hasNext();) {
				Map.Entry place = (Map.Entry) it.next();
				sortedMap.put(place.getKey(), place.getValue());
			}
			sortedScoredPlacesWithCat.put(entry.getKey(), sortedMap);
		}
		return sortedScoredPlacesWithCat;		
	}

	@Override
	public Map<String, Map<Place, Float>> scaleByPreferences(Map<String, Map<Place, Float>> finalScoredPlacesWithCat, List<UsersPreferences> currentUserPreferences) {
		
		float score = 0;
		Map< String , Map<Place, Float> > scaledPlacesWithCat = new LinkedHashMap<String, Map<Place, Float>>();
		Map<Place, Float> scaledPlaces = new LinkedHashMap<Place, Float>();
		
		for(Entry<String, Map<Place,Float>> entry : finalScoredPlacesWithCat.entrySet() ){
			
			if(entry.getKey().equalsIgnoreCase("Museum")){
				Map<Place, Float> places = entry.getValue();
				for(Map.Entry<Place, Float> e : places.entrySet()){					
					//score = e.getValue() * currentUserPreferences.get(0).getMuseumPreference();
					score = GetMultiplicativeScore(e.getValue(),currentUserPreferences.get(0).getMuseumPreference());
					scaledPlaces.put(e.getKey(), score);
				}
				scaledPlacesWithCat.put(entry.getKey(), new LinkedHashMap<Place, Float>(scaledPlaces));
			}
			else if(entry.getKey().equalsIgnoreCase("Night life")){
				Map<Place, Float> places = entry.getValue();
				for(Map.Entry<Place, Float> e : places.entrySet()){					
					//score = e.getValue() * currentUserPreferences.get(0).getNightlifePreference();
					score = GetMultiplicativeScore(e.getValue(),currentUserPreferences.get(0).getNightlifePreference());
					scaledPlaces.put(e.getKey(), score);
				}
				scaledPlacesWithCat.put(entry.getKey(), new LinkedHashMap<Place, Float>(scaledPlaces));
			}
			else if(entry.getKey().equalsIgnoreCase("Food")){
				Map<Place, Float> places = entry.getValue();
				for(Map.Entry<Place, Float> e : places.entrySet()){					
					//score = e.getValue() * currentUserPreferences.get(0).getFoodPreference();
					score = GetMultiplicativeScore(e.getValue(),currentUserPreferences.get(0).getFoodPreference());
					scaledPlaces.put(e.getKey(), score);
				}
				scaledPlacesWithCat.put(entry.getKey(), new LinkedHashMap<Place, Float>(scaledPlaces));
			}
			else if(entry.getKey().equalsIgnoreCase("Nature")){
				Map<Place, Float> places = entry.getValue();
				for(Map.Entry<Place, Float> e : places.entrySet()){					
					//score = e.getValue() * currentUserPreferences.get(0).getNaturePreference();
					score = GetMultiplicativeScore(e.getValue(),currentUserPreferences.get(0).getNaturePreference());
					scaledPlaces.put(e.getKey(), score);
				}
				scaledPlacesWithCat.put(entry.getKey(), new LinkedHashMap<Place, Float>(scaledPlaces));
			}
			else if(entry.getKey().equalsIgnoreCase("Music")){
				Map<Place, Float> places = entry.getValue();
				for(Map.Entry<Place, Float> e : places.entrySet()){					
					//score = e.getValue() * currentUserPreferences.get(0).getMusicPreference();
					score = GetMultiplicativeScore(e.getValue(),currentUserPreferences.get(0).getMusicPreference());
					scaledPlaces.put(e.getKey(), score);
				}
				scaledPlacesWithCat.put(entry.getKey(), new LinkedHashMap<Place, Float>(scaledPlaces));
			}
			else if(entry.getKey().equalsIgnoreCase("Shopping")){
				Map<Place, Float> places = entry.getValue();
				for(Map.Entry<Place, Float> e : places.entrySet()){					
					//score = e.getValue() * currentUserPreferences.get(0).getShoppingPreference();
					score = GetMultiplicativeScore(e.getValue(),currentUserPreferences.get(0).getShoppingPreference());
					scaledPlaces.put(e.getKey(), score);
				}
				scaledPlacesWithCat.put(entry.getKey(), new LinkedHashMap<Place, Float>(scaledPlaces));
			}
			scaledPlaces.clear();
			score = 0;
		}
		return scaledPlacesWithCat;
	}
	
	private float GetMultiplicativeScore(float score,int preferences){
	
		switch(preferences){
		case(5):
			score=score+(score*0.24f);
		break;
		case(4):
			score=score+(score*0.18f);
		break;
		case(3):
			score=score+(score*0.12f);
		break;
		case(2):
			score=score+(score*0.06f);
		break;
		case(1):
			score=score+(score*0.01f);
		break;
		default:
			score=0f;
		break;
		}

		return score;
	}
}


