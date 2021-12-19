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
		Bike bikeOutput = bikeDao.getById(idInput);
		assertNull(bikeOutput);
	}
	@Test
	public void getByAvalible() {
		Set<Bike> givenOutput = bikeDao.getAll();
		assertNotNull(givenOutput);
	}
	@Test
	public void getByBrand() {
		Set<Bike> givenOutput = bikeDao.getAll();
		assertNotNull(givenOutput);
	}
	@Test
	public void getByModel() {
		Set<Bike> givenOutput = bikeDao.getAll();
		assertNotNull(givenOutput);
	}
	@Test
	public void getByColor() {
		Set<Bike> givenOutput = bikeDao.getAll();
		assertNotNull(givenOutput);
	}
	@Test
	public void getByBrakes() {
		Set<Bike> givenOutput = bikeDao.getAll();
		assertNotNull(givenOutput);
	}
	@Test
	public void getByWheels() {
		Set<Bike> givenOutput = bikeDao.getAll();
		assertNotNull(givenOutput);
	}
	@Test
	public void getByElec() {
		Set<Bike> givenOutput = bikeDao.getAll();
		assertNotNull(givenOutput);
	}
	@Test
	public void getByFrameSize() {
		Set<Bike> givenOutput = bikeDao.getAll();
		assertNotNull(givenOutput);
	}
	@Test
	public void getBySpeeds() {
		Set<Bike> givenOutput = bikeDao.getAll();
		assertNotNull(givenOutput);
	}
	@Test
	public void getByFrameSize() {
		Set<Bike> givenOutput = bikeDao.getAll();
		assertNotNull(givenOutput);
	}
	@Test
	public void getByFrameSize() {
		Set<Bike> givenOutput = bikeDao.getAll();
		assertNotNull(givenOutput);
	}
	@Test
	public void addNewBike() {
		Bike newBike = new Bike();
		System.out.println(newBike);
		int generatedId = bikeDao.create(newBike);
		assertNotEquals(0, generatedId);
		System.out.println(generatedId);
	}
}
