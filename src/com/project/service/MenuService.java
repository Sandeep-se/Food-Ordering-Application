package com.project.service;

import java.sql.ResultSet;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.DatabaseRepository;
import com.project.repository.MenuRepository;
import com.project.repository.MenuValidationRepository;

public class MenuService implements MenuRepository {
	private DatabaseRepository databaseOperation;
	private final MenuValidationRepository menuValidationRepository;
	
	public MenuService(DatabaseRepository databaseOperation, MenuValidationRepository menuValidationRepository) {
        this.databaseOperation = databaseOperation;
        this.menuValidationRepository = menuValidationRepository;
    }
	@Override
	public String createMenu(Object[] values) {
		if (!menuValidationRepository.checkMenuItemExistInMenu(new Object[]{values[1]})) {
            return "Food id is not exists in food list!";
        }
		boolean response=databaseOperation.executeUpdate(Queries.ADD_RESTAURANT_MENU.getQuery(), values);
		return response?"Menu item added successfully!":"Failed to add menu item.";
	}
	@Override
	public String deleteMenu(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.DELETE_RESTAURANT_MENU.getQuery(), values);
		return response?"Menu item deleted successfully!":"Failed to delete menu item. Menu ID might not exist.";
	}
	@Override
	public ResultSet getRestaurantMenu(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_RESTAURANTS_MENU.getQuery(), values);
		return resultSet;
	}

	@Override
	public String updateMenu(Object[] values) {
		boolean response=databaseOperation.executeUpdate(Queries.UPDATE_MENU_PRICE.getQuery(), values);
		return response?"Menu price updated successfully.":"Menu ID not found. Update failed.";
	}
	@Override
	public ResultSet searchFoodInMenu(Object[] values) {
		return databaseOperation.executeQuery(Queries.SEARCH_IN_MENU_BY_FOOD_NAME.getQuery(), values);
	}
	@Override
	public ResultSet searchFoodInMenuRestaurant(Object[] values) {
		return databaseOperation.executeQuery(Queries.SEARCH_BY_FOOD_IN_MENU_RESTAURANT.getQuery(), values);
	}
	
//	public ResultSet getMenu(Object[] values) {
//	ResultSet resultSet=databaseOperation.executeQuery(null, values);
//	return resultSet;
//}

}
