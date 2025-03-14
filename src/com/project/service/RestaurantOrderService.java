package com.project.service;

import java.sql.ResultSet;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.DatabaseRepository;
import com.project.repository.RestaurantOrderRepository;

public class RestaurantOrderService implements RestaurantOrderRepository{

	private DatabaseRepository databaseOperation;
	
	public RestaurantOrderService(DatabaseRepository databaseOperation) {
		this.databaseOperation=databaseOperation;
	}
	@Override
	public ResultSet getRestaurantOrders(Object[] values) {
		
		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_RESTAURANT_ORDERS.getQuery(), values);
		return resultSet;
	}
	@Override
	public boolean updateOrderStatus(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.UPDATE_ORDERS.getQuery(), values);
		return response;
	}

}
