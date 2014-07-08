package de.tum.in.aics.thesis.project.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Place {
	//private String reference;
	private String name;
	//private String icon;
	//private String formatted_address;
	//private String formatted_phone_number;
	private String geometry;
	//private String opening_hours;
	private boolean openNow;
	//private String price_level;
	private float rating;
	private String types;
	//private String vicinity;
	private Integer stats; 
	private Integer likes;
	private double latitude;
	private double longitude;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public String getFormatted_phone_number() {
		return formatted_phone_number;
	}

	public void setFormatted_phone_number(String formatted_phone_number) {
		this.formatted_phone_number = formatted_phone_number;
	}*/

	public String getGeometry() {
		return geometry;
	}

	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}
	
	public boolean getOpenNow() {
		return openNow;
	}

	public void setOpenNow(boolean openNow) {
		this.openNow = openNow;
	}

	/*public String getOpening_hours() {
		return opening_hours;
	}

	public void setOpening_hours(String opening_hours) {
		this.opening_hours = opening_hours;
	}

	

	public String getPrice_level() {
		return price_level;
	}

	public void setPrice_level(String price_level) {
		this.price_level = price_level;
	}*/

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	/*public String getVicinity() {
		return vicinity;
	}

	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}


	
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}*/

	public Integer getStats() {
		return stats;
	}

	public void setStats(Integer stats) {
		this.stats = stats;
	}
	
	public Integer getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	/*@Override
	public String toString() {
	    return this.getName() + "=" +  this.getStats();
	}*/
	
}
