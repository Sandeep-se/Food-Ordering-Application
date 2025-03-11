package com.project.controller;

import java.sql.ResultSet;

import com.project.repository.AutheticationRepository;

public class AuthenticationController {
	public AutheticationRepository autheticationRepository;
	
	public AuthenticationController(AutheticationRepository autheticationRepository) {
		this.autheticationRepository=autheticationRepository;
	}
	
	public boolean checkUserPhoneNumberExists(Object[] values) {
		return autheticationRepository.checkUserPhoneNumberExists(values);
	}

	public boolean checkUserEmailExists(Object[] values) {
		return autheticationRepository.checkUserEmailExists(values);
	}

	
	public ResultSet login(Object values[]) {
		return autheticationRepository.login(values);
	}
}
