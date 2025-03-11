package com.project.repository;

import java.sql.ResultSet;

public interface CartRepository {
	public boolean addItemInCart(Object[] values);
	public boolean removeItemInCart(Object[] values);
	public ResultSet getItemInCart(Object[] values);
	public boolean clearCart(Object[] values);
	public boolean increaseQuantity(Object[] values);
	public boolean decreaseQuantity(Object values[]);
}
