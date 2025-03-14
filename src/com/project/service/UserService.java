package com.project.service;


import java.sql.ResultSet;

import com.project.Validation;
import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.DatabaseRepository;
import com.project.repository.UserRepository;
import com.project.repository.UserValidationRepository;

public class UserService implements UserRepository{
	private final DatabaseRepository databaseOperation;
	private final UserValidationRepository userValidationRepository;

	
    public UserService(DatabaseRepository databaseOperation,UserValidationRepository userValidationRepository) {
        this.databaseOperation = databaseOperation;
        this.userValidationRepository = userValidationRepository;
    }
    @Override
    public String register(Object values[]) {
    	String name=(String) values[0];
    	String email = (String) values[1];
    	String password=(String) values[2];
    	String phoneNumber = (String) values[3];
    	
    	if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
	        return "All fields are required!";
	        
	    }
    	if (!Validation.isValidEmail(email)) {
            return "Invalid Email Format!";
        }
        if (!Validation.isValidPhoneNumber(phoneNumber)) {
            return "Invalid Phone Number Format!";
        }
        
    	if (userValidationRepository.checkEmailExists(new Object[]{email})) {
            return "Email already exists!.try different Email";
        }
    	if (userValidationRepository.checkPhoneNumberExists(new Object[]{phoneNumber})) {
            return "Phone number already exists!.try different phone number";
        }
    	boolean response=databaseOperation.executeUpdate(Queries.USER_REGISTER.getQuery(),values);
    	if(response) {
    		return "register success";
    	}
    	else {
    		return "database connection error";
    	}
    }
    @Override
    public String updateUser(Object values[]) {
    	
    	String name = (String) values[0];
        String email = (String) values[1];
        String phoneNumber = (String) values[2];
        Integer userId = (Integer) values[3];
        
        if (userId == null || userId <= 0) {
            return "Invalid User ID!";
        }
        if (!Validation.isValidEmail(email)) {
            return "Invalid Email Format!";
        }
        
        if (!Validation.isValidPhoneNumber(phoneNumber)) {
            return "Invalid Phone Number Format!";
        }
        
        if (userValidationRepository.checkEmailExists(new Object[]{email})) {
          return "Email already exists!.try different Email";
      }
    	boolean response=databaseOperation.executeUpdate(Queries.UPDATE_USER.getQuery(), values);
    	return response? "sussessfully updated":"updation failed";
    }
    
    public ResultSet viewUserProfile(Object values[]) {
        return databaseOperation.executeQuery(Queries.VIEW_USER_PROFILE.getQuery(), values);
    }
	
}

