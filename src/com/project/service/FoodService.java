package com.project.service;

import java.sql.ResultSet;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;

public class FoodService {
	private DatabaseOperation databaseOperation;
	
	public FoodService(DatabaseOperation databaseOperation) {
		this.databaseOperation=databaseOperation;
	}
	public boolean createFood(Object[] values) {
		boolean response=databaseOperation.executeUpdate(null, values);
		return response;
	}

	public boolean updateFood(Object[] values) {
		boolean response=databaseOperation.executeUpdate(null, values);
		return response;
	}

	public boolean deleteFood(Object[] values) {
		boolean response=databaseOperation.executeUpdate(null, values);
		return response;
	}

	public ResultSet getAllFoodItem() {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_ALL_FOOD_ITEMS.getQuery(),new Object[] {});
		return resultSet;
	}

}
