package com.revature.data.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.data.DAO.BikeDAO;
import com.revature.utils.ConnectionUtil;

public class BikePostgres implements BikeDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	@Override
	public int create(Bike dataToAdd) {
		int generatedID = 0;
		
		try (Connection conn = connUtil.getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "insert into bike (id, brand, bike_style, color, brakes, wheels, electric, avalibility_id, frameSize, speeds, price) "
					+ "values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			String[] keys = {"id"};
			
			PreparedStatement pStmt = conn.prepareStatement(sql, keys);
			
			pStmt.setString(1, dataToAdd.getBrand());
			pStmt.setString(2, dataToAdd.getStyle());
			pStmt.setString(3, dataToAdd.getColor());
			pStmt.setString(4, dataToAdd.getBrakes());
			pStmt.setString(5, dataToAdd.getWheels());
			pStmt.setBoolean(6, dataToAdd.isElectric());
			pStmt.setBoolean(11, dataToAdd.isAvalible());
			pStmt.setInt(8, dataToAdd.getFrameSize());
			pStmt.setInt(9, dataToAdd.getSpeeds());
			pStmt.setInt(10, dataToAdd.getPrice());
			
			
			pStmt.executeUpdate();
			ResultSet resultSet = pStmt.getGeneratedKeys();
			
			if (resultSet.next()) {
				
				generatedID = resultSet.getInt("id");
				
				conn.commit();
			
			} else {
				conn.rollback();
			}
			
		}	catch (SQLException e) {
			e.printStackTrace();
		}
		
		return generatedID;
	}

	@Override
	public Bike getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Bike> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Bike dataToUpdate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Bike dataToDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Bike> getByAvalibility(String avalibility) {
		// TODO Auto-generated method stub
		return null;
	}
}
