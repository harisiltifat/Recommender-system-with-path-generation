package de.tum.in.aics.thesis.project.algorithms.implementations;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

import de.tum.in.aics.thesis.project.algorithms.interfaces.IPathFindAlgorithm;
import de.tum.in.aics.thesis.project.models.Edge;
import de.tum.in.aics.thesis.project.models.Location;
import de.tum.in.aics.thesis.project.models.Place;
import de.tum.in.aics.thesis.project.models.Vertex;
import de.tum.in.aics.thesis.project.models.PathParams;
import de.tum.in.aics.thesis.project.util.Utilities;

public class PathFind_DynammicAlgoImpl implements IPathFindAlgorithm {

	PriorityQueue<Vertex> vertexQueue;
	Vertex destinationVertex=null;
	Vertex sourceVertex=null;
	public List<Place> findPath(Location sourceLoc, Location destinationLoc,
			List<Place> lstplaces, float time, float budget) {
		AssignTimeAndCost(lstplaces);
		CreateGraph(sourceLoc, destinationLoc, lstplaces);
		Vertex v=null;
		while (!vertexQueue.isEmpty()) {
			v = vertexQueue.poll();
			for(Edge edge:v.getAdjacencies()){
				if(v.getRating()==0)
					continue;
				//Iterating on backward edges only
				if(edge.isBackwardEdge()){
					//If the backward node is source then handle specifically
					if(edge.getTarget().equals(sourceVertex)){
						PathParams pathObj=new PathParams();
						pathObj.setMaxEntertainment(v.getRating());
						pathObj.setMaxCost(v.getCostOfVertex());
						pathObj.lstPath.add(edge.getTarget());
						Map.Entry<Double,PathParams> keyValuePair=new AbstractMap.SimpleEntry<Double,PathParams>(edge.getWeight()+v.getTimeToSpend(),pathObj);
						performComparisionSteps(time, budget, v, keyValuePair);
					}
					//If the backward node is not source
					else{
						Map<Double,PathParams> previousMapPath=edge.getTarget().getMapPath();
						for(Entry previousEntry:previousMapPath.entrySet()){
							PathParams pathObj=new PathParams();
							pathObj.setMaxEntertainment(v.getRating()+((PathParams)previousEntry.getValue()).getMaxEntertainment());
							pathObj.setMaxCost(v.getCostOfVertex()+ ((PathParams)previousEntry.getValue()).getMaxCost());
							pathObj.lstPath.addAll(((PathParams)previousEntry.getValue()).lstPath);
							pathObj.lstPath.add(edge.getTarget());
							Map.Entry<Double,PathParams> keyValuePair=new AbstractMap.SimpleEntry<Double,PathParams>(edge.getWeight()+(Double)previousEntry.getKey()+v.getTimeToSpend(),pathObj);
							performComparisionSteps(time, budget, v, keyValuePair);
						}
					}
				}
			}
		}
		double maximumEntertainment=0;
	
		List<Vertex> finalPathVertex=null;
		Entry destinationEntry=destinationVertex.getMapPath().lastEntry();

		double otherEntertainment=((PathParams)destinationEntry.getValue()).getMaxEntertainment();

		System.out.println("Key is:"+destinationEntry.getKey()+" and ent is:"+((PathParams)destinationEntry.getValue()).getMaxEntertainment()+" and path is:"+((PathParams)destinationEntry.getValue()).lstPath);

		if(otherEntertainment>maximumEntertainment){
			maximumEntertainment=otherEntertainment;
			finalPathVertex=((PathParams)destinationEntry.getValue()).lstPath;
		}

		
		List<Place> lstPathPlaces=new ArrayList<Place>();
		for(Vertex vertex:finalPathVertex){
			lstPathPlaces.add(createPlace(vertex));
		}
		
		lstPathPlaces.add(createPlace(destinationVertex));
		return lstPathPlaces;
	}

	private int CountCategories(List<Vertex> lstPath) {
		int categoryCounter=0;
		List<String> lstCategory=new ArrayList<String>();
		for(Vertex vertex:lstPath){
			if(!lstCategory.contains(vertex.getCategory())){
				lstCategory.add(vertex.getCategory());
				categoryCounter++;
			}
		}
		return categoryCounter;
	}

