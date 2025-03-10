package com.project.service;

import java.sql.ResultSet;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;

public class MenuService {
	private DatabaseOperation databaseOperation;
	
	public MenuService(DatabaseOperation databaseOperation) {
		this.databaseOperation=databaseOperation;
	}
	public boolean createMenu(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.ADD_RESTAURANT_MENU.getQuery(), values);
		return response;
	}

	public boolean deleteMenu(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.DELETE_RESTAURANT_MENU.getQuery(), values);
		return response;
	}

	public ResultSet getRestaurantMenu(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_RESTAURANTS_MENU.getQuery(), values);
		return resultSet;
	}

	public ResultSet getMenu(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(null, values);
		return resultSet;
	}
	
	public boolean updateMenu(Object[] values) {
		boolean response=databaseOperation.executeUpdate(null, values);
		return response;
	}
	public ResultSet searcFoodInMenu(Object[] values) {
		return databaseOperation.executeQuery(Queries.SEARCH_BY_FOOD_NAME.getQuery(), values);
	}

}
