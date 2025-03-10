package com.project.model;

public class Favorite {
	private int favoriteId;
    private int userId;
    private int menuId;

    public Favorite(int favoriteId, int userId, int menuId) {
        this.favoriteId = favoriteId;
        this.userId = userId;
        this.menuId = menuId;
    }

    public int getFavoriteId() { return favoriteId; }
    public void setFavoriteId(int favoriteId) { this.favoriteId = favoriteId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getMenuId() { return menuId; }
    public void setMenuId(int menuId) { this.menuId = menuId; }

    @Override
    public String toString() {
        return "Favorite{ favoriteId=" + favoriteId + ", userId=" + userId + ", menuId=" + menuId + " }";
    }
}
