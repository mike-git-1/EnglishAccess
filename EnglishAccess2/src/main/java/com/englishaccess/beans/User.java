package com.englishaccess.beans;


import java.util.UUID;

public class User {

	private UUID userID;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	
	public User(String firstName, String lastName, String email, String password) {
		this.userID = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public User() {
		
	}

	public UUID getUserID() {
		return userID;
	}
	
	public void setUserID(UUID uuid) {
		this.userID = uuid;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
