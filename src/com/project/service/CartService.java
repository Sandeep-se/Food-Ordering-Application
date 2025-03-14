package com.project.service;

import java.sql.ResultSet;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.CartRepository;
import com.project.repository.CartValidationRepository;
import com.project.repository.DatabaseRepository;
import com.project.repository.MenuValidationRepository;

public class CartService implements CartRepository{
	private final DatabaseRepository databaseOperation;
    private final CartValidationRepository cartValidationRepository;
    private final MenuValidationRepository menuValidationRepository;

    public CartService(DatabaseRepository databaseOperation, CartValidationRepository cartValidationRepository,MenuValidationRepository menuValidationRepository) {
        this.databaseOperation = databaseOperation;
        this.cartValidationRepository = cartValidationRepository;
        this.menuValidationRepository = menuValidationRepository;
    }
	@Override
	public String addItemInCart(Object[] values) {
		try {
			
			if(!menuValidationRepository.checkMenuItemExistInMenu(new Object[]{values[1]})) {
                return "Menu item does not exist.";
            }
			
            if (cartValidationRepository.checkMenuExists(new Object[]{values[0], values[1]})) {
            	System.out.println("Menu item already exists, updating quantity...");
                return increaseQuantity(new Object[] {values[2],values[0],values[1]});
            } 
            else {
            	
                boolean response=databaseOperation.executeUpdate(Queries.ADD_ITEM_IN_CART.getQuery(), values);
                return response?"Succesfully added item in cart":"added failed from cart";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "addinting item in cart failed";
	}
	@Override
	public String removeItemInCart(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.REMOVE_ITEM_IN_CART.getQuery(), values);
		return response?"Successfully removed item in cart":"removing item is failed";
	}
	@Override
	public ResultSet getItemInCart(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_CART_ITEMS.getQuery(), values);
		return resultSet;
	}
	@Override
	public String clearCart(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.CLEAR_CART_ITEM.getQuery(), values);
		return response?"cleared cart is successful":"clear cart is bail";
	}
	@Override
	public String increaseQuantity(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.INCREMENT_QUANTITY.getQuery(), values);
		return response?"successfully incremented":"increaes quantity fail";
	}
	@Override
	public String decreaseQuantity(Object values[]) {
		boolean response=databaseOperation.executeUpdate(Queries.DECREMENT_QUANTITY.getQuery(), values);
		return response?"Succesfull decreased quantity by 1":"quantity should be greater than 1 (use remove)";
	}
}

