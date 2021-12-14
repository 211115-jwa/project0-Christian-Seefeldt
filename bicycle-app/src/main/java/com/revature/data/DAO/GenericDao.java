package com.revature.data.DAO;

import java.util.List;
import java.util.Set;

public interface GenericDao<D> {
	public int create(D dataToAdd);
	public D getById(int id);
	public Set<D> getAll();
	public void update(D dataToUpdate);
	public void delete(D dataToDelete);
}