	private void performComparisionSteps(float time, float budget, Vertex v,
			Map.Entry<Double, PathParams> keyValuePair) {
		
		//Checking cost constraint
		if(checkCostConstraint(keyValuePair.getValue().getMaxCost(),budget)){
			//Checking time constraint
			if(checkTimeConstraint(keyValuePair.getKey(),time)){
				int responseEquivalentKey=findEquivalentKey(v,keyValuePair);
				if(responseEquivalentKey==2){
					v.getMapPath().put(keyValuePair.getKey(),keyValuePair.getValue());
					findLargerKey(v,keyValuePair);
				}
				else if(responseEquivalentKey==1){
					if(findSmallerKey(v,keyValuePair)){
						findLargerKey(v,keyValuePair);
					}
				}
					
			}
		}
		
	}

	private void CreateGraph(Location sourceLoc, Location destinationLoc,
			List<Place> lstplaces) {

		//Declaring source place and destination place
				List<Vertex> lstVertices=new ArrayList<Vertex>();

				//Creating vertices and finding source place and destination place

				for(Place place: lstplaces){
				
					Vertex vertex=createVertex(place);
					//Checking if source exist in the list of places
					if(sourceLoc.getLat()==vertex.getLat() && sourceLoc.getLng()==vertex.getLng())
						sourceVertex=vertex;
					//Checking if destination exist in the list of places
					else if(destinationLoc.getLat()==vertex.getLat() && destinationLoc.getLng()==vertex.getLng())
						destinationVertex=vertex;
					
					lstVertices.add(vertex);
				}

				//source location may not be in the list of places. Then add source manually
				if(sourceVertex==null){
					sourceVertex=new Vertex("source");
					sourceVertex.setLat(sourceLoc.getLat());
					sourceVertex.setLng(sourceLoc.getLng());
					sourceVertex.setRating(1);
					lstVertices.add(sourceVertex);
				}
				//destination location may not be in the list of places. Then add destination manually
				if(destinationVertex==null){
					destinationVertex=new Vertex("destination");
					destinationVertex.setLat(destinationLoc.getLat());
					destinationVertex.setLng(destinationLoc.getLng());
					destinationVertex.setRating(1);
					destinationVertex.setCostOfVertex(0);
					destinationVertex.setTimeToSpend(0);
					lstVertices.add(destinationVertex);
				}
				
				vertexQueue = new PriorityQueue<Vertex>();
				for(Vertex vertex: lstVertices){
					Location locVertices=new Location(vertex.getLat(), vertex.getLng());
					double distance= Utilities.distance(sourceLoc,locVertices,'k');
					
					vertex.setDistanceFromSource(distance);
					if(!vertex.equals(sourceVertex))
						vertexQueue.add(vertex);
				}
				
				//Edge creation and assigning to vertices. Creating a complete graph
				for(Vertex vertex: lstVertices){
					for(Vertex vertexToCompare: lstVertices){
						if(!vertex.equals(sourceVertex)){
							Location loc1=new Location(vertex.getLat(),vertex.getLng());
							Location loc2=new Location(vertexToCompare.getLat(), vertexToCompare.getLng());
							double distance= Utilities.distance(loc1,loc2,'k');
							double distanceInMeters=distance*1000;
							double time=distanceInMeters/74.07;
							Edge edge=new Edge(vertexToCompare,time);
							
							//Creating forward edges to vertices who are further from source
							if(vertexToCompare.getDistanceFromSource()>vertex.getDistanceFromSource())
								edge.setForwardEdge(true);
							//Creating backward edges to vertices who are nearer from source
							else if(vertexToCompare.getDistanceFromSource()<vertex.getDistanceFromSource())
								edge.setBackwardEdge(true);
							//Creating backward or forward edges depending the type of edge equivalent vertex had
							else{
								for(Edge e:vertexToCompare.getAdjacencies()){
									if(e.getTarget().equals(vertexToCompare)){
										if(e.isForwardEdge())
											edge.setBackwardEdge(true);
										else
											edge.setForwardEdge(true);
									}
								}
							}
							vertex.getAdjacencies().add(edge);
						}
					}
				}
	}
	
	private Vertex createVertex(Place place) {
		Vertex vertex= new Vertex(place.getName());
		vertex.setGeometry(place.getGeometry());
		vertex.setRating(place.getRating());
		if(place.getLikes()!=null)
			vertex.setLikes(place.getLikes());
		vertex.setTypes(place.getTypes());
		vertex.setOpenNow(place.getOpenNow());
		vertex.setStats(place.getStats());
		vertex.setLng(place.getLongitude());
		vertex.setLat(place.getLatitude());
		vertex.setTimeToSpend(place.getTimeToSpend());
		vertex.setCostOfVertex(place.getCostOfPlace());
		vertex.setCategory(place.getCategory());
		return vertex;
	}
	
