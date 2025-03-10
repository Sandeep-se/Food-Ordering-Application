package com.project.model;

public class Food {
	private int foodId;
    private String name;
    private double price;
    private String type;

    public Food(int foodId, String name, double price, String type) {
        this.foodId = foodId;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public int getFoodId() { return foodId; }
    public void setFoodId(int foodId) { this.foodId = foodId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return "Food{ foodId=" + foodId + ", name='" + name + "', price=" + price + " }";
    }
}
