// this is probably not useful

package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.revature.beans.Bike;
import com.revature.data.DAO.BikeDAO;
import com.revature.data.DAO.PersonDAO;

@ExtendWith(MockitoExtension.class)
public class BikeServicesTest {
	
	@Mock
	private BikeDAO bikeDao;
	
	@Mock 
	private PersonDAO personDao;
	
	@InjectMocks
	private UserService userServ = new UserServiceImpl();
	
	private static Set<Bike> mockAvalibleBikes;
	
	@BeforeAll
	public static void mockAvalibleBrandsSetup() {
		mockAvalibleBikes = new HashSet<>();
		
		for (int i=1; i<=5; i ++) {
			Bike bike = new Bike();
			if (i<3)
				bike.setBrand("placeholder");
			mockAvalibleBikes.add(bike);
		}
	}
	
	@Test
	public void searchByBrandExists() {
		String brand = "placeholder";
		
		when(bikeDao.getAll()).thenReturn(mockAvalibleBikes);
		
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
		String brand = "car";
		
		when(bikeDao.getAll()).thenReturn(mockAvalibleBikes);
		
		Set<Bike> actualBikes = userServ.searchAvailablebikesByBrand(brand);
		assertTrue(actualBikes.isEmpty());
	}
	
	
}
