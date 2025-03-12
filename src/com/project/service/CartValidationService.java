package com.project.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.CartValidationRepository;
import com.project.repository.DatabaseRepository;

public class CartValidationService implements CartValidationRepository {
	private final DatabaseRepository databaseOperation;

    public CartValidationService(DatabaseRepository databaseOperation) {
        this.databaseOperation = databaseOperation;
    }
    
    @Override
    public boolean checkMenuExists(Object[] values) {
        try (ResultSet resultSet = databaseOperation.executeQuery(Queries.CHECK_MENU_EXISTS.getQuery(), values)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    } 
}
