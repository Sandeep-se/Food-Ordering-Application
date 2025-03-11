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
	
	public boolean addItemInCart(Object values[]) {
		return cartRepository.addItemInCart(values);
	} 
	
	public boolean removeItemInCart(Object values[]) {
		return cartRepository.removeItemInCart(values);
	}
	
	public ResultSet getItemInCart(Object values[]) {
		return cartRepository.getItemInCart(values);
	}
	
	public boolean clearCart(Object values[]) {
		return cartRepository.clearCart(values);
	}
	
	public boolean increaseQuantity(Object[] values) {
		boolean response=cartRepository.increaseQuantity(values);
		return response;
	}
	
	public boolean decreaseQuantity(Object values[]) {
		boolean response=cartRepository.decreaseQuantity(values);
		return response;
	}
}
