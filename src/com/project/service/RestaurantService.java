package com.project.service;

import java.sql.ResultSet;

import com.project.Validation;
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
	
	public String createRestaurant(Object values[]) {
		String name=(String) values[0];
		String location=(String)values[1];
		String email=(String)values[2];
		String password=(String)values[3];
		if(name.isEmpty() || email.isEmpty() || password.isEmpty() || location.isEmpty()) {
			return "All the fields are required";
		}
		if(!Validation.isValidEmail(email)) {
			return "Email should contain '@gmail.com'";
		}
		if (emailValidationRepository.checkEmailExists(new Object[]{email})) {
	        return "Email already exists!.try different email";
	    }
		boolean response = databaseOperation.executeUpdate(Queries.RESTAURANT_REGITSER.getQuery(), values);
		return response ? "Registration successful" : "Registration failed";
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
	
	public String deleteRestaurantsById(Object values[]) {
		boolean response=databaseOperation.executeUpdate(Queries.DELETE_RESTAURANT.getQuery(), values);
		return response?"deleted the item":"seletion fail";
	}

//	public ResultSet getRestaurantsMenu(Object[] values) {
//		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_RESTAURANTS_MENU.getQuery(), values);
//		return resultSet;
//		
//	}
}

//public ResultSet login(Object[] values) {
//	ResultSet resultSet=databaseOperation.executeQuery(Queries.RESTAURANT_LOGIN.getQuery(), values);
//	return resultSet;
//}
