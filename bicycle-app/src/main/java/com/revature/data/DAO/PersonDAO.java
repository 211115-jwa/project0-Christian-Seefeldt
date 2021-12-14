package com.revature.data.DAO;

import com.revature.beans.Person;

public interface PersonDAO extends GenericDao<Person> {
	public Person getById(int id);
}
