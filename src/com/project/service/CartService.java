package com.project.service;

import java.sql.ResultSet;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.CartRepository;

public class CartService implements CartRepository{
	private DatabaseOperation databaseOperation;
	
	public CartService(DatabaseOperation databaseOperation) {
		this.databaseOperation=databaseOperation;
	}
	@Override
	public boolean addItemInCart(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.ADD_ITEM_IN_CART.getQuery(), values);
		return response;
	}
	@Override
	public boolean removeItemInCart(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.REMOVE_ITEM_IN_CART.getQuery(), values);
		return response;
	}
	@Override
	public ResultSet getItemInCart(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_CART_ITEMS.getQuery(), values);
		return resultSet;
	}
	@Override
	public boolean clearCart(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.CLEAR_CART_ITEM.getQuery(), values);
		return response;
	}
	@Override
	public boolean increaseQuantity(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.INCREMENT_QUANTITY.getQuery(), values);
		return response;
	}
	@Override
	public boolean decreaseQuantity(Object values[]) {
		boolean response=databaseOperation.executeUpdate(Queries.DECREMENT_QUANTITY.getQuery(), values);
		return response;
	}
}

