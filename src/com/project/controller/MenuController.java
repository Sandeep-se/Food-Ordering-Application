package com.project.controller;

import java.sql.ResultSet;

import com.project.service.MenuService;

public class MenuController {
	private MenuService menuService;
	
	public MenuController(MenuService menuService) {
		this.menuService=menuService;
	}
	
	public boolean createMenu(Object values[]) {
		return  menuService.createMenu(values);
	}
	
	public boolean updateMenu(Object values[]) {
		return menuService.updateMenu(values);
	}
	
	public boolean deleteMenu(Object values[]) {
		return menuService.deleteMenu(values);
	}
	
	public ResultSet getRestaurantMenu(Object values[]) {
		return menuService.getRestaurantMenu(values);
	}
	
	public ResultSet getMenu(Object values[]) {
		return menuService.getMenu(values);
	}
	
	public ResultSet searcFoodInMenu(Object []values) {
		return menuService.searcFoodInMenu(values);
	}
	
	
}
