package com.project.service;

import java.sql.ResultSet;

import com.project.database.DatabaseOperation;

public class RestaurantOrderService {

	private DatabaseOperation databaseOperation;
	
	public RestaurantOrderService(DatabaseOperation databaseOperation) {
		this.databaseOperation=databaseOperation;
	}
	public ResultSet getRestaurantOrders(Object[] values) {
		
		ResultSet resultSet=databaseOperation.executeQuery(null, values);
		return resultSet;
	}

	public boolean updateOrderStatus(Object[] values) {
		boolean response=databaseOperation.executeUpdate(null, values);
		return response;
	}

}
