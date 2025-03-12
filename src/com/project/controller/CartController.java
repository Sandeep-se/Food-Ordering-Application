package com.project.controller;

import java.sql.ResultSet;

import com.project.database.Queries;
import com.project.repository.CartRepository;
import com.project.service.CartService;

public class CartController {
	private CartRepository cartRepository;
	
	public CartController(CartRepository cartRepository) {
		this.cartRepository=cartRepository;
	}
	
	public String addItemInCart(Object values[]) {
		return cartRepository.addItemInCart(values);
	} 
	
	public String removeItemInCart(Object values[]) {
		return cartRepository.removeItemInCart(values);
	}
	
	public ResultSet getItemInCart(Object values[]) {
		return cartRepository.getItemInCart(values);
	}
	
	public String clearCart(Object values[]) {
		return cartRepository.clearCart(values);
	}
	
	public String increaseQuantity(Object[] values) {
		return cartRepository.increaseQuantity(values);
	}
	
	public String decreaseQuantity(Object values[]) {
		return cartRepository.decreaseQuantity(values);
	}
}
