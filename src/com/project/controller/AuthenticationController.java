package com.project.controller;

import java.sql.ResultSet;

import com.project.service.AuthenticationService;

public class AuthenticationController {
	public AuthenticationService authenticationService;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService=authenticationService;
	}
	
	public boolean checkUserPhoneNumberExists(Object[] values) {
		return authenticationService.checkUserPhoneNumberExists(values);
	}

	public boolean checkUserEmailExists(Object[] values) {
		return authenticationService.checkUserEmailExists(values);
	}

	public boolean checkUserIdExists(Object[] values) {
        return authenticationService.checkUserIdExists(values);
    }
	
	public ResultSet login(Object values[]) {
		return authenticationService.login(values);
	}
}
