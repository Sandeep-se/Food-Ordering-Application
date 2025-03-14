package com.project.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.database.Queries;
import com.project.repository.DatabaseRepository;
import com.project.repository.MenuValidationRepository;

public class MenuValidationService implements MenuValidationRepository {
    private final DatabaseRepository databaseOperation;

    public MenuValidationService(DatabaseRepository databaseOperation) {
        this.databaseOperation = databaseOperation;
    }

    @Override
    public boolean checkMenuItemExistInMenu(Object[] values) {
        try (ResultSet resultSet = databaseOperation.executeQuery(Queries.CHECK_MENU_ITEM_EXISTS.getQuery(), values)) {
        	if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
