package de.tum.in.aics.thesis.project.services.implementations;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import de.tum.in.aics.thesis.project.models.Place;
import de.tum.in.aics.thesis.project.services.interfaces.IFilteringByOpeningTimesService;

@Component
public class FilteringByOpeningTimesServiceImpl implements IFilteringByOpeningTimesService {
	
	@Override
	public Map<String, List<Place>> filterPlaces(Map<String, List<Place>> categorisedPlaces) {

		Map<String, List<Place>> filteredPlaces = new LinkedHashMap<String, List<Place>>();
		List<Place> newPlaces = new ArrayList<Place>();
		for(Entry<String, List<Place>> entry : categorisedPlaces.entrySet()) {		
			List<Place> places  = entry.getValue();
			for (Place p : places) {
				if(p.getOpenNow() == true)
					newPlaces.add(p);				
			}
			filteredPlaces.put(entry.getKey(), new ArrayList<Place> (newPlaces));
			newPlaces.clear();
		}
		return filteredPlaces;
	}	
}
