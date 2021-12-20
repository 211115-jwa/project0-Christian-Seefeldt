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
	private BikeDAO Bikeao;
	
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
//	@Test
//	public void searchByBrandExists() {
//		String brand = "cat";
//		
//		when(petDao.getByStatus("Available")).thenReturn(mockAvailablePets);
//		
//		Set<Pet> actualCats = userServ.searchAvailablePetsBySpecies(species);
//		boolean onlyCats = true;
//		for (Pet pet : actualCats) {
//			if (!pet.getSpecies().equals(species))
//				onlyCats = false;
//		}
//		
//		assertTrue(onlyCats);
//	}
//	
//	@Test
//	public void searchBySpeciesDoesNotExist() {
//		String species = "qwertyuiop";
//		
//		when(petDao.getByStatus("Available")).thenReturn(mockAvailablePets);
//		
//		Set<Pet> actualPets = userServ.searchAvailablePetsBySpecies(species);
//		assertTrue(actualPets.isEmpty());
//	}
//	
//	@Test
//	public void adoptPetSuccessfully() {
//		int petId = 1;
//		Person person = new Person();
//		
//		Pet mockPet = new Pet();
//		mockPet.setId(1);
//		when(petDao.getById(petId)).thenReturn(mockPet);
//		
//		// mock will do nothing when "update" gets called with any pet or person
//		doNothing().when(petDao).update(Mockito.any(Pet.class));
//		doNothing().when(personDao).update(Mockito.any(Person.class));
//		
//		Person newPerson = userServ.adoptPet(petId, person);
//		
//		// make sure that the method returned a person that has their
//		// newly adopted pet there, and that pet has the correct status
//		mockPet.setStatus("Adopted");
//		assertTrue(newPerson.getPets().contains(mockPet));
//	}
//	
//	@Test
//	public void adoptPetAlreadyAdopted() {
//		int petId = 1;
//		Person person = new Person();
//		
//		Pet mockPet = new Pet();
//		mockPet.setId(1);
//		mockPet.setStatus("Adopted");
//		when(petDao.getById(petId)).thenReturn(mockPet);
//		
//		Person newPerson = userServ.adoptPet(petId, person);
//		
//		assertNull(newPerson);
//		
//		// these Mockito methods will verify that neither of these
//		// update methods got called
//		verify(petDao, times(0)).update(Mockito.any(Pet.class));
//		verify(personDao, times(0)).update(Mockito.any(Person.class));
//	}
//	
//	@Test
//	public void updateSuccessfully() {
//		Person mockPerson = new Person();
//		mockPerson.setId(1);
//		
//		doNothing().when(personDao).update(Mockito.any(Person.class));
//		when(personDao.getById(1)).thenReturn(mockPerson);
//		
//		Person person = new Person();
//		person.setId(1);
//		person.setUsername("qwertyuiop");
//		Person updatedPerson = userServ.updateUser(person);
//		assertNotEquals(person, updatedPerson);
//	}
//	
//	@Test
//	public void updateSomethingWrong() {
//		Person mockPerson = new Person();
//		mockPerson.setId(1);
//		
//		doNothing().when(personDao).update(Mockito.any(Person.class));
//		when(personDao.getById(1)).thenReturn(mockPerson);
//		
//		Person person = new Person();
//		person.setId(1);
//		person.setUsername("qwertyuiop");
//		Person updatedPerson = userServ.updateUser(person);
//		assertNotEquals(person, updatedPerson);
//	}
//	
//	@Test
//	public void viewAvailablePets() {
//		when(petDao.getByStatus("Available")).thenReturn(mockAvailablePets);
//		
//		Set<Pet> actualPets = userServ.viewAvailablePets();
//		
//		assertEquals(mockAvailablePets, actualPets);
//	}
//}
//
////	@Test
////	public void searchByBrandExists() {
////		String brand = "Kent";
////		
////		when(BikeDAO.getByAvailable("Available")).thenReturn(mockAvailableBikes);
////		
////		Set<Bike> actualBikes = userServ.searchAvailablebikesByBrand(brand);
////		boolean onlyBikes = true;
////		for (Bike bike : actualBikes) {
////			if (!bike.getBrand().equals(brand))
////				onlyBikes = false;
////		}
////		
////		assertTrue(onlyBikes);
////	}
//
}