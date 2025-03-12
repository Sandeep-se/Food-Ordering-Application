package com.project.repository;

import java.sql.ResultSet;

public interface RestaurantRepository {
	public boolean createRestaurant(Object values[]);
	public ResultSet getAllRestaurants();
	public ResultSet getRestaurantById(Object values[]);
	public ResultSet searchRestaurantByName(Object []values);
	public boolean deleteRestaurantsById(Object values[]);
	public ResultSet getRestaurantsMenu(Object[] values);
}
