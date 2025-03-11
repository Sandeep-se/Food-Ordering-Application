package com.project.controller;

import java.sql.ResultSet;

import com.project.repository.RestaurantOrderRepository;

public class RestaurantOrderController {
	private RestaurantOrderRepository restaurantOrderRepository;
	
	public RestaurantOrderController(RestaurantOrderRepository restaurantOrderRepository) {
		this.restaurantOrderRepository=restaurantOrderRepository;
	}
	
	public ResultSet getRestaurantOrders(Object values[]) {
		ResultSet resultSet=restaurantOrderRepository.getRestaurantOrders(values);
		return resultSet;
	}
	
	public boolean updateOrderStatus(Object values[]) {
		boolean response=restaurantOrderRepository.updateOrderStatus(values);
		return response;
	}
}
