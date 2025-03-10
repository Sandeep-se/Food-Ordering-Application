package com.project.service;

import java.sql.ResultSet;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;

public class CartService {
	private DatabaseOperation databaseOperation;
	
	public CartService(DatabaseOperation databaseOperation) {
		this.databaseOperation=databaseOperation;
	}
	
	public boolean addItemInCart(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.ADD_ITEM_IN_CART.getQuery(), values);
		return response;
	}

	public boolean removeItemInCart(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.REMOVE_ITEM_IN_CART.getQuery(), values);
		return response;
	}
  
	public ResultSet getItemInCart(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_CART_ITEMS.getQuery(), values);
		return resultSet;
	}

	public boolean clearCart(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.CLEAR_CART_ITEM.getQuery(), values);
		return response;
	}
	
	public boolean increaseQuantity(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.INCREMENT_QUANTITY.getQuery(), values);
		return response;
	}
	
	public boolean decreaseQuantity(Object values[]) {
		boolean response=databaseOperation.executeUpdate(Queries.DECREMENT_QUANTITY.getQuery(), values);
		return response;
	}
}

