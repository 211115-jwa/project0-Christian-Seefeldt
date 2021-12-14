package com.revature.beans;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private int id;
	private String firstName;
	private String lastName;
	private String role;
	private List<Bike> bikes;
	
	public Person() {
		id = 0;
		firstName = null;
		lastName = null;
		role = "customer";
		bikes = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Bike> getBikes() {
		return bikes;
	}
	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}
}
