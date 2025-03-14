package com.project.repository;

import java.sql.ResultSet;

public interface FavoriteRepository {
	String addFavorite(Object[] values);
    String deleteFavorite(Object[] values);
    String deleteAllFavorite(Object[] values);
    ResultSet listFavoriteItems(Object[] values);
}
