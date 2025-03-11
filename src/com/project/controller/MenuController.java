package com.project.controller;

import java.sql.ResultSet;

import com.project.repository.MenuRepository;

public class MenuController {
	private MenuRepository menuRepository;
	
	public MenuController(MenuRepository menuRepository) {
		this.menuRepository=menuRepository;
	}
	
	public boolean createMenu(Object values[]) {
		return  menuRepository.createMenu(values);
	}
	
	public boolean updateMenu(Object values[]) {
		return menuRepository.updateMenu(values);
	}
	
	public boolean deleteMenu(Object values[]) {
		return menuRepository.deleteMenu(values);
	}
	
	public ResultSet getRestaurantMenu(Object values[]) {
		return menuRepository.getRestaurantMenu(values);
	}
	
//	public ResultSet getMenu(Object values[]) {
//		return menuRepository.getMenu(values);
//	}
	
	public ResultSet searchFoodInMenu(Object []values) {
		return menuRepository.searchFoodInMenu(values);
	}
	
	public ResultSet searchFoodInMenuRestaurant(Object[] values) {
		return menuRepository.searchFoodInMenuRestaurant( values);
	}
	
	
}
