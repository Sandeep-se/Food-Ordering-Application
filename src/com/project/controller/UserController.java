package com.project.controller;



import com.project.repository.UserRepository;

public class UserController {
	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(Object []values) {
        return userRepository.register(values);
    }
    
    public String updateUser(Object values[]) {
    	return userRepository.updateUser(values);
    }
}


