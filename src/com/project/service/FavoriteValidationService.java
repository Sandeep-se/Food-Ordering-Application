package com.project.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.database.Queries;
import com.project.repository.DatabaseRepository;
import com.project.repository.FavoriteValidationRepository;

public class FavoriteValidationService implements FavoriteValidationRepository{
	private final DatabaseRepository databaseOperation;
	public FavoriteValidationService(DatabaseRepository databaseOperation) {
		this.databaseOperation=databaseOperation;
	}
	public boolean checkMenuExistInFavorite(Object values[]) {
		try (ResultSet resultSet = databaseOperation.executeQuery(Queries.CHECK_MENU_EXISTS_IN_FAVORITES.getQuery(), values)) {
	        if (resultSet != null && resultSet.next()) {
	            int count = resultSet.getInt(1);
	            System.out.println("Menu exists in favorites? " + count);
	            return count > 0;
	        } else {
	            System.out.println("Menu not found in favorites.");
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Error in checkMenuExistInFavorite(): " + e.getMessage());
	        e.printStackTrace();
	    }
	    return false;
	}

}
