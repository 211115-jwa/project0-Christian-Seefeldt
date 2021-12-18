package com.revature.beans;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private int id;
	private String usr; //
	private String psw; //
	private String firstName;
	private String lastName;
	private String userRole; //
	private List<Bike> bikes;
	
	public Person() {
		id = 0;
		firstName = null;
		lastName = null;
		userRole = "Customer";
		bikes = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return usr;
	}
	public void setUsername(String usr) {
		this.usr = usr;
	}
	public String getPassword() {
		return psw;
	}
	public void setPassword(String psw) {
		this.psw = psw;
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
		return userRole;
	}
	public void setRole(String userRole) {
		this.userRole = userRole;
	}
	public List<Bike> getBikes() {
		return bikes;
	}
	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}
}
