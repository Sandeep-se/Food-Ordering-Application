package com.project.controller;

import java.sql.ResultSet;

import com.project.repository.RestaurantRepository;

public class RestaurantController {
	private RestaurantRepository restaurantRepository;
	
	public RestaurantController(RestaurantRepository restaurantRepository) {
		this.restaurantRepository= restaurantRepository;
	}
	
	public String createRestaurant(Object values[]) {
		return restaurantRepository.createRestaurant(values);
	}
	
	
	public ResultSet getAllRestaurants() {
		return restaurantRepository.getAllRestaurants();
	}
	
	public ResultSet getRestaurnatById(Object values[]) {
		return restaurantRepository.getRestaurantById(values);
	}
	
	public ResultSet searchRestaurantByName(Object []values) {
		return restaurantRepository.searchRestaurantByName(values);
	}
	
	public String deleteRestaurantsById(Object values[]) {
		return restaurantRepository.deleteRestaurantsById(values);
	}
	
//	public ResultSet getRestaurantsMenu(Object values[]) {
//		return restaurantRepository.getRestaurantsMenu(values);
//	}
}
