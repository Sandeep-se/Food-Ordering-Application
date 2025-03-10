package com.project.model;

public class Menu {
	private int menuId;
    private int restaurantId;
    private int foodId;

    public Menu(int menuId, int restaurantId, int foodId) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.foodId = foodId;
    }

    public int getMenuId() { return menuId; }
    public void setMenuId(int menuId) { this.menuId = menuId; }

    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    public int getFoodId() { return foodId; }
    public void setFoodId(int foodId) { this.foodId = foodId; }

    @Override
    public String toString() {
        return "Menu{ menuId=" + menuId + ", restaurantId=" + restaurantId + ", foodId=" + foodId + " }";
    }
}
