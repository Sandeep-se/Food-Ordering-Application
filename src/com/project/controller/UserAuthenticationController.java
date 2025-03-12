package com.project.controller;

import java.sql.ResultSet;

import com.project.repository.AuthenticationRepository;

public class UserAuthenticationController {
	private AuthenticationRepository authenticationRepository;
	
	public UserAuthenticationController(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }
	
	public ResultSet login(Object values[]) {
        return authenticationRepository.login(values);
    }
}
