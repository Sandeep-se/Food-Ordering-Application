package com.project.controller;

import java.sql.ResultSet;

import com.project.repository.AuthenticationRepository;

public class RestaurantAuthenticationController {
	private AuthenticationRepository restaurantAuthenticationService;
	public RestaurantAuthenticationController(AuthenticationRepository restaurantAuthenticationService) {
		this.restaurantAuthenticationService=restaurantAuthenticationService;
	}
	
	public ResultSet login(Object values[]) {
		return restaurantAuthenticationService.login(values);
	}
}
