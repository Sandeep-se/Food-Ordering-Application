package com.project.repository;

import java.sql.ResultSet;

public interface MenuRepository {
	public String createMenu(Object[] values);
	public String deleteMenu(Object[] values);
	public ResultSet getRestaurantMenu(Object[] values);
	public String updateMenu(Object[] values);
	public ResultSet searchFoodInMenu(Object[] values);
	public ResultSet searchFoodInMenuRestaurant(Object[] values);
}
