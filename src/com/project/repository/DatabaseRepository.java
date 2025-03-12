package com.project.repository;

import java.sql.ResultSet;
import java.util.List;

import com.project.model.Cart;

public interface DatabaseRepository {
	public boolean executeUpdate(String query,Object[] values) ;
	public boolean executeUpdate(String query);
	public ResultSet executeQuery(String query,Object[] values);
	public boolean executeBatch(String query,List<Cart> cartItems, Object values[]);
}
