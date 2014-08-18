package de.tum.in.aics.thesis.project.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
public class UsersLocation implements Serializable{

	private static final long serialVersionUID = 1L;
	private int locationId;
	private double sourcelat;
	private double sourcelong;
	private double destinationlat;
	private double destinationlong;
	private float time;
	private float budget;
	private boolean isTimeEnable;

	public double getSourcelat() {
		return sourcelat;
	}

	public void setSourcelat(double sourcelat) {
		this.sourcelat = sourcelat;
	}

	public double getSourcelong() {
		return sourcelong;
	}

	public void setSourcelong(double sourcelong) {
		this.sourcelong = sourcelong;
	}

	public double getDestinationlat() {
		return destinationlat;
	}

	public void setDestinationlat(double destinationlat) {
		this.destinationlat = destinationlat;
	}

	public double getDestinationlong() {
		return destinationlong;
	}

	public void setDestinationlong(double destinationlong) {
		this.destinationlong = destinationlong;
	}

	public UsersLocation(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public boolean isTimeEnable() {
		return isTimeEnable;
	}

	public void setTimeEnable(boolean isTimeEnable) {
		this.isTimeEnable = isTimeEnable;
	}
	
}
