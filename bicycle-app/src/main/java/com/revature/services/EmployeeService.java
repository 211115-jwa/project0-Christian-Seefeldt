package com.revature.services;

import com.revature.beans.Bike;

public interface EmployeeService {
	
	public int addNewBike(Bike newBike);
	public Bike editBike(Bike bikeToEdit);
	public Bike getBikeById(int id);
}
