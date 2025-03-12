package com.project.service;

import java.sql.ResultSet;
import com.project.database.Queries;
import com.project.repository.DatabaseRepository;
import com.project.repository.FavoriteRepository;

public class FavoriteService implements FavoriteRepository{
	private final DatabaseRepository databaseOperation;

    public FavoriteService(DatabaseRepository databaseOperation) {
        this.databaseOperation = databaseOperation;
    }
	@Override
    public boolean addFavorite(Object[] values) {
        return databaseOperation.executeUpdate(Queries.ADD_FAVORITE.getQuery(), values);
    }

    @Override
    public boolean deleteFavorite(Object[] values) {
        return databaseOperation.executeUpdate(Queries.DELETE_FAVORITE.getQuery(), values);
    }
    
    @Override
	public boolean deleteAllFavorite(Object[] values) {
		return databaseOperation.executeUpdate(Queries.DELETE_ALL_FAVORITES.getQuery(), values);
	}
    
    @Override
    public ResultSet listFavoriteItems(Object[] values) {
        return databaseOperation.executeQuery(Queries.LIST_FAVORITE_ITEMS.getQuery(), values);
    }
    

}
