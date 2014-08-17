package de.tum.in.aics.thesis.project.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "preferences")
public class UsersPreferences implements Serializable{

	private static final long serialVersionUID = 1L;
	private int museumPreference;
	private int nightlifePreference;
	private int foodPreference;
	private int naturePreference;
	private int musicPreference;
	private int shoppingPreference;
	
	public UsersPreferences(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int preferenceId;


	public int getMuseumPreference() {
		return museumPreference;
	}

	public void setMuseumPreference(int museumPreference) {
		this.museumPreference = museumPreference;
	}

	public int getNightlifePreference() {
		return nightlifePreference;
	}

	public void setNightlifePreference(int nightlifePreference) {
		this.nightlifePreference = nightlifePreference;
	}

	public int getFoodPreference() {
		return foodPreference;
	}

	public void setFoodPreference(int foodPreference) {
		this.foodPreference = foodPreference;
	}

	public int getNaturePreference() {
		return naturePreference;
	}

	public void setNaturePreference(int naturePreference) {
		this.naturePreference = naturePreference;
	}

	public int getMusicPreference() {
		return musicPreference;
	}

	public void setMusicPreference(int musicPreference) {
		this.musicPreference = musicPreference;
	}

	public int getShoppingPreference() {
		return shoppingPreference;
	}

	public void setShoppingPreference(int shoppingPreference) {
		this.shoppingPreference = shoppingPreference;
	}

	public int getPreferenceId() {
		return preferenceId;
	}

	public void setPreferenceId(int preferenceId) {
		this.preferenceId = preferenceId;
	}
	
	@ManyToOne
    @JoinColumn(name = "userId")
    private User user;
	
	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser(){
		return user;
	}

	@ManyToOne
    @JoinColumn(name = "locationId")
    private UsersLocation location;

	public UsersLocation getUserLoc() {
		return location;
	}

	public void setUserLoc(UsersLocation userLoc) {
		this.location = userLoc;
	}
	
}
