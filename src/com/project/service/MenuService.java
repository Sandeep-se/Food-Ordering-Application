package com.project.service;

import java.sql.ResultSet;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.MenuRepository;

public class MenuService implements MenuRepository {
	private DatabaseOperation databaseOperation;
	
	public MenuService(DatabaseOperation databaseOperation) {
		this.databaseOperation=databaseOperation;
	}
	@Override
	public boolean createMenu(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.ADD_RESTAURANT_MENU.getQuery(), values);
		return response;
	}
	@Override
	public boolean deleteMenu(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.DELETE_RESTAURANT_MENU.getQuery(), values);
		return response;
	}
	@Override
	public ResultSet getRestaurantMenu(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_RESTAURANTS_MENU.getQuery(), values);
		return resultSet;
	}

//	public ResultSet getMenu(Object[] values) {
//		ResultSet resultSet=databaseOperation.executeQuery(null, values);
//		return resultSet;
//	}
	@Override
	public boolean updateMenu(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.UPDATE_MENU_PRICE.getQuery(), values);
		return response;
	}
	@Override
	public ResultSet searchFoodInMenu(Object[] values) {
		return databaseOperation.executeQuery(Queries.SEARCH_IN_MENU_BY_FOOD_NAME.getQuery(), values);
	}
	@Override
	public ResultSet searchFoodInMenuRestaurant(Object[] values) {
		return databaseOperation.executeQuery(Queries.SEARCH_BY_FOOD_IN_MENU_RESTAURANT.getQuery(), values);
	}

}
