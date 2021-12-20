package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Bike;
import com.revature.beans.Person;
import com.revature.data.DAO.PersonDAO;
import com.revature.data.postgres.PersonPostgress;

public class PersonDAOTest {
		private PersonDAO personDao = new PersonPostgress();
		
		@Test
		public void getAllNotNull() {
			Set<Person> actualPerson = personDao.getAll();
			assertNotEquals(null, actualPerson);
			Set<Person> expectedPeople = new HashSet<>();
			assertNotEquals(expectedPeople, actualPerson);
		}
		
		@Test
		public void getValidPersonById()
		{
			String expectedUsername = "CDS";
			Person actualUsername = personDao.getById(1);
			assertEquals(expectedUsername, actualUsername.getUsername());
		}
		
		@Test
		public void testGetIDNoID() {
			Person personOutput= personDao.getById(10000);
			assertNull(personOutput);
		}

		@Test
		public void testUpdate() {
			Person personUp = personDao.getById(1);
			personUp.setFirstName("Bob");
			personDao.update(personUp);
			assertEquals("Bob",personDao.getById(1).getFirstName());
		}
	
		@Test
		public void createTest() {
			Person create = new Person();
			assertNotEquals(0, personDao.create(create));
		}
		
		@Test
		public void getByUsernameWhenUsernameExists() {
			String usernameInput = "CDS";
			Person personOutput = personDao.getByUsername(usernameInput);
			assertEquals("CDS", personOutput.getUsername());
		}
		
		@Test
		public void getByUsernameButUsernameDoesNotExist() {
			String usernameInput = "qwertyuiop";
			Person personOutput = personDao.getByUsername(usernameInput);
			assertNull(personOutput);
		}
}
