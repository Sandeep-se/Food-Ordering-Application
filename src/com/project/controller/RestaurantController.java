package com.project.controller;

import java.sql.ResultSet;

import com.project.service.RestaurantService;

public class RestaurantController {
	private RestaurantService restaurantService;
	
	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService= restaurantService;
	}
	
	public boolean createRestaurant(Object values[]) {
		boolean response=restaurantService.createRestaurant(values);
		return response;
	}
	
	
	public ResultSet getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}
	
	public ResultSet getRestaurnatById(Object values[]) {
		return restaurantService.getRestaurantById(values);
	}
	
	public ResultSet searchRestaurantByName(Object []values) {
		return restaurantService.searchRestaurantByName(values);
	}
	public boolean deleteRestaurantsById(Object values[]) {
		return restaurantService.deleteRestaurantsById(values);
	}
	
	public ResultSet getRestaurantsMenu(Object values[]) {
		return restaurantService.getRestaurantsMenu(values);
	}
}
