package com.project.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.project.model.Cart;
import com.project.repository.DatabaseRepository;

public class DatabaseOperation implements DatabaseRepository {
	
	private Connection connection;
	
	public DatabaseOperation() {
		connection=Jdbc.getConnection();
	};
	
	@Override
	public boolean executeUpdate(String query,Object[] values) {
		int row=0;
		try(PreparedStatement statement=connection.prepareStatement(query)){
			
			for(int i=1;i<=values.length;i++) {
				statement.setObject(i, values[i-1]);
			}
			
			row=statement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return row>0;
	}
	@Override 
	public boolean executeUpdate(String query) {
		int row=0;
		try(PreparedStatement statement=connection.prepareStatement(query)){
			
			row=statement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return row>0;
	}
	@Override
	public ResultSet executeQuery(String query,Object[] values) {
		ResultSet resultSet=null;
		try{
			PreparedStatement statement=connection.prepareStatement(query);
			for(int i=1;i<=values.length;i++) {
				statement.setObject(i, values[i-1]);
			}
			
			resultSet=statement.executeQuery();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public boolean executeBatch(String query, List<Cart> cartItems,Object values[]) {
		try (PreparedStatement statement = connection.prepareStatement(query)) {

		        connection.setAutoCommit(false); 

		        for (Cart cart : cartItems) {
		            statement.setInt(1, (int)values[0]);
		            statement.setInt(2, (int) values[1]);
		            statement.setInt(3, cart.getMenuId());
		            statement.setInt(4, cart.getQuantity());
		            statement.addBatch(); 
		        }

		        int[] rowsAffected = statement.executeBatch(); 
		        connection.commit(); 

		        return rowsAffected.length == cartItems.size();

		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
	}
}


