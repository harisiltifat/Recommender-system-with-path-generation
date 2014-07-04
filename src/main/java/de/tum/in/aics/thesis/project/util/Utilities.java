package de.tum.in.aics.thesis.project.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import de.tum.in.aics.thesis.project.models.Location;
import de.tum.in.aics.thesis.project.models.Place;

public class Utilities {

	static Location midloc = new Location(0, 0);

	public Location midPoint(Location loc1, Location loc2) {
		
		double dLon = Math.toRadians(loc2.lng - loc1.lng);
		double lat1 = Math.toRadians(loc1.lat);
		double lat2 = Math.toRadians(loc2.lat);
		double lng1 = Math.toRadians(loc1.lng);

		double Bx = Math.cos(lat2) * Math.cos(dLon);
		double By = Math.cos(lat2) * Math.sin(dLon);
		midloc.lat = Math.atan2(Math.sin(lat1) + Math.sin(lat2),Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
		midloc.lng = lng1 + Math.atan2(By, Math.cos(lat1) + Bx);
		midloc.lat = Math.toDegrees(midloc.lat);
		midloc.lng = Math.toDegrees(midloc.lng);
		return midloc;
	}

	public double distance(Location loc1, Location loc2, char unit) {
		double theta = loc1.lng - loc2.lng;
		double dist = Math.sin(deg2rad(loc1.lat)) * Math.sin(deg2rad(loc2.lat)) + Math.cos(deg2rad(loc1.lat)) * Math.cos(deg2rad(loc2.lat)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == 'K') {
			dist = dist * 1.609344;
		} else if (unit == 'N') {
			dist = dist * 0.8684;
		}
		return (dist);
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

	public List<Place> mergeList(List<Place> allList, List<Place> list) {
		List<Place> tmpList = new ArrayList<Place>();
		for (Place place : list) {
			boolean exist = false;
			for (Place place2 : allList) {
				if (place.getName().equalsIgnoreCase(place2.getName()))
					exist = true;
			}
			if (!exist)
				tmpList.add(place);
		}

		allList.addAll(tmpList);
		return allList;
	}
	
	public float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
