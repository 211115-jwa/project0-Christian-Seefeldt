package com.revature.data;

import org.junit.jupiter.api.Test;

import com.revature.beans.Bike;
import com.revature.data.DAO.BikeDAO;
import com.revature.data.postgres.BikePostgres;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
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
		Set<Bike> givenOutput = bikeDao.getByAvalible(true);
		assertNotNull(givenOutput);
		Set<Bike> expectedBikes = new HashSet<>();
		assertNotEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByBrandWhenBrandDoesExist() {
		Set<Bike> givenOutput = bikeDao.getByBrand("Kent");
		assertNotNull(givenOutput);
		Set<Bike> expectedBikes = new HashSet<>();
		assertNotEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByBrandWhenBrandDoesntExist() {
		Set<Bike> givenOutput = bikeDao.getByBrand("QQ7");
		Set<Bike> expectedBikes = new HashSet<>();
		assertEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByModelWhenModelDoesExist() {
		Set<Bike> givenOutput = bikeDao.getByModel("Mountain");
		assertNotNull(givenOutput);
		Set<Bike> expectedBikes = new HashSet<>();
		assertNotEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByModelWhenModelDoesntExist() {
		Set<Bike> givenOutput = bikeDao.getByModel("QQ7");
		Set<Bike> expectedBikes = new HashSet<>();
		assertEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByColorWhenColorDoesExist() {
		Set<Bike> givenOutput = bikeDao.getByColor("red");
		assertNotNull(givenOutput);
		Set<Bike> expectedBikes = new HashSet<>();
		assertNotEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByColorWhenColorDoesntExist() {
		Set<Bike> givenOutput = bikeDao.getByColor("QQ7");
		Set<Bike> expectedBikes = new HashSet<>();
		assertEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByBrakesWhenBrakesDoesExist() {
		Set<Bike> givenOutput = bikeDao.getByBrakes("linear pull");
		assertNotNull(givenOutput);
		Set<Bike> expectedBikes = new HashSet<>();
		assertNotEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByBrakesWhenBrakesDoesntExist() {
		Set<Bike> givenOutput = bikeDao.getByBrakes("QQ7");
		Set<Bike> expectedBikes = new HashSet<>();
		assertEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByWheelsWhenWheelsDoesExist() {
		Set<Bike> givenOutput = bikeDao.getByWheels("steel");
		assertNotNull(givenOutput);
		Set<Bike> expectedBikes = new HashSet<>();
		assertNotEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByWheelsWhenWheelsDoesntExist() {
		Set<Bike> givenOutput = bikeDao.getByWheels("QQ7");
		Set<Bike> expectedBikes = new HashSet<>();
		assertEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByElecWhenTrue() {
		Set<Bike> givenOutput = bikeDao.getByElec(true);
		assertNotNull(givenOutput);
		Set<Bike> expectedBikes = new HashSet<>();
		assertNotEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByElecWhenFalse() {
		Set<Bike> givenOutput = bikeDao.getByElec(false);
		assertNotNull(givenOutput);
		Set<Bike> expectedBikes = new HashSet<>();
		assertNotEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByFrameSizeWhenFrameSizeDoesExist() {
		Set<Bike> givenOutput = bikeDao.getByFrameSize(25);
		assertNotNull(givenOutput);
		Set<Bike> expectedBikes = new HashSet<>();
		assertNotEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByFrameSizeWhenFrameSizeDoesntExist() {
		Set<Bike> givenOutput = bikeDao.getByFrameSize(987654321);
		Set<Bike> expectedBikes = new HashSet<>();
		assertEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getBySpeedsWhenSpeedsDoesExist() {
		Set<Bike> givenOutput = bikeDao.getBySpeeds(7);
		assertNotNull(givenOutput);
		Set<Bike> expectedBikes = new HashSet<>();
		assertNotEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getBySpeedsWhenSpeedsDoesntExist() {
		Set<Bike> givenOutput = bikeDao.getBySpeeds(987654321);
		Set<Bike> expectedBikes = new HashSet<>();
		assertEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getBypriceWhenPriceDoesExsit() {
		Set<Bike> givenOutput = bikeDao.getByPrice(198);
		assertNotNull(givenOutput);
		Set<Bike> expectedBikes = new HashSet<>();
		assertNotEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getBypriceWhenPriceDoesntExist() {
		Set<Bike> givenOutput = bikeDao.getByPrice(987654321);
		Set<Bike> expectedBikes = new HashSet<>();
		assertEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByPricesWhenPricesDoExist() {
		Set<Bike> givenOutput = bikeDao.getByPriceRange(100, 300);
		assertNotNull(givenOutput);
		Set<Bike> expectedBikes = new HashSet<>();
		assertNotEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByPricesWhenPricesDontExist() {
		Set<Bike> givenOutput = bikeDao.getByPriceRange(987654321, 987654321);
		Set<Bike> expectedBikes = new HashSet<>();
		assertEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByPricesWhenOnlyOnePriceEntered() {
		Set<Bike> givenOutput = bikeDao.getByPriceRange(280, 280);
		assertNotNull(givenOutput);
		Set<Bike> expectedBikes = new HashSet<>();
		assertNotEquals(expectedBikes, givenOutput);
	}
	@Test
	public void getByPricesWhenPriceEnteredBackwards() {
		Set<Bike> givenOutput = bikeDao.getByPriceRange(300, 100);
		Set<Bike> expectedBikes = new HashSet<>();
		assertEquals(expectedBikes, givenOutput);
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
