package com.revature.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.revature.beans.Bike;
import com.revature.beans.Person;
import com.revature.data.DAO.BikeDAO;
import com.revature.data.DAO.PersonDAO;

public class UserServiceImpl implements UserService {
	private PersonDAO personDao;
	private BikeDAO bikeDao;
	private Set<Bike> allBikes  = new HashSet<Bike>();
	private BikeDAO bikedao;
	
	@Override
	public Person register(Person newUser) {
		int newId = personDao.create(newUser);
		if (newId != 0) {
			newUser.setId(newId);
			return newUser;
		}
		return null;
	}

	@Override
	public Person logIn(String username, String password) {
		Person personFromDatabase = personDao.getByUsername(username);
		if (personFromDatabase != null && personFromDatabase.getPassword().equals(password)) {
			return personFromDatabase;
		}
		return null;
	}

	@Override
	public Person updateUser(Person userToUpdate) {
		if (personDao.getById(userToUpdate.getId()) != null) {
			personDao.update(userToUpdate);
			userToUpdate = personDao.getById(userToUpdate.getId());
			return userToUpdate;
		}
		return null;
	}


	@Override
	public Person buyBike(int bikeID, Person newOwner) {
		Bike bikeToBuy = bikeDao.getById(bikeID);
		if (bikeToBuy.isAvalible()) {
			bikeToBuy.setAvalible(false);
			newOwner.getBikes().add(bikeToBuy);
			
			bikeDao.update(bikeToBuy);
			personDao.update(newOwner);
			return newOwner;
		}
		return null;
	}


	@Override
	public Set<Bike> viewAvailableBikes() {
		return bikeDao.getByAvalibility(true);
	}
	
	@Override
	public Set<Bike> searchAvailablebikesByStyle(String style) {
		Set<Bike> availableBikes = bikeDao.getByAvalibility(true);
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getStyle().toLowerCase().contains(style.toLowerCase()))
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

	@Override
	public Set<Bike> searchAvailablebikesByColor(String color) {
		Set<Bike> availableBikes = bikeDao.getByAvalibility(true);
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getColor().toLowerCase().contains(color.toLowerCase()))
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

	@Override
	public Set<Bike> searchAvailablebikesByBrakes(String brakes) {
		Set<Bike> availableBikes = bikeDao.getByAvalibility(true);
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getBrakes().toLowerCase().contains(brakes.toLowerCase()))
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

	@Override
	public Set<Bike> searchAvailablebikesByWheels(String wheels) {
		Set<Bike> availableBikes = bikeDao.getByAvalibility(true);
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getWheels().toLowerCase().contains(wheels.toLowerCase()))
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

	@Override
	public Set<Bike> searchAvailablebikesByElec(Boolean electric) {
		Set<Bike> availableBikes = bikeDao.getByAvalibility(true);
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.isElectric().contains(electric)
					.collect(Collectors.toSet();
		
		return availableBikes;
	}

	@Override
	public Set<Bike> searchAvailablebikesByFrame(int frameSize) {
		Set<Bike> availableBikes = bikeDao.getByAvalibility(true);
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getFrameSize().ParseString().contains(frameSize.ParseString())
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

	@Override
	public Set<Bike> searchAvailablebikesBySpeeds(int speeds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Bike> searchAvailablebikesByPrice(float price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Bike> searchAvailablebikesByBrand(String brand) {
		Set<Bike> availableBikes = bikeDao.getByAvalibility(true);
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getBrand().toLowerCase().contains(brand.toLowerCase()))
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

}