	private Place createPlace(Vertex vertex){
		Place place= new Place();
		place.setName(vertex.getName());
		place.setGeometry(vertex.getGeometry());
		place.setRating(vertex.getRating());
		place.setLikes(vertex.getLikes());
		place.setTypes(vertex.getTypes());
		place.setOpenNow(vertex.getOpenNow());
		place.setStats(vertex.getStats());
		place.setLongitude(vertex.getLng());
		place.setLatitude(vertex.getLat());
		return place;
	}
	
	private boolean checkTimeConstraint(double timeSpent, double timeAllowed){
		if(timeSpent<=timeAllowed)
			return true;
		else
			return false;
	}
	
	private boolean checkCostConstraint(double costSpent, double costAllowed){
		if(costSpent<=costAllowed)
			return true;
		else
			return false;
	}
	
	//0 means key-value pair needs to be rejected 
	//1 means proceed to step c and d
	//2 means insert key value and perform step d
	private int findEquivalentKey(Vertex currentVertex, Map.Entry<Double,PathParams> keyValuePair){
		if(currentVertex.getMapPath().containsKey(keyValuePair.getKey())){
			if(keyValuePair.getValue().getMaxEntertainment()>currentVertex.getMapPath().get(keyValuePair.getKey()).getMaxEntertainment())
				return 2;
			else
				return 0;
		}
		return 1;
	}
	
	private void findLargerKey(Vertex currentVertex, Map.Entry<Double,PathParams> keyValuePair){
		while(true){
			Double largerKey=currentVertex.getMapPath().higherKey(keyValuePair.getKey());
			
			
			if(largerKey!=null){
				int categoryCount = CountCategories(currentVertex.getMapPath().get(largerKey).lstPath);
				int newCategoryCount=CountCategories(keyValuePair.getValue().lstPath);
				if(categoryCount * currentVertex.getMapPath().get(largerKey).getMaxEntertainment()<=newCategoryCount * keyValuePair.getValue().getMaxEntertainment()){
					currentVertex.getMapPath().remove(largerKey);
				}
				else
					break;
			}
			else 
				break;
		}
	}
		
	private boolean findSmallerKey(Vertex currentVertex, Map.Entry<Double,PathParams> keyValuePair){
		Double smallerKey=currentVertex.getMapPath().lowerKey(keyValuePair.getKey());
		if(smallerKey == null){
			currentVertex.getMapPath().put(keyValuePair.getKey(), keyValuePair.getValue());
			return true;
		}
		
		int categoryCount = CountCategories(currentVertex.getMapPath().get(smallerKey).lstPath);
		int newCategoryCount=CountCategories(keyValuePair.getValue().lstPath);
			
		if(categoryCount*currentVertex.getMapPath().get(smallerKey).getMaxEntertainment()<newCategoryCount*keyValuePair.getValue().getMaxEntertainment()){
			currentVertex.getMapPath().put(keyValuePair.getKey(), keyValuePair.getValue());
			return true;
		}
			
		return false;
	}
	
	private void AssignTimeAndCost(List<Place> lstPlaces){
		float costOfPlace=0, timeToSpend=0;
		for(Place place:lstPlaces){
			String category= place.getCategory();
			if(category.equalsIgnoreCase("Museum")){
				costOfPlace=12;
				timeToSpend=45;
			}
			else if(category.equalsIgnoreCase("Night Life")){
				costOfPlace=10;
				timeToSpend=60;
			}
			else if(category.equalsIgnoreCase("Food")){
				costOfPlace=13;
				timeToSpend=40;
			}
			else if(category.equalsIgnoreCase("Nature")){
				costOfPlace=0;
				timeToSpend=45;
			}
			else if(category.equalsIgnoreCase("Music")){
				costOfPlace=15;
				timeToSpend=50;
			}
			else if(category.equalsIgnoreCase("Shopping")){
				costOfPlace=0;
				timeToSpend=60;
			}
			else{
				System.out.println("I am out,category is: "+place.getCategory());
				costOfPlace=200;
				timeToSpend=200;
			}


			place.setCostOfPlace(costOfPlace);
			place.setTimeToSpend(timeToSpend);

		}
	}
}
