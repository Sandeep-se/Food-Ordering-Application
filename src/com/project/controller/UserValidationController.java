package com.project.controller;

import com.project.repository.UserValidationRepository;

public class UserValidationController {
	private UserValidationRepository userValidationRepository;
	
	
	public UserValidationController(UserValidationRepository userValidationRepository) {
        this.userValidationRepository = userValidationRepository;
    }

    public boolean isEmailExists(String email) {
        return userValidationRepository.checkEmailExists(new Object[]{email});
    }

    public boolean isPhoneNumberExists(String phoneNumber) {
        return userValidationRepository.checkPhoneNumberExists(new Object[]{phoneNumber});
    }
    
}
