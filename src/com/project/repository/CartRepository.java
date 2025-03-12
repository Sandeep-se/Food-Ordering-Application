package com.project.repository;

import java.sql.ResultSet;

public interface CartRepository {
	public String addItemInCart(Object[] values);
	public String removeItemInCart(Object[] values);
	public ResultSet getItemInCart(Object[] values);
	public String clearCart(Object[] values);
	public String increaseQuantity(Object[] values);
	public String decreaseQuantity(Object values[]);
}
