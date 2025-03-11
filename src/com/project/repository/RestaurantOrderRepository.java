package com.project.repository;

import java.sql.ResultSet;

public interface RestaurantOrderRepository {
	public ResultSet getRestaurantOrders(Object[] values);
	public boolean updateOrderStatus(Object[] values);
}
