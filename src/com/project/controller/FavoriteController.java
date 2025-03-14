package com.project.controller;

import java.sql.ResultSet;

import com.project.repository.FavoriteRepository;

public class FavoriteController {
	private FavoriteRepository favoriteRepositiory;
	
	public FavoriteController(FavoriteRepository favoriteRepositiory) {
		this.favoriteRepositiory=favoriteRepositiory;
	}
	
	public String addFavorite(Object values[]) {
		return favoriteRepositiory.addFavorite(values);
	}
	
	public String deleteFavorite(Object values[]) {
		return favoriteRepositiory.deleteFavorite(values);
	}
	
	public String deleteAllFavorite(Object values[]) {
		return favoriteRepositiory.deleteAllFavorite(values);
	}
	
	public ResultSet listFavoriteItems(Object values[]) {
		return favoriteRepositiory.listFavoriteItems(values);
	}
	
}
