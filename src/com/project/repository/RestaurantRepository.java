package com.project.repository;

import java.sql.ResultSet;

public interface RestaurantRepository {
	public String createRestaurant(Object values[]);
	public ResultSet getAllRestaurants();
	public ResultSet getRestaurantById(Object values[]);
	public ResultSet searchRestaurantByName(Object []values);
	public String deleteRestaurantsById(Object values[]);
//	public ResultSet getRestaurantsMenu(Object[] values);
}
