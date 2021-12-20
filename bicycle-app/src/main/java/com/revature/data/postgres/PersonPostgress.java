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
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public int create(Person dataToAdd) {
		int generatedId = 0;
		
		
		try (Connection conn = connUtil.getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "insert into person (id,usr,psw,firstName,lastName,userRole) "
					+ "values (default, ?, ?, ?, ?, ?)";
			String[] keys = {"id"}; 
			
			PreparedStatement pStmt = conn.prepareStatement(sql, keys);
			pStmt.setString(1, dataToAdd.getUsername()); 
			pStmt.setString(2, dataToAdd.getPassword());
			pStmt.setString(3, dataToAdd.getFirstName());
			pStmt.setString(4, dataToAdd.getLastName());
			pStmt.setString(5, dataToAdd.getRole());
			
			pStmt.executeUpdate();
			ResultSet resultSet = pStmt.getGeneratedKeys();
			
			if (resultSet.next()) { 
				generatedId = resultSet.getInt("id");
				conn.commit(); 
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return generatedId;
	}

	@Override
	public Set<Person> getAll() {
		Set<Person> allPeople = new HashSet<>();
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from person";
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			

			while (resultSet.next()) {
				
				Person person = new Person();
				
				person.setId(resultSet.getInt("id"));
				person.setUsername(resultSet.getString("usr"));
				person.setPassword(resultSet.getString("psw"));
				person.setFirstName(resultSet.getString("firstname"));
				person.setLastName(resultSet.getString("lastname"));
				person.setRole(resultSet.getString("userRole"));

				person.setBikes(getBikesByOwner(conn, person.getId()));
				
				allPeople.add(person);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allPeople;
	}


	@Override
	public void update(Person dataToUpdate) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "update person set "
					+ "usr=?,psw=?,firstname=?,lastname=?,userrole=? "
					+ "where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, dataToUpdate.getUsername()); 
			pStmt.setString(2, dataToUpdate.getPassword());
			pStmt.setString(3, dataToUpdate.getFirstName());
			pStmt.setString(4, dataToUpdate.getLastName());
			pStmt.setString(5, dataToUpdate.getRole());
			pStmt.setInt(6, dataToUpdate.getId());
			
			int rowsAffected = pStmt.executeUpdate();
			
			boolean bikesUpdated = addNewOwnedBikes(conn, dataToUpdate);
			
			if (rowsAffected<=1 && bikesUpdated) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Person dataToDelete) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "delete from person "
					+ "where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, dataToDelete.getId());
			
			int rowsAffected = pStmt.executeUpdate();
			
			if (rowsAffected==1) {
				sql="delete from pet_owner where owner_id=?";
				PreparedStatement pStmt2 = conn.prepareStatement(sql);
				pStmt2.setInt(1, dataToDelete.getId());
				rowsAffected = pStmt2.executeUpdate();
				
				if (rowsAffected==dataToDelete.getBikes().size()) {
					conn.commit();
				} else {
					conn.rollback();
				}
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public Person getById(int id) {
		Person person = null;
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from person where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				person = new Person();
				person.setId(id);
				person.setUsername(resultSet.getString("usr"));
				person.setPassword(resultSet.getString("psw"));
				person.setFirstName(resultSet.getString("firstName"));
				person.setLastName(resultSet.getString("lastName"));
				person.setRole(resultSet.getString("userRole"));
				
				person.setBikes(getBikesByOwner(conn, person.getId()));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return person;
	}


	@Override
	public Person getByUsername(String usr) {
		Person person = null;
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from person where usr=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, usr);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				person = new Person();
				person.setId(resultSet.getInt("id"));
				person.setUsername(resultSet.getString("usr"));
				person.setPassword(resultSet.getString("psw"));
				person.setFirstName(resultSet.getString("firstName"));
				person.setLastName(resultSet.getString("lastName"));
				person.setRole(resultSet.getString("userRole"));
				
				person.setBikes(getBikesByOwner(conn, person.getId()));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return person;
	}

	@Override
	public Person getByLastname(String lastName) {
		Person person = null;
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from person where lastname=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, lastName);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				person = new Person();
				person.setId(resultSet.getInt("id"));
				person.setUsername(resultSet.getString("usr"));
				person.setPassword(resultSet.getString("psw"));
				person.setFirstName(resultSet.getString("firstName"));
				person.setLastName(resultSet.getString("lastName"));
				person.setRole(resultSet.getString("userRole"));
				
				person.setBikes(getBikesByOwner(conn, person.getId()));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return person;
	}

	private List<Bike> getBikesByOwner(Connection conn, int personId) throws SQLException {
		List<Bike> bikes = new LinkedList<>();
		
		String sql = "select * from bike join bike_owner on bike.id=bike_owner.bike_id where person_id=?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, personId);
		
		ResultSet resultSet = pStmt.executeQuery();
		
		while (resultSet.next()) {
			Bike bike = new Bike();
			bike.setId(resultSet.getInt("id"));
			bike.setBrand(resultSet.getString("brand"));
			bike.setModel(resultSet.getString("model"));
			bike.setColor(resultSet.getString("color"));
			bike.setBrakes(resultSet.getString("brakes"));
			bike.setWheels(resultSet.getString("wheels"));
			bike.setAvailable(resultSet.getString("available"));
			bike.setElectric(resultSet.getBoolean("electric"));
			bike.setFrameSize(resultSet.getInt("frameSize"));
			bike.setSpeeds(resultSet.getInt("speeds"));
			bike.setPrice(resultSet.getFloat("price"));
			
			bikes.add(bike);
		}
		
		return bikes;
	}
	
	private boolean addNewOwnedBikes(Connection conn, Person person) throws SQLException {
		String sql = "insert into bike_owner (bike_id,person_id) values ";
		for (int i = 0; i < person.getBikes().size(); i++) {
			sql += "(?,?)";
			if (i!=person.getBikes().size()-1) sql+= ",";
		}
		
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		int parameterIndex = 1;
		for (Bike bike : person.getBikes()) {
			pStmt.setInt(parameterIndex++, bike.getId());
			pStmt.setInt(parameterIndex++, person.getId());
		}
		
		int rowsAffected = pStmt.executeUpdate();
		if (rowsAffected==person.getBikes().size()) {
			return true;
		}
		return false;
	}
}


