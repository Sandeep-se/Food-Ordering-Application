package com.project.service;

import java.sql.ResultSet;
import java.util.List;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.model.Cart;

public class UserOrderService {
	
	private DatabaseOperation databaseOperation;
	
	public UserOrderService(DatabaseOperation databaseOperation) {
		this.databaseOperation=databaseOperation;
	}
	public ResultSet getOrderHistory(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_ADDRESS_BY_ID.getQuery(), values);
		return resultSet;
	}

	public boolean makeOrder(List<Cart> cartItems, Object values[]) {
		boolean response=databaseOperation.executeBatch(Queries.MAKE_ORDERS.getQuery(),cartItems,values);
		return response;
	}
	public boolean updateOrder(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.UPDATE_ORDERS.getQuery(), values);	
		return response;
	}
	
}
