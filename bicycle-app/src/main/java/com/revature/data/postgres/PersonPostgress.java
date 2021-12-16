package com.revature.data.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Bike;
import com.revature.data.DAO.PersonDAO;
import com.revature.utils.ConnectionUtil;


public class PersonPostgress implements PersonDAO {

	@Override
	public int create(Person dataToAdd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Person dataToUpdate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Person dataToDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getByLastname(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

}
