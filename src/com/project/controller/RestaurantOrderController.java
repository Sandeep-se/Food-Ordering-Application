package com.project.controller;

import java.sql.ResultSet;

import com.project.service.RestaurantOrderService;

public class RestaurantOrderController {
	private RestaurantOrderService restaurantOrderService;
	
	public RestaurantOrderController(RestaurantOrderService restaurantOrderService) {
		this.restaurantOrderService=restaurantOrderService;
	}
	
	public ResultSet getRestaurantOrders(Object values[]) {
		ResultSet resultSet=restaurantOrderService.getRestaurantOrders(values);
		return resultSet;
	}
	
	public boolean updateOrderStatus(Object values[]) {
		boolean response=restaurantOrderService.updateOrderStatus(values);
		return response;
	}
}
