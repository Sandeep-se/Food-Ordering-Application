package com.project.database;

public enum Queries {
	//users
    USER_REGISTER("INSERT INTO users (name, email, password, phone_number) VALUES (?, ?, ?, ?)"),
    USER_LOGIN("SELECT * FROM users WHERE email = ? AND password = ?"),
    UPDATE_USER("UPDATE users SET name = ?, email = ?, phone_number = ? WHERE user_id = ?"),
    USERS("SELECT * FROM users"),
    //address
	ADD_ADDRESS("INSERT INTO addresses (user_id, address) VALUES (?, ?)"),
    GET_ADDRESS_BY_ID("SELECT * FROM addresses WHERE address_id = ?"),
    GET_ADDRESSES_BY_USER("SELECT * FROM addresses WHERE user_id = ?"),
    //orders
    MAKE_ORDERS("INSERT INTO orders (user_id, address_id, menu_id, quantity) values(?,?,?,?)"),
    GET_ORDERS("SELECT o.order_id, r.restaurant_name, f.food_name, o.order_date, a.address, o.status \n"
    		+ "FROM orders o \n"
    		+ "JOIN menu m ON o.menu_id = m.menu_id  \n"
    	 	+ "JOIN restaurants r ON m.restaurant_id = r.restaurant_id \n"
    		+ "JOIN food f ON m.food_id = f.food_id  \n"
    		+ "JOIN addresses a ON o.address_id = a.address_id \n"
    		+ "WHERE o.user_id = ?"),
    UPDATE_ORDERS("UPDATE orders SET status = ? WHERE order_id = ?"),
    //cart
    ADD_ITEM_IN_CART("INSERT INTO cart (user_id, menu_id, quantity) VALUES (?, ?, ?)"),
    REMOVE_ITEM_IN_CART("DELETE FROM cart WHERE user_id = ? AND menu_id = ?"),
    CLEAR_CART_ITEM("DELETE FROM cart WHERE user_id = ?"),
    GET_CART_ITEMS("SELECT c.quantity, r.restaurant_name, r.restaurant_location, " +
            "f.food_name, f.type, m.price " +
            "FROM cart c " +
            "JOIN menu m ON c.menu_id = m.menu_id " +
            "JOIN restaurants r ON m.restaurant_id = r.restaurant_id " +
            "JOIN food f ON m.food_id = f.food_id " +
            "WHERE c.user_id = ?;"),
	INCREMENT_QUANTITY("UPDATE cart SET quantity = quantity + 1 WHERE user_id = ? AND menu_id = ?"),
    DECREMENT_QUANTITY("UPDATE cart SET quantity = quantity - 1 WHERE user_id = ? AND menu_id = ? AND quantity > 1"),
	//favorite
	
	//restaurant
	RESTAURANT_REGITSER("INSERT INTO restaurants(restaurant_name, restaurant_location, email, password) VALUES (?, ?, ?, ?)"),
	RESTAURANT_LOGIN("SELECT * FROM restaurants WHERE email = ? AND password = ?"),
	LIST_RESTAURANTS("SELECT * FROM restaurants"),
	RESTAURANT_BY_ID("SELECT * FROM restaurants WHERE restaurant_id = ?"),
	RESTAURANT_BY_NAME("SELECT r.restaurant_id, r.restaurant_name, r.restaurant_location, f.food_name, m.price " +
		    	    "FROM restaurants r " +
		    	    "JOIN menu m ON r.restaurant_id = m.restaurant_id " +
		    	    "JOIN food f ON m.food_id = f.food_id " +
		    	    "WHERE r.restaurant_name LIKE ?"),
	DELETE_RESTAURANT("DELETE FROM restaurants WHERE restaurant_id = ?"),
	
	//menu
	ADD_RESTAURANT_MENU("INSERT INTO menu(restaurant_id, food_id, price) VALUES (?, ?, ?)"),
	GET_RESTAURANTS_MENU("SELECT m.menu_id,f.food_name, m.price \n"
			+ "FROM menu m\n"
			+ "JOIN restaurants r ON m.restaurant_id = r.restaurant_id\n"
			+ "JOIN food f ON m.food_id = f.food_id\n"
			+ "WHERE r.restaurant_id = ?"),
	DELETE_RESTAURANT_MENU("DELETE FROM menu WHERE menu_id = ?"),
	//food
	CREATE_FOOD("INSERT INTO food(food_name, type) VALUES (?, ?)"),
	DELETE_FOOD("DELETE FROM food WHERE food_id = ?"),
//	SEARCH_FOOD("SELECT r.restaurant_name, f.food_name, m.price \n"
//			+ "FROM restaurants r \n"
//			+ "JOIN menu m ON r.restaurant_id = m.restaurant_id \n"
//			+ "JOIN food f ON m.food_id = f.food_id \n"
//			+ "WHERE f.food_name = ?");
	SEARCH_BY_FOOD_NAME(
		    "SELECT r.restaurant_id, r.restaurant_name, r.restaurant_location, f.food_name, m.price " +
		    "FROM restaurants r " +
		    "JOIN menu m ON r.restaurant_id = m.restaurant_id " +
		    "JOIN food f ON m.food_id = f.food_id " +
		    "WHERE f.food_name LIKE ?"
		);
	private final String query;
	Queries(String query){
		this.query=query;
	}
	
	public String getQuery() {
		return query;
	}
}

