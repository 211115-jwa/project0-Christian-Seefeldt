package com.revature.data.DAO;

import java.util.Set;

import com.revature.beans.Bike;

public interface BikeDAO extends GenericDao<Bike> {
	
	public Set<Bike> getByAvalibility(String avalibility);

	public Set<Bike> getByBrand(String brand);
	

}
