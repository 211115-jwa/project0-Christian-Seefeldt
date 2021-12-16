package com.revature.data.DAO;

import com.revature.beans.Person;

public interface PersonDAO extends GenericDao<Person> {
	
	public Person getByUsername(String username);
	
//	public Person getById(int id);
	
//	public Person getByLastname(String lastName);
}
