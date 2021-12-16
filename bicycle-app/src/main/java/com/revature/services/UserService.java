package com.revature.services;

import java.util.List;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Person;

public interface UserService {
	public Person register(Person newUser);
	public Person logIn(String username, String password);
	public Person updateUser(Person userToUpdate);
	public Person buyBike(int bikeId, Person newOwner);
	public Set<Bike> viewAvailableBikes();
	public Set<Bike> searchAvailablebikesByBrand(String brand);
	public Set<Bike> searchAvailablebikesByStyle(String style);
	public Set<Bike> searchAvailablebikesByColor(String color);
	public Set<Bike> searchAvailablebikesByBrakes(String brakes);
	public Set<Bike> searchAvailablebikesByWheels(String wheels);
	public Set<Bike> searchAvailablebikesByElec(Boolean electric);
	public Set<Bike> searchAvailablebikesByFrame(int frameSize);
	public Set<Bike> searchAvailablebikesBySpeeds(int speeds);
	public Set<Bike> searchAvailablebikesByPrice(float price);
}


