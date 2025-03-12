package com.project.service;

import java.sql.ResultSet;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.AuthenticationRepository;

public class RestaurantAuthenticationService implements AuthenticationRepository {
	private DatabaseOperation databaseOperation;

	public RestaurantAuthenticationService(DatabaseOperation databaseOperation) {
		this.databaseOperation = databaseOperation;
	}

	@Override
	public ResultSet login(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(null, values);	
		return resultSet;
	}
}
