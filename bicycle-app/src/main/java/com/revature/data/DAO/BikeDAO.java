package com.revature.data.DAO;

import java.util.Set;

import com.revature.beans.Bike;

public interface BikeDAO extends GenericDao<Bike> {
	
	public Bike getById(int id);
	
	public Set<Bike> getByAvalible(boolean avalible);
	
	public Set<Bike> getByBrand(String brand);
	
	public Set<Bike> getByModel(String model);
	
	public Set<Bike> getByColor(String color);
	
	public Set<Bike> getByBrakes(String brakes);
	
	public Set<Bike> getByWheels(String wheels);
	
	public Set<Bike> getByElec(boolean electric);
	
	public Set<Bike> getByFrameSize(int frameSize);
	
	public Set<Bike> getBySpeeds(int speeds);
	
	public Set<Bike> getByPrice(int price);
	
	public Set<Bike> getByPriceRange(int price, int price1);
	
	

}
