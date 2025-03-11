package com.project.controller;

import java.sql.ResultSet;

import com.project.repository.RestaurantRepository;

public class RestaurantController {
	private RestaurantRepository restaurantRepository;
	
	public RestaurantController(RestaurantRepository restaurantRepository) {
		this.restaurantRepository= restaurantRepository;
	}
	
	public boolean createRestaurant(Object values[]) {
		boolean response=restaurantRepository.createRestaurant(values);
		return response;
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
	
	public ResultSet login(Object values[]) {
		return restaurantRepository.login(values);
	}
	public boolean deleteRestaurantsById(Object values[]) {
		return restaurantRepository.deleteRestaurantsById(values);
	}
	
	public ResultSet getRestaurantsMenu(Object values[]) {
		return restaurantRepository.getRestaurantsMenu(values);
	}
}
