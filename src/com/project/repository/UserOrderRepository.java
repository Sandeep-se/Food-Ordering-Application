package com.project.repository;

import java.sql.ResultSet;
import java.util.List;

import com.project.model.Cart;

public interface UserOrderRepository {
	public ResultSet getOrderHistory(Object[] values);
	public boolean makeOrder(List<Cart> cartItems, Object values[]);
	public ResultSet getOrderById(Object values[]);
}
