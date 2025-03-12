package com.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.controller.AddressController;
import com.project.controller.CartController;
import com.project.controller.MenuController;
import com.project.controller.RestaurantController;
import com.project.controller.UserOrderController;
import com.project.model.Cart;
import com.project.model.Restaurant;
import com.project.model.User;
import com.project.repository.AddressRepository;
import com.project.service.AddressService;

public class Utility {
	public static int addressIdHelper(int userId, Scanner sc, AddressController addressController) {
		int id=-1;
		try(ResultSet resultSet2=addressController.getUserAddressesByUserId(new Object[] {userId});){
			System.out.println("Select address Id from below: ");
			
			if (!resultSet2.isBeforeFirst()) { 
                System.out.println("No address found for user.");
            }
	        while(resultSet2.next()) {
	        	System.out.println("|| Address Id: "+ resultSet2.getInt("address_id")+" Address : "+resultSet2.getString("address")+" ||");
	        }
	        System.out.println("<--------------->");
	        System.out.println("(Select address Id from address and enter )or (new address or No address in the list enetr -1)");
	        int option=sc.nextInt();
	        sc.nextLine();
	        
	        if(option==-1) {
	        	System.out.println("Enter new Address :");
	        	String address=sc.nextLine();
	        	String response=addressController.addAddress(new Object[] {userId,address});
	        	System.out.println(response);
	        	return addressIdHelper(userId,sc,addressController);
	        	
	        }
	        else {
	        	return option;
	        }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static void listRestaurantsAndProduct(RestaurantController restaurantController,MenuController menuController) {
		try(ResultSet resultSet1=restaurantController.getAllRestaurants()){
			
			
			List<Restaurant> restaurants=new ArrayList<>();
			while(resultSet1!=null && resultSet1.next()) {
				restaurants.add(new Restaurant(resultSet1.getInt("restaurant_id"),resultSet1.getString("restaurant_name"),resultSet1.getString("restaurant_location"),resultSet1.getString("email"),resultSet1.getString("password")));
			}
			
			for(Restaurant restaurant:restaurants) {
				System.out.println("\n===============================");
                System.out.println("Restaurant: " + restaurant.getRestaurantName());
                System.out.println("Location: " + restaurant.getRestaurantLocation());
                System.out.println("-------------------------------");
				
				
				
				try(ResultSet resultSet2=menuController.getRestaurantMenu(new Object[] {restaurant.getRestaurantId()})) {
			        boolean hasMenu = false;
			        while (resultSet2 != null && resultSet2.next()) {
			            hasMenu = true;
			            int menuId = resultSet2.getInt("menu_id");
			            String foodName = resultSet2.getString("food_name");
			            double price = resultSet2.getDouble("price");
			            System.out.println("-> Food Name : " + foodName + " (Menu ID: " + menuId + ") price: ₹" + price);
			        }
			        if (!hasMenu) {
			            System.out.println("  No menu available for this restaurant.");
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
				
			}
			
		}catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	public static void serachByRestaurant(Scanner sc,RestaurantController restaurantController,MenuController menuController) {
		try {
		    System.out.println("Enter restaurant name (partial or full): ");
		    String searchName = sc.nextLine();

		    try (ResultSet resultSet1 = restaurantController.searchRestaurantByName(new Object[]{"%" + searchName + "%"}) ) {
		        boolean found = false;

		        while (resultSet1.next()) {
		            found = true;
		            int restaurantId = resultSet1.getInt("restaurant_id");
		            String restaurantName = resultSet1.getString("restaurant_name");
		            String location = resultSet1.getString("restaurant_location");

		            System.out.println("\n===============================");
		            System.out.println("Restaurant: " + restaurantName);
		            System.out.println("Location: " + location);
		            System.out.println("-------------------------------");

		            boolean hasMenu = false;

		            
		            try (ResultSet menuResultSet = menuController.getRestaurantMenu(new Object[]{restaurantId})) {
		                while (menuResultSet.next()) {
		                    hasMenu = true;
		                    String foodName = menuResultSet.getString("food_name");
		                    double price = menuResultSet.getDouble("price");
		                    System.out.printf("-> %s Price: ₹%.2f%n", foodName, price);
		                }
		            }

		            if (!hasMenu) {
		                System.out.println("No menu available for this restaurant.");
		            }
		        }

		        if (!found) {
		            System.out.println("No restaurant found with the given name.");
		        }
		    } 
		} catch (SQLException e) {
		    System.err.println("Database error: " + e.getMessage());
		}
	}
	
	public static void searchByFoodName(Scanner sc,RestaurantController restaurantController,MenuController menuController) {
		try {
	        System.out.println("Enter food name (partial or full): ");
	        String searchFood = sc.nextLine();

	        try(ResultSet resultSet1 = menuController.searchFoodInMenu(new Object[]{"%" + searchFood + "%"})){

		        boolean found = false;
		        while (resultSet1 != null && resultSet1.next()) {
		            found = true;
		            int restaurantId = resultSet1.getInt("restaurant_id");
		            String restaurantName = resultSet1.getString("restaurant_name");
		            String location = resultSet1.getString("restaurant_location");
		            String foodName = resultSet1.getString("food_name");
		            double price = resultSet1.getDouble("price");

		            System.out.println("\n===============================");
		            System.out.println("Restaurant: " + restaurantName);
		            System.out.println("Location: " + location);
		            System.out.println("Food: " + foodName);
		            System.out.println("Price: ₹" + price);
		            System.out.println("-------------------------------");
		        }

		        if (!found) {
		            System.out.println("No restaurant found offering this food item.");
		        }
	        }
	    } catch (SQLException e) {
	        System.err.println("Database error: " + e.getMessage());
	    }
	}
	
	public static void makeOrder(Scanner sc,AddressController addressController,UserOrderController userOrderController,CartController cartController,User user) {
		try {
			List<Cart> cartItems=new ArrayList<>();
	        
	        try(ResultSet resultSet1=cartController.getItemInCart(new Object[] {user.getUserId()})){
		        while(resultSet1.next()) {
		        	cartItems.add(new Cart(resultSet1.getInt("user_id"),resultSet1.getInt("menu_id"),resultSet1.getInt("quantity")));
		        }
	        }
	        if (cartItems.isEmpty()) {
	            System.out.println(" No items in cart.");
	            return ;
	        }
	        int addressId=Utility.addressIdHelper(user.getUserId(), sc, addressController);
	        boolean response=false;
	        
	        response=userOrderController.makeOrder(cartItems,new Object[] {user.getUserId(),addressId});
	        if(response) {
	        	String response1=cartController.clearCart(new Object[] {user.getUserId()});
	        	System.out.println(response1);
	        }
	        if (response) {
	            System.out.println("Order placed successfully!");
	        } else {
	            System.out.println("Failed to place the order. Please try again.");
	        }
	    } 
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public static void listItemInCart(CartController cartController,User user) {
		try (ResultSet resultSet1 = cartController.getItemInCart(new Object[]{user.getUserId()})){
		    boolean hasItems = false;
		    while (resultSet1 != null && resultSet1.next()) {
		        hasItems = true;
		        int cartId=resultSet1.getInt("cart_id");
		        int menuId=resultSet1.getInt("menu_id");
		        int quantity = resultSet1.getInt("quantity");
		        String restaurantName = resultSet1.getString("restaurant_name");
		        String restaurantLocation = resultSet1.getString("restaurant_location");
		        String foodName = resultSet1.getString("food_name");
		        String foodType = resultSet1.getString("type");
		        double price = resultSet1.getDouble("price");

		        System.out.println("--------------------------------------------------");
		        System.out.println("Cart Id          : " + cartId );
		        System.out.println("Menu Id          : " + menuId );
		        System.out.println("Restaurant Name  : " + restaurantName);
		        System.out.println("Location         : " + restaurantLocation);
		        System.out.println("Food Name        : " + foodName);
		        System.out.println("Food Type        : " + foodType);
		        System.out.println("Quantity         : " + quantity);
		        System.out.println("Price per Item   : ₹" + price);
		        System.out.println("Total Price      : ₹" + (quantity * price));
		        System.out.println("--------------------------------------------------");
		    }
		    if (!hasItems) {
		        System.out.println("Cart is empty.");
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}
	
	public static void listOrders(UserOrderController userOrderController,User user) {
		try(ResultSet resultSet1 = userOrderController.getOrderHistory(new Object[]{user.getUserId()})){
		    
		    System.out.println("Order History:");
		    System.out.println("--------------------------------------------------------------------------------");
		    System.out.printf("%-10s %-20s %-20s %-15s %-20s %-10s\n", 
		                      "Order ID", "Restaurant", "Food", "Date", "Address", "Status");
		    System.out.println("--------------------------------------------------------------------------------");
		    boolean hashItem=true;
		    try {
		        while (resultSet1.next()) {
		        	hashItem=false;
		            int orderId = resultSet1.getInt("order_id");
		            String restaurantName = resultSet1.getString("restaurant_name");
		            String foodName = resultSet1.getString("food_name");
		            String orderDate = resultSet1.getString("order_date");
		            String address = resultSet1.getString("address");
		            String status = resultSet1.getString("status");

		            System.out.printf("%-10d %-20s %-20s %-15s %-20s %-10s\n", 
		                              orderId, restaurantName, foodName, orderDate, address, status);
		        }
		        
		        if(hashItem) {
		        	System.out.println("no previous order found");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    System.out.println("----------------------------------------------------------------------------------");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}


