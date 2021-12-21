package com.revature.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Person;
import com.revature.beans.Bike;
import com.revature.data.DAO.PersonDAO;
import com.revature.data.DAO.BikeDAO;


@ExtendWith(MockitoExtension.class)
public class UserServicesTest {

	@Mock
	private BikeDAO BikeDao;
	
	@Mock
	private PersonDAO personDao;
	
	
	@InjectMocks
	private UserService userServ = new UserServiceImpl();
	
	private static Set<Bike> mockAvailableBikes;
	
	@BeforeAll
	public static void mockAvailableBikesSetup() {
		mockAvailableBikes = new HashSet<>();
		
		for (int i=1; i<=5; i++) {
			Bike bike = new Bike();
			bike.setId(i);
			if (i<3)
				bike.setBrand("Kent");
				bike.setModel("Mountain");
				bike.setColor("red");
				bike.setBrakes("cruiser");
				bike.setWheels("blue");
				bike.setAvailable("available");
				bike.setElectric(false);
				bike.setFrameSize(25);
				bike.setSpeeds(5);
				bike.setPrice(150.43);
				
			mockAvailableBikes.add(bike);
		}
	}
	
	@Test
	public void logInSuccessfully() {
		
		String username="qwertyuiop";
		String password="pass";
		
		
		Person mockPerson = new Person();
		mockPerson.setUsername(username);
		mockPerson.setPassword(password);
		when(personDao.getByUsername(username)).thenReturn(mockPerson);
		
		
		Person actualPerson = userServ.logIn(username, password);
		
		
		assertEquals(mockPerson,actualPerson);
	}
	
	@Test
	public void logInIncorrectPassword() {
		String username="qwertyuiop";
		String password="12345";
		
		Person mockPerson = new Person();
		mockPerson.setUsername(username);
		mockPerson.setPassword("pass");
		when(personDao.getByUsername(username)).thenReturn(mockPerson);
		
		Person actualPerson = userServ.logIn(username, password);
		assertNull(actualPerson);
	}
	
	@Test
	public void logInUsernameDoesNotExist() {
		String username="asdfghjkl";
		String password="pass";
		
		when(personDao.getByUsername(username)).thenReturn(null);
		
		Person actualPerson = userServ.logIn(username, password);
		assertNull(actualPerson);
	}
	
	@Test
	public void registerPersonSuccessfully() {
		Person person = new Person();
		
		when(personDao.create(person)).thenReturn(10);
		
		Person actualPerson = userServ.register(person);
		assertEquals(10, actualPerson.getId());
	}
	
	@Test
	public void registerPersonSomethingWrong() {
		Person person = new Person();
		when(personDao.create(person)).thenReturn(0);
		Person actualPerson = userServ.register(person);
		assertNull(actualPerson);
	}
	
	@Test
	public void searchByBrandExists() {
		String brand = "Kent";
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByBrand(brand);
		boolean onlyBikes = true;
		for (Bike bike : actualBikes) {
			if (!bike.getBrand().equals(brand))
				onlyBikes = false;
		}
		
		assertTrue(onlyBikes);
	}
	
	@Test
	public void searchByBrandDoesNotExist() {
		String brand = "qwertyuiop";
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByBrand(brand);
		assertTrue(actualBikes.isEmpty());
	}
	
	@Test
	public void searchByModelExists() {
		String model = "Mountain";
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByModel(model);
		boolean onlyBikes = true;
		for (Bike bike : actualBikes) {
			if (!bike.getModel().equals(model))
				onlyBikes = false;
		}
		
		assertTrue(onlyBikes);
	}
	
	@Test
	public void searchByModelDoesNotExist() {
		String model = "qwertyuiop";
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByModel(model);
		assertTrue(actualBikes.isEmpty());
	}
	
	
	@Test
	public void searchByColorExists() {
		String color = "red";
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByColor(color);
		boolean onlyBikes = true;
		for (Bike bike : actualBikes) {
			if (!bike.getColor().equals(color))
				onlyBikes = false;
		}
		
		assertTrue(onlyBikes);
	}
	
	@Test
	public void searchByColorDoesNotExist() {
		String color = "qwertyuiop";
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByColor(color);
		assertTrue(actualBikes.isEmpty());
	}
	
	@Test
	public void searchByBrakesExists() {
		String brakes = "cruiser";
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByBrand(brakes);
		boolean onlyBikes = true;
		for (Bike bike : actualBikes) {
			if (!bike.getBrand().equals(brakes))
				onlyBikes = false;
		}
		
		assertTrue(onlyBikes);
	}
	
	@Test
	public void searchByBrakesDoesNotExist() {
		String brakes = "qwertyuiop";
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByBrakes(brakes);
		assertTrue(actualBikes.isEmpty());
	}
	
