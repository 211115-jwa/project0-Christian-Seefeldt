package com.revature.services;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.revature.beans.Bike;
import com.revature.beans.Person;
import com.revature.data.DAO.BikeDAO;
import com.revature.data.DAO.PersonDAO;
import com.revature.data.postgres.PersonPostgress;
import com.revature.data.postgres.BikePostgres;


public class UserServiceImpl implements UserService {
	private PersonDAO personDao = new PersonPostgress();
	private BikeDAO bikeDao = new BikePostgres();

	
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
		if (bikeToBuy.isAvailable().equals("available")) {
			bikeToBuy.setAvailable("bought");
			newOwner.getBikes().add(bikeToBuy);
			
			bikeDao.update(bikeToBuy);
			personDao.update(newOwner);
			return newOwner;
		} 
		return null;
	}


	@Override
	public Set<Bike> viewAvailableBikes() {
		return bikeDao.getByAvailable("available");
	}
	
	@Override
	public Set<Bike> searchAvailablebikesByModel(String model) {
		Set<Bike> availableBikes = bikeDao.getByAvailable("available");
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getModel().toLowerCase().contains(model.toLowerCase()))
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

	@Override
	public Set<Bike> searchAvailablebikesByColor(String color) {
		Set<Bike> availableBikes = bikeDao.getByAvailable("available");
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getColor().toLowerCase().contains(color.toLowerCase()))
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

	@Override
	public Set<Bike> searchAvailablebikesByBrakes(String brakes) {
		Set<Bike> availableBikes = bikeDao.getByAvailable("available");
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getBrakes().toLowerCase().contains(brakes.toLowerCase()))
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

	@Override
	public Set<Bike> searchAvailablebikesByWheels(String wheels) {
		Set<Bike> availableBikes = bikeDao.getByAvailable("available");
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getWheels().toLowerCase().contains(wheels.toLowerCase()))
					.collect(Collectors.toSet());
		
		return availableBikes;
	}
	
	@Override
	public Set<Bike> searchAvailablebikesByBrand(String brand) {
		Set<Bike> availableBikes = bikeDao.getByAvailable("available");
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getBrand().toLowerCase().contains(brand.toLowerCase()))
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

	@Override
	public Set<Bike> searchAvailablebikesByElec(Boolean electric) {
		Set<Bike> availableBikes = bikeDao.getByAvailable("available");
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.isElectric() == electric)
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

	@Override
	public Set<Bike> searchAvailablebikesByFrame(int frameSize) {
		Set<Bike> availableBikes = bikeDao.getByAvailable("available");
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getFrameSize() == frameSize)
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

	@Override
	public Set<Bike> searchAvailablebikesBySpeeds(int speeds) {
		Set<Bike> availableBikes = bikeDao.getByAvailable("available");
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getSpeeds() == speeds)
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

	@Override
	public Set<Bike> searchAvailablebikesByPrice(double price) {
		Set<Bike> availableBikes = bikeDao.getByAvailable("available");
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getPrice() == price)
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

	@Override
	public Set<Bike> searchAvailablebikesByPriceRange(double price1, double price2) {
		Set<Bike> availableBikes = bikeDao.getByAvailable("available");
		
		availableBikes = availableBikes.stream()
					.filter(bike -> bike.getPrice() <= price1)
					.filter(bike -> bike.getPrice() >= price2)
					.collect(Collectors.toSet());
		
		return availableBikes;
	}

}
