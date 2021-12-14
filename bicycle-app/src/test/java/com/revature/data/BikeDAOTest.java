package com.revature.data;

import org.junit.jupiter.api.Test;

import com.revature.beans.Bike;
import com.revature.data.DAO.BikeDAO;
import com.revature.data.postgres.BikePostgres;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class BikeDAOTest {
	private BikeDAO bikeDao = new BikePostgres();
	
	@Test
	public void getByIdWhenIdExists() {
		int idInput = 1;
		Bike idOutput = bikeDao.getById(idInput);
		assertEquals(1, idOutput.getId());
	}
	@Test
	public void getByIdWhenIdDoesNotExists() {
		int idInput = -1;
		Bike petOutput = bikeDao.getById(idInput);
		assertNull(petOutput);
	}
	@Test
	public void getAll() {
		Set<Bike> givenOutput = bikeDao.getAll();
		assertNotNull(givenOutput);
	}
	@Test
	public void addNewPet() {
		Bike newPet = new Bike();
		System.out.println(newPet);
		int generatedId = bikeDao.create(newPet);
		assertNotEquals(0, generatedId);
		System.out.println(generatedId);
	}
}
