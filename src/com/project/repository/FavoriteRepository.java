package com.project.repository;

import java.sql.ResultSet;

public interface FavoriteRepository {
	boolean addFavorite(Object[] values);
    boolean deleteFavorite(Object[] values);
    boolean deleteAllFavorite(Object[] values);
    ResultSet listFavoriteItems(Object[] values);
}
