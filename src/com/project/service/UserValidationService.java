package com.project.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.DatabaseRepository;
import com.project.repository.UserValidationRepository;

public class UserValidationService implements UserValidationRepository {
	private final DatabaseRepository databaseOperation;
	
	public UserValidationService(DatabaseRepository databaseOperation) {
        this.databaseOperation = databaseOperation;
    }
	@Override
    public boolean checkEmailExists(Object values[]) {
		
		try (ResultSet resultset=databaseOperation.executeQuery(Queries.CHECK_USER_EMAIL_EXISTS.getQuery(), values)){
			if (resultset.next()) {
			    return resultset.getInt(1) > 0; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
    }

	@Override
    public boolean checkPhoneNumberExists(Object values[]) {
		try (ResultSet resultset=databaseOperation.executeQuery(Queries.CHECK_PHONE_EXISTS.getQuery(), values)){
			if (resultset.next()) {
			    return resultset.getInt(1) > 0; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
    }
}
