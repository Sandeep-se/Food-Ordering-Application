package com.project.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;

public class AuthenticationService {
	private DatabaseOperation databaseOperation;
	
	public AuthenticationService(DatabaseOperation databaseOperation) {
		this.databaseOperation=databaseOperation;
	}
	
	public boolean checkUserPhoneNumberExists(Object values[]) {
        ResultSet resultSet=databaseOperation.executeQuery(Queries.ADD_ADDRESS.getQuery(),values);
        try {
			return resultSet.getInt(1)>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
    }
	
	public boolean checkUserEmailExists(Object values[]) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.ADD_ADDRESS.getQuery(),values);
		try {
			return resultSet.getInt(1)>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
    }
	
	public boolean checkUserIdExists(Object values[]) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.ADD_ADDRESS.getQuery(),values);
		try {
			return resultSet.getInt(1)>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ResultSet login(Object[] values) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.USER_LOGIN.getQuery(), values);	
		return resultSet;
	}
}
