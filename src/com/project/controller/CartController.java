package com.project.controller;

import java.sql.ResultSet;

import com.project.database.Queries;
import com.project.service.CartService;

public class CartController {
	private CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService=cartService;
	}
	
	public boolean addItemInCart(Object values[]) {
		return cartService.addItemInCart(values);
	} 
	
	public boolean removeItemInCart(Object values[]) {
		return cartService.removeItemInCart(values);
	}
	
	public ResultSet getItemInCart(Object values[]) {
		return cartService.getItemInCart(values);
	}
	
	public boolean clearCart(Object values[]) {
		return cartService.clearCart(values);
	}
	
	public boolean increaseQuantity(Object[] values) {
		boolean response=cartService.increaseQuantity(values);
		return response;
	}
	
	public boolean decreaseQuantity(Object values[]) {
		boolean response=cartService.decreaseQuantity(values);
		return response;
	}
}
