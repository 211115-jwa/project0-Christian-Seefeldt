package com.revature.data.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
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
			
			String sql = "insert into bike (id, brand, model, color, brakes, wheels, available, electric, frameSize, speeds, price) "
					+ "values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			String[] keys = {"id"};
			
			PreparedStatement pStmt = conn.prepareStatement(sql, keys);
			
			pStmt.setString(1, dataToAdd.getBrand());
			pStmt.setString(2, dataToAdd.getModel());
			pStmt.setString(3, dataToAdd.getColor());
			pStmt.setString(4, dataToAdd.getBrakes());
			pStmt.setString(5, dataToAdd.getWheels());
			pStmt.setString(6, dataToAdd.isAvailable());
			pStmt.setBoolean(7, dataToAdd.isElectric());
			pStmt.setInt(8, dataToAdd.getFrameSize());
			pStmt.setInt(9, dataToAdd.getSpeeds());
			pStmt.setDouble(10, dataToAdd.getPrice());
			
			
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
		Bike bike = null;
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				bike = new Bike();
				bike.setId(id);
				bike.setBrand(resultSet.getString("brand"));
				bike.setModel(resultSet.getString("model"));
				bike.setColor(resultSet.getString("color"));
				bike.setBrakes(resultSet.getString("brakes"));
				bike.setWheels(resultSet.getString("wheels"));
				bike.setAvailable(resultSet.getString("available"));
				bike.setElectric(resultSet.getBoolean("electric"));
				bike.setFrameSize(resultSet.getInt("frameSize"));
				bike.setSpeeds(resultSet.getInt("speeds"));
				bike.setPrice(resultSet.getDouble("price"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bike;
	}


	@Override
	public Set<Bike> getAll() {
		Set<Bike> allBikes = new HashSet<>();
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike";
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			
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
				bike.setPrice(resultSet.getDouble("price"));
				
				allBikes.add(bike);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allBikes;
	}


	@Override
	public void update(Bike dataToUpdate) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "update bike set "
					+ "brand=?,model=?,color=?,brakes=?,wheels=?,available=?,electric=?,frameSize=?,speeds=?,Price=? "
					+ "where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, dataToUpdate.getBrand());
			pStmt.setString(2, dataToUpdate.getModel());
			pStmt.setString(3, dataToUpdate.getColor());
			pStmt.setString(4, dataToUpdate.getBrakes());
			pStmt.setString(5, dataToUpdate.getWheels());
			pStmt.setString(6, dataToUpdate.isAvailable());
			pStmt.setBoolean(7, dataToUpdate.isElectric());
			pStmt.setInt(8, dataToUpdate.getFrameSize());
			pStmt.setInt(9, dataToUpdate.getSpeeds());
			pStmt.setDouble(10, dataToUpdate.getPrice());
			
			
			int rowsAffected = pStmt.executeUpdate();
			
			if (rowsAffected==1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void delete(Bike dataToDelete) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);

			String sql = "delete from bike "
					+ "where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, dataToDelete.getId());

			int rowsAffected = pStmt.executeUpdate();

			if (rowsAffected==1) {
				sql="delete from bike_owner where bike_id=?";
				PreparedStatement pStmt2 = conn.prepareStatement(sql);
				pStmt2.setInt(1, dataToDelete.getId());
				rowsAffected = pStmt2.executeUpdate();
				
				if (rowsAffected<=1) {
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
	public Set<Bike> getByAvailable(String available) {
		Set<Bike> allBikes = new HashSet<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where available=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, available);
	
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
				bike.setPrice(resultSet.getDouble("price"));
				
				allBikes.add(bike);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allBikes;
	}


	@Override
	public Set<Bike> getByBrand(String brand) {
		Set<Bike> allBikes = new HashSet<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where brand=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, brand);
	
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
				bike.setPrice(resultSet.getDouble("price"));

				allBikes.add(bike);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allBikes;
	}

	@Override
	public Set<Bike> getByModel(String model) {
		Set<Bike> allBikes = new HashSet<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where model=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, model);
	
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
				bike.setPrice(resultSet.getDouble("price"));

				allBikes.add(bike);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allBikes;
	}

	@Override
	public Set<Bike> getByColor(String color) {
		Set<Bike> allBikes = new HashSet<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where color=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, color);
	
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
				bike.setPrice(resultSet.getDouble("price"));

				allBikes.add(bike);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allBikes;
	}

	@Override
	public Set<Bike> getByBrakes(String brakes) {
		Set<Bike> allBikes = new HashSet<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where brakes=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, brakes);
	
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
				bike.setPrice(resultSet.getDouble("price"));

				allBikes.add(bike);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allBikes;
	}

	@Override
	public Set<Bike> getByWheels(String wheels) {
		Set<Bike> allBikes = new HashSet<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where wheels=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, wheels);
	
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
				bike.setPrice(resultSet.getDouble("price"));
				
				allBikes.add(bike);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allBikes;
	}

	@Override
	public Set<Bike> getByElec(boolean electric) {
		Set<Bike> allBikes = new HashSet<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where electric=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setBoolean(1, electric);
	
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
				bike.setPrice(resultSet.getDouble("price"));
				

				allBikes.add(bike);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allBikes;
	}

	@Override
	public Set<Bike> getByFrameSize(int frameSize) {
		Set<Bike> allBikes = new HashSet<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where frameSize=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, frameSize);
	
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
				bike.setPrice(resultSet.getDouble("price"));

				allBikes.add(bike);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allBikes;
	}

	@Override
	public Set<Bike> getBySpeeds(int speeds) {
		Set<Bike> allBikes = new HashSet<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where speeds=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, speeds);
	
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
				bike.setPrice(resultSet.getDouble("price"));

				allBikes.add(bike);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allBikes;
	}
		@Override
		public Set<Bike> getByPrice(double price) {
			Set<Bike> allBikes = new HashSet<>();

			try (Connection conn = connUtil.getConnection()) {
				String sql = "select * from bike where price=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setDouble(1, price);
		
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
					bike.setPrice(resultSet.getDouble("price"));

					allBikes.add(bike);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return allBikes;
		}

		@Override
		public Set<Bike> getByPriceRange(double price, double price1) {
			Set<Bike> allBikes = new HashSet<>();

			try (Connection conn = connUtil.getConnection()) {
				String sql = "select * from bike where price>? intersect select * from bike where price<?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
		
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
					bike.setPrice(resultSet.getDouble("price"));

					allBikes.add(bike);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return allBikes;
		}
}
