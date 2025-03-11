package com.project.controller;

import java.sql.ResultSet;

import com.project.Validation;
import com.project.repository.UserRepository;
import com.project.service.UserService;

public class UserController {
	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(Object []values) {
        return userRepository.register(values);
    }
    
    public boolean updateUser(Object values[]) {
    	boolean response=userRepository.updateUser(values);
    	return response;
    }
}


