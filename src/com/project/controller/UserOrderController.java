package com.project.controller;

import java.sql.ResultSet;
import java.util.List;

import com.project.model.Cart;
import com.project.repository.UserOrderRepository;
import com.project.service.UserOrderService;

public class UserOrderController {
	private UserOrderRepository userOrderRepository;
	
	public UserOrderController(UserOrderRepository userOrderRepository) {
		this.userOrderRepository=userOrderRepository;
	}
	
	public ResultSet getOrderHistory(Object values[]) {
		return userOrderRepository.getOrderHistory(values);
	}
	
	public boolean makeOrder(List<Cart> cartItems,Object values[]) {
		return userOrderRepository.makeOrder(cartItems,values);
	}
	
	public ResultSet getOrderById(Object []values) {
		return userOrderRepository.getOrderById(values);
	}

}

