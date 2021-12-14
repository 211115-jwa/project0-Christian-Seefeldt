package com.revature.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person logIn(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person updateUser(Person userToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person buyBike(int bikeId, Person newOwner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Bike> viewAvailableBikes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Bike> searchAvailablebikesByBrand(String brand) {
		Set<Bike> bike = new HashSet <Bike>();
		allBikes = bikedao.getAll();
		for(int i = 0;i < allBikes.size();i++) {
			Iterator value = bike.iterator();
			while (value.hasNext()) {
				if( allBikes.get(i).getBrand().equals(brand)) {
					b.add(allBikes.get(i));
			}
			
			
				
			}
		}
		return b;
	}

}
