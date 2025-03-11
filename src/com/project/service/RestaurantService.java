package com.project.service;

import java.sql.ResultSet;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;

public class RestaurantService {
	private DatabaseOperation databaseOperation;
	
	public RestaurantService(DatabaseOperation databaseOperation) {
		this.databaseOperation=databaseOperation;
	}
	
	public boolean createRestaurant(Object values[]) {
		boolean response=databaseOperation.executeUpdate(Queries.RESTAURANT_REGITSER.getQuery(), values);
		return response;
	}
	 
	public ResultSet getAllRestaurants() {
		return databaseOperation.executeQuery(Queries.LIST_RESTAURANTS.getQuery(),new Object[] {});
	}
	
	public ResultSet getRestaurantById(Object values[]) {
		return databaseOperation.executeQuery(Queries.RESTAURANT_BY_ID.getQuery(), values);
	}
	
	public ResultSet searchRestaurantByName(Object []values) {
		return databaseOperation.executeQuery(Queries.RESTAURANT_BY_NAME.getQuery(), values);
	}
	
	public boolean deleteRestaurantsById(Object values[]) {
		return databaseOperation.executeUpdate(Queries.DELETE_RESTAURANT.getQuery(), values);
	}

	public ResultSet getRestaurantsMenu(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_RESTAURANTS_MENU.getQuery(), values);
		return resultSet;
		
	}

	public ResultSet login(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.RESTAURANT_LOGIN.getQuery(), values);
		return resultSet;
	}
}
