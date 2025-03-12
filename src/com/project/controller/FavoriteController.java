package com.project.controller;

import java.sql.ResultSet;

import com.project.repository.FavoriteRepository;

public class FavoriteController {
	private FavoriteRepository favoriteRepositiory;
	
	public FavoriteController(FavoriteRepository favoriteRepositiory) {
		this.favoriteRepositiory=favoriteRepositiory;
	}
	
	public boolean addFavorite(Object values[]) {
		return favoriteRepositiory.addFavorite(values);
	}
	
	public boolean deleteFavorite(Object values[]) {
		return favoriteRepositiory.deleteFavorite(values);
	}
	
	public boolean deleteAllFavorite(Object values[]) {
		return favoriteRepositiory.deleteAllFavorite(values);
	}
	
	public ResultSet listFavoriteItems(Object values[]) {
		return favoriteRepositiory.listFavoriteItems(values);
	}
	
}