	@Test
	public void searchByWheelsExists() {
		String wheels = "blue";
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByWheels(wheels);
		boolean onlyBikes = true;
		for (Bike bike : actualBikes) {
			if (!bike.getWheels().equals(wheels))
				onlyBikes = false;
		}
		
		assertTrue(onlyBikes);
	}
	
	@Test
	public void searchByWheelsDoesNotExist() {
		String wheels = "qwertyuiop";
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByBrand(wheels);
		assertTrue(actualBikes.isEmpty());
	}
	
	@Test
	public void searchByElectricExists() {
		Boolean electric = false;
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByElec(electric);
		boolean onlyBikes = true;
		for (Bike bike : actualBikes) {
			if (!bike.isElectric() == (electric))
				onlyBikes = false;
		}
		
		assertTrue(onlyBikes);
	}
	
	@Test
	public void searchByElectricDoesNotExist() {
		boolean electric = true;
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByElec(electric);
		assertTrue(actualBikes.isEmpty());
	}
	
	@Test
	public void searchByFrameSizeExists() {
		int frameSize = 25;
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByFrame(frameSize);
		boolean onlyBikes = true;
		for (Bike bike : actualBikes) {
			if (!(bike.getFrameSize() == (frameSize)))
				onlyBikes = false;
		}
		
		assertTrue(onlyBikes);
	}
	
	@Test
	public void searchByFrameSizeDoesNotExist() {
		int frameSize = 9898;
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByFrame(frameSize);
		assertTrue(actualBikes.isEmpty());
	}
	
	@Test
	public void searchBySpeedsExists() {
		int speeds = 5;
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesBySpeeds(speeds);
		boolean onlyBikes = true;
		for (Bike bike : actualBikes) {
			if (!(bike.getSpeeds() == (speeds)))
				onlyBikes = false;
		}
		
		assertTrue(onlyBikes);
	}
	
	@Test
	public void searchBySpeedsDoesNotExist() {
		int speeds = 55;
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesBySpeeds(speeds);
		assertTrue(actualBikes.isEmpty());
	}
	
	@Test
	public void searchByPriceExists() {
		double price = 150.43;
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByPrice(price);
		boolean onlyBikes = true;
		for (Bike bike : actualBikes) {
			if (!(bike.getPrice() == (price)))
				onlyBikes = false;
		}
		
		assertTrue(onlyBikes);
	}
	
	@Test
	public void searchByPriceDoesNotExist() {
		double price = 11.11;
		
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByPrice(price);
		assertTrue(actualBikes.isEmpty());
	}
	
	@Test
	public void BuyBikeSuccessfully() {
		int bikeID = 1;
		Person person = new Person();
		
		Bike mockBike = new Bike();
		mockBike.setId(1);
		mockBike.setAvailable("available");
		when(BikeDao.getById(bikeID)).thenReturn(mockBike);
		
		doNothing().when(BikeDao).update(Mockito.any(Bike.class));
		doNothing().when(personDao).update(Mockito.any(Person.class));
		
		Person newPerson = userServ.buyBike(bikeID, person) ;

		mockBike.setAvailable("bought");
		assertTrue(newPerson.getBikes().contains(mockBike));
	}
	
	@Test
	public void BuyBikeAlreadyBought() {
		int bikeId = 1;
		Person person = new Person();
		
		Bike mockBike = new Bike();
		mockBike.setId(1);
		mockBike.setAvailable("bought");
		when(BikeDao.getById(bikeId)).thenReturn(mockBike);
		
		Person newPerson = userServ.buyBike(bikeId, person);
		
		assertNull(newPerson);
		
		verify(BikeDao, times(0)).update(Mockito.any(Bike.class));
		verify(personDao, times(0)).update(Mockito.any(Person.class));
	}
	
	@Test
	public void updateSuccessfully() {
		Person mockPerson = new Person();
		mockPerson.setId(1);
		
		doNothing().when(personDao).update(Mockito.any(Person.class));
		when(personDao.getById(1)).thenReturn(mockPerson);
		
		Person person = new Person();
		person.setId(1);
		person.setUsername("qwertyuiop");
		Person updatedPerson = userServ.updateUser(person);
		assertNotEquals(person, updatedPerson);
	}
	
	@Test
	public void updateSomethingWrong() {
		Person mockPerson = new Person();
		mockPerson.setId(1);
		
		doNothing().when(personDao).update(Mockito.any(Person.class));
		when(personDao.getById(1)).thenReturn(mockPerson);
		
		Person person = new Person();
		person.setId(1);
		person.setUsername("qwertyuiop");
		Person updatedPerson = userServ.updateUser(person);
		assertNotEquals(person, updatedPerson);
	}
	
	@Test
	public void viewAvailableBikes() {
		when(BikeDao.getByAvailable("available")).thenReturn(mockAvailableBikes);
		
		Set<Bike> actualBikes = userServ.viewAvailableBikes();
		
		assertEquals(mockAvailableBikes, actualBikes);
	}
}
