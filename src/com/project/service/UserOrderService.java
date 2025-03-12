package com.project.service;

import java.sql.ResultSet;
import java.util.List;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.model.Cart;
import com.project.repository.DatabaseRepository;
import com.project.repository.UserOrderRepository;

public class UserOrderService implements UserOrderRepository {
	
	private DatabaseRepository databaseOperation;
	
	public UserOrderService(DatabaseRepository databaseOperation) {
		this.databaseOperation=databaseOperation;
	}
	@Override
	public ResultSet getOrderHistory(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_ORDERS.getQuery(), values);
		return resultSet;
	}
	@Override
	public boolean makeOrder(List<Cart> cartItems, Object values[]) {
		boolean response=databaseOperation.executeBatch(Queries.MAKE_ORDERS.getQuery(),cartItems,values);
		return response;
	}
	@Override
	public ResultSet getOrderById(Object values[]) {
		ResultSet response=databaseOperation.executeQuery(Queries.GET_ORDER_BY_ID.getQuery(), values);
		return response;
	}
	
}
