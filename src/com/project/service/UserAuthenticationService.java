package com.project.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.AuthenticationRepository;
import com.project.repository.DatabaseRepository;

public class UserAuthenticationService implements AuthenticationRepository{
	private DatabaseRepository databaseOperation;
	
	public UserAuthenticationService(DatabaseRepository databaseOperation) {
		this.databaseOperation=databaseOperation;
	}
	
	@Override
	public ResultSet login(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.USER_LOGIN.getQuery(), values);	
		return resultSet;
	}
}
