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
}


