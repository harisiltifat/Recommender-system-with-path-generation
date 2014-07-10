package de.tum.in.aics.thesis.project.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import de.tum.in.aics.thesis.project.algorithms.implementations.*;
import de.tum.in.aics.thesis.project.algorithms.interfaces.IPathFindAlgorithm;
import de.tum.in.aics.thesis.project.models.Location;
import de.tum.in.aics.thesis.project.models.Place;
import de.tum.in.aics.thesis.project.models.UsersLocation;
import de.tum.in.aics.thesis.project.services.implementations.Context;
import de.tum.in.aics.thesis.project.services.implementations.ScoringByMaxCheckinsImpl;
import de.tum.in.aics.thesis.project.services.implementations.ScoringByMaxLikesImpl;
import de.tum.in.aics.thesis.project.services.implementations.ScoringByMaxRatingImpl;
import de.tum.in.aics.thesis.project.services.implementations.ScoringByOpeningTimeImpl;
import de.tum.in.aics.thesis.project.services.interfaces.ILocationsService;
import de.tum.in.aics.thesis.project.services.interfaces.IPlacesService;
import de.tum.in.aics.thesis.project.services.interfaces.ISearchService;
import de.tum.in.aics.thesis.project.util.Utilities;

@Controller
public class PlacesController {

	private static double SOURCE_LAT = 0.0;
	private static double SOURCE_LNG = 0.0;
	private static double DESTINATION_LAT = 0.0;
	private static double DESTINATION_LNG = 0.0;

	@Autowired
	private IPlacesService placesService;

	@Autowired
	ISearchService searchService;

	@Autowired
	private ILocationsService locationService;

	Utilities util = new Utilities();

