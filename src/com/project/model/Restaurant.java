package com.project.model;

public class Restaurant {
	private int restaurantId;
    private String restaurantName;
    private String restaurantLocation;
    private String email;
    private String password;

    public Restaurant(int restaurantId, String restaurantName, String restaurantLocation, String email, String password) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
        this.email = email;
        this.password = password;
    } 
    
    public Restaurant(int restaurantId, String restaurantName, String restaurantLocation) {
    	this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
    }
    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public String getRestaurantLocation() { return restaurantLocation; }
    public void setRestaurantLocation(String restaurantLocation) { this.restaurantLocation = restaurantLocation; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
    	return "| restaurantId=" + restaurantId + ", name='" + restaurantName + "', location=" + restaurantLocation + " |";

    }
}
