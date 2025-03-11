package com.project.service;


import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.UserRepository;
import com.project.repository.UserValidationRepository;

public class UserService implements UserRepository{
	private final DatabaseOperation databaseOperation;
	private final UserValidationRepository userValidationRepository;

	
    public UserService(DatabaseOperation databaseOperation,UserValidationRepository userValidationRepository) {
        this.databaseOperation = databaseOperation;
        this.userValidationRepository = userValidationRepository;
    }
    @Override
    public boolean register(Object values[]) {
    	String email = (String) values[2];
    	String phone = (String) values[3];
    	
    	if (userValidationRepository.checkUserEmailExists(new Object[]{email})) {
            System.out.println("Email already exists!");
            return false;
        }
    	if (userValidationRepository.checkUserPhoneNumberExists(new Object[]{phone})) {
            System.out.println("Phone number already exists!");
            return false;
        }
    	boolean response=databaseOperation.executeUpdate(Queries.USER_REGISTER.getQuery(),values);
        return response;
    }
    @Override
    public boolean updateUser(Object values[]) {
    	boolean response=databaseOperation.executeUpdate(Queries.UPDATE_USER.getQuery(), values);
    	return response;
    }
	
}