	@RequestMapping(method = RequestMethod.GET, value = "/places")
	public ModelAndView showRecommnededPlaces(HttpServletRequest request) {

		Map<String, List<Place>> categorizedPlaces = new HashMap<String, List<Place>>();
		Map<String, List<Place>> sortedCategorisedPlaces = new LinkedHashMap<String, List<Place>>();

		Map<String, Map<Place, Float>> scoredPlacesByMaxCheckins = new LinkedHashMap<String, Map<Place, Float>>();
		Map<String, Map<Place, Float>> scoredPlacesByMaxLikes = new LinkedHashMap<String, Map<Place, Float>>();
		Map<String, Map<Place, Float>> scoredPlacesByMaxRating = new LinkedHashMap<String, Map<Place, Float>>();
		Map<String, Map<Place, Float>> scoredPlacesByOpeningTime = new LinkedHashMap<String, Map<Place, Float>>();

		Map<Place, Float> maxCheckinsScoredPlaces = new LinkedHashMap<Place, Float>();
		Map<Place, Float> maxLikesScoredPlaces = new LinkedHashMap<Place, Float>();
		Map<Place, Float> maxRatingScoredPlaces = new LinkedHashMap<Place, Float>();

		Map<Place, Float> finalScoredPlaces = new LinkedHashMap<Place, Float>();
		Map<String, Map<Place, Float>> finalScoredPlacesWithCat = new LinkedHashMap<String, Map<Place, Float>>();

		int currentLocationId = (Integer) WebUtils.getSessionAttribute(request,
				"locationId");
		List<UsersLocation> currentLocation = locationService
				.findCurrentLocation(currentLocationId);
		UsersLocation loc = null;
		if (!currentLocation.isEmpty()) {
			loc = currentLocation.get(0);
			SOURCE_LAT = loc.getSourcelat();
			SOURCE_LNG = loc.getSourcelong();
			DESTINATION_LAT = loc.getDestinationlat();
			DESTINATION_LNG = loc.getDestinationlong();
		}
		Location sourceLoc = new Location(loc.getSourcelat(),
				loc.getSourcelong());
		Location destLoc = new Location(loc.getDestinationlat(),
				loc.getDestinationlong());

		List<Place> places = searchService.explore(new Location(SOURCE_LAT,
				SOURCE_LNG), new Location(DESTINATION_LAT, DESTINATION_LNG));

		categorizedPlaces = placesService.categorize(places);
		sortedCategorisedPlaces = placesService
				.sortPlacesByName(categorizedPlaces);

		Context context = new Context(new ScoringByMaxCheckinsImpl());
		scoredPlacesByMaxCheckins = context
				.executeStrategy(sortedCategorisedPlaces);
		for (Entry<String, Map<Place, Float>> entry : scoredPlacesByMaxCheckins
				.entrySet())
			maxCheckinsScoredPlaces.putAll(entry.getValue());

		context = new Context(new ScoringByMaxLikesImpl());
		scoredPlacesByMaxLikes = context
				.executeStrategy(sortedCategorisedPlaces);
		for (Entry<String, Map<Place, Float>> entry : scoredPlacesByMaxLikes
				.entrySet())
			maxLikesScoredPlaces.putAll(entry.getValue());

		context = new Context(new ScoringByMaxRatingImpl());
		scoredPlacesByMaxRating = context
				.executeStrategy(sortedCategorisedPlaces);
		for (Entry<String, Map<Place, Float>> entry : scoredPlacesByMaxRating
				.entrySet())
			maxRatingScoredPlaces.putAll(entry.getValue());

		context = new Context(new ScoringByOpeningTimeImpl());
		scoredPlacesByOpeningTime = context
				.executeStrategy(sortedCategorisedPlaces);
		scoredPlacesByOpeningTime = placesService
				.sortPlacesByScore(scoredPlacesByOpeningTime);

		finalScoredPlaces = placesService.mergeScores(maxCheckinsScoredPlaces,
				maxLikesScoredPlaces, maxRatingScoredPlaces);
		finalScoredPlacesWithCat = placesService
				.clusterPlaces(finalScoredPlaces);
		finalScoredPlacesWithCat = placesService
				.sortPlacesByScore(finalScoredPlacesWithCat);

		List<Place> lstplaces = ConvertMapToList(finalScoredPlacesWithCat);
		IPathFindAlgorithm algo = new PathFind_DijkstraDivImpl();
		System.out.println(lstplaces.get(0).getGeometry());
		List<Place> lstPath = algo
				.findPath(sourceLoc, destLoc, lstplaces, 0, 0);
		ModelAndView model = new ModelAndView("places");
		// model.addObject("scoredPlacesByMaxCheckins",
		// scoredPlacesByMaxCheckins);
		// model.addObject("scoredPlacesByMaxLikes", scoredPlacesByMaxLikes);
		// model.addObject("scoredPlacesByMaxRating", scoredPlacesByMaxRating);
		// model.addObject("scoredPlacesByOpeningTime",
		// scoredPlacesByOpeningTime);
		// model.addObject("finalScoredPlacesWithCat",
		// finalScoredPlacesWithCat);
		model.addObject("lstplaces", lstPath);
		return model;
	}

	@SuppressWarnings("rawtypes")
	private List<Place> ConvertMapToList(
			Map<String, Map<Place, Float>> finalScoredPlacesWithCat) {
		List<Place> lstplaces = new ArrayList<Place>();
		Iterator outerIterator = finalScoredPlacesWithCat.entrySet().iterator();
		while (outerIterator.hasNext()) {
			Map.Entry pairs = (Map.Entry) outerIterator.next();
			Map outerPlacesMap = (Map) pairs.getValue();
			Iterator innterIteratorPlaces = outerPlacesMap.entrySet()
					.iterator();
			while (innterIteratorPlaces.hasNext()) {
				Map.Entry pairs2 = (Map.Entry) innterIteratorPlaces.next();
				Float rating = (Float) pairs2.getValue();
				Place place = (Place) pairs2.getKey();
				place.setRating(rating);
				lstplaces.add(place);
			}
		}
		return lstplaces;
	}

}
