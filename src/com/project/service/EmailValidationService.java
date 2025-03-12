package com.project.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.database.DatabaseOperation;
import com.project.repository.DatabaseRepository;
import com.project.repository.EmailValidationRepository;

public class EmailValidationService implements EmailValidationRepository{
	private final DatabaseRepository databaseOperation;
	
	public EmailValidationService(DatabaseRepository databaseOperation) {
        this.databaseOperation = databaseOperation;
    }
	@Override
	public boolean checkEmailExists(Object[] values) {
        try (ResultSet resultSet = databaseOperation.executeQuery(null, values)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
