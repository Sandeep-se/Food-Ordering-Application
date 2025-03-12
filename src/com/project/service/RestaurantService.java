package com.project.service;

import java.sql.ResultSet;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.DatabaseRepository;
import com.project.repository.EmailValidationRepository;
import com.project.repository.RestaurantRepository;

public class RestaurantService  implements RestaurantRepository{
	private final DatabaseRepository databaseOperation;
    private final EmailValidationRepository emailValidationRepository;

    public RestaurantService(DatabaseRepository databaseOperation, EmailValidationRepository emailValidationRepository) {
        this.databaseOperation = databaseOperation;
        this.emailValidationRepository = emailValidationRepository;
    }
	
	public boolean createRestaurant(Object values[]) {
		if (emailValidationRepository.checkEmailExists(new Object[]{values[2]})) {
	        System.out.println("Email already exists!");
	        return false;
	    }
		boolean response=databaseOperation.executeUpdate(Queries.RESTAURANT_REGITSER.getQuery(), values);
		return response;
	}
	 
	public ResultSet getAllRestaurants() {
		return databaseOperation.executeQuery(Queries.LIST_RESTAURANTS.getQuery(),new Object[] {});
	}
	
	public ResultSet getRestaurantById(Object values[]) {
		return databaseOperation.executeQuery(Queries.RESTAURANT_BY_ID.getQuery(), values);
	}
	
	public ResultSet searchRestaurantByName(Object []values) {
		return databaseOperation.executeQuery(Queries.RESTAURANT_BY_NAME.getQuery(), values);
	}
	
	public boolean deleteRestaurantsById(Object values[]) {
		return databaseOperation.executeUpdate(Queries.DELETE_RESTAURANT.getQuery(), values);
	}

	public ResultSet getRestaurantsMenu(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_RESTAURANTS_MENU.getQuery(), values);
		return resultSet;
		
	}
}

//public ResultSet login(Object[] values) {
//	ResultSet resultSet=databaseOperation.executeQuery(Queries.RESTAURANT_LOGIN.getQuery(), values);
//	return resultSet;
//}
