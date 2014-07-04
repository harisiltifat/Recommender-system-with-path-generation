package de.tum.in.aics.thesis.project.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long userId;
	private String email;
	private String lastName;
	private String firstName;	
	private String location;
	
	public User() {
		
	}

	@Id
	@Column(name = "userId", unique = true, nullable = false)
	public long getUserId() {
		return this.userId;
	}
	
	public long setUserId(long socialId) {
		return this.userId = socialId;
	}
	
	@Column(name = "email", nullable = false, length = 50)
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "firstName", nullable = true, length = 20)
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName", nullable = true, length = 20)
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "location", nullable = true, length = 50)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
