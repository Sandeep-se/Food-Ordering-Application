package com.project.service;

import java.sql.ResultSet;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.AuthenticationRepository;
import com.project.repository.DatabaseRepository;

public class RestaurantAuthenticationService implements AuthenticationRepository {
	private DatabaseRepository databaseOperation;

	public RestaurantAuthenticationService(DatabaseRepository databaseOperation) {
		this.databaseOperation = databaseOperation;
	}

	@Override
	public ResultSet login(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.RESTAURANT_LOGIN.getQuery(), values);	
		return resultSet;
	}
}
