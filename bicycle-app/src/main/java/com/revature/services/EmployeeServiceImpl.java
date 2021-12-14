package com.revature.services;

import com.revature.beans.Bike;
import com.revature.data.DAO.BikeDAO;
import com.revature.data.postgres.BikePostgres;

public class EmployeeServiceImpl implements EmployeeService {
	private BikeDAO bikeDao = new BikePostgres();

	@Override
	public int addNewPet(Bike newBike) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Bike editPet(Bike bikeToEdit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bike getPetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
