package com.revature.services;

import com.revature.beans.Bike;

public interface EmployeeService {
	
	public int addNewPet(Bike newBike);
	public Bike editPet(Bike bikeToEdit);
	public Bike getPetById(int id);
}
