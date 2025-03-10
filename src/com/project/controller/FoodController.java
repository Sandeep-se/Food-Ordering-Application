package com.project.controller;

import java.sql.ResultSet;

import com.project.service.FoodService;

public class FoodController {
	private FoodService foodService;
	public FoodController(FoodService foodService) {
		this.foodService=foodService;
	}
	
	public boolean createFood(Object values[]) {
		return foodService.createFood(values);
	}
	
	public boolean updateFood(Object values[]) {
		return foodService.updateFood(values);
	}
	
	public boolean deleteFoodItem(Object values[]) {
		return foodService.deleteFood(values);
	}
	public ResultSet getAllFoodItem() {
		return foodService.getAllFoodItem();
	}
}
