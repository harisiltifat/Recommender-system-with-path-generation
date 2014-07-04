package de.tum.in.aics.thesis.project.comparators;

import java.util.Comparator;
import java.util.Map;

public class PlacesComparator implements Comparator<Object> {

	Map<String, Integer> map;
	
	public PlacesComparator(Map<String, Integer> map) {
        this.map = map;
    }
	@Override
	public int compare(Object o1, Object o2) {
		if (map.get(o2) == map.get(o1))
            return 1;
        else
            return ((Integer) map.get(o2)).compareTo((Integer) map.get(o1));
	}

}
