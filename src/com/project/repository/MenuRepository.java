package com.project.repository;

import java.sql.ResultSet;

public interface MenuRepository {
	public boolean createMenu(Object[] values);
	public boolean deleteMenu(Object[] values);
	public ResultSet getRestaurantMenu(Object[] values);
	public boolean updateMenu(Object[] values);
	public ResultSet searchFoodInMenu(Object[] values);
	public ResultSet searchFoodInMenuRestaurant(Object[] values);
}
