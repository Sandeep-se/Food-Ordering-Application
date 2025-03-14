package com.project.service;

import java.sql.ResultSet;
import com.project.database.Queries;
import com.project.repository.DatabaseRepository;
import com.project.repository.FavoriteRepository;
import com.project.repository.FavoriteValidationRepository;
import com.project.repository.MenuValidationRepository;

public class FavoriteService implements FavoriteRepository{
	private final DatabaseRepository databaseOperation;
	private final FavoriteValidationRepository favoriteValidationRepository;
	private final MenuValidationRepository menuValidationRepository;

    public FavoriteService(DatabaseRepository databaseOperation, FavoriteValidationRepository favoriteValidationRepository,MenuValidationRepository menuValidationRepository) {
        this.databaseOperation = databaseOperation;
        this.favoriteValidationRepository = favoriteValidationRepository;
        this.menuValidationRepository = menuValidationRepository;
    }
	@Override
    public String addFavorite(Object[] values) {
		int menuId = (int) values[1];
		if(!menuValidationRepository.checkMenuItemExistInMenu(new Object[] {menuId}));
		if(favoriteValidationRepository.checkMenuExistInFavorite(values))
		{
			return "Alread exist in favorite";
		}
        boolean response=databaseOperation.executeUpdate(Queries.ADD_FAVORITE.getQuery(), values);
        return response?"successfully added in cart":"failed in adding in favorite";
    }

    @Override
    public String deleteFavorite(Object[] values) {
    	if(!favoriteValidationRepository.checkMenuExistInFavorite(values))
		{
			return "No such item in favorite for deleting";
		}
    	boolean response=databaseOperation.executeUpdate(Queries.DELETE_FAVORITE.getQuery(), values);
    	return response?"deletion success":"deletion fail";
    }
    
    @Override
	public String deleteAllFavorite(Object[] values) {
    	boolean response=databaseOperation.executeUpdate(Queries.DELETE_ALL_FAVORITES.getQuery(), values);
    	return response?"deleted all success":"deletion all fail";
	}
    
    @Override
    public ResultSet listFavoriteItems(Object[] values) {
    	return databaseOperation.executeQuery(Queries.LIST_FAVORITE_ITEMS.getQuery(), values);
    }
    

}
