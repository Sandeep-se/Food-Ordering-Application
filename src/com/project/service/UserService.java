package com.project.service;

import java.sql.ResultSet;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;

public class UserService {
	private final DatabaseOperation databaseOperation;

    public UserService(DatabaseOperation databaseOperation) {
        this.databaseOperation = databaseOperation;
    }

    public boolean register(Object values[]) {
        boolean response=databaseOperation.executeUpdate(Queries.USER_REGISTER.getQuery(),values);
        return response;
    }

    public ResultSet login(Object []values) {
        ResultSet resultSet=databaseOperation.executeQuery(Queries.USER_LOGIN.getQuery(),values);
        return resultSet;
    }
    public boolean updateUser(Object values[]) {
    	boolean response=databaseOperation.executeUpdate(Queries.UPDATE_USER.getQuery(), values);
    	return response;
    }
	
}

