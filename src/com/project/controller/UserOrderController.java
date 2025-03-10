package com.project.controller;

import java.sql.ResultSet;
import java.util.List;

import com.project.model.Cart;
import com.project.service.UserOrderService;

public class UserOrderController {
	private UserOrderService userOrderService;
	
	public UserOrderController(UserOrderService userOrderService) {
		this.userOrderService=userOrderService;
	}
	
	public ResultSet getOrderHistory(Object values[]) {
		return userOrderService.getOrderHistory(values);
	}
	
	public boolean makeOrder(List<Cart> cartItems,Object values[]) {
		return userOrderService.makeOrder(cartItems,values);
	}
	
	public boolean updateOrder(Object values[]) {
		return userOrderService.updateOrder(values);
	}

}

