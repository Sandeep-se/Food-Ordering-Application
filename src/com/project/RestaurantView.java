package com.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.project.controller.FoodController;
import com.project.controller.MenuController;
import com.project.controller.RestaurantController;
import com.project.controller.RestaurantOrderController;
import com.project.database.DatabaseOperation;
import com.project.service.FoodService;
import com.project.service.MenuService;
import com.project.service.RestaurantOrderService;
import com.project.service.RestaurantService;
import com.project.model.Restaurant;

public class RestaurantView {
	public void restaurantView(Scanner sc) {
		
		DatabaseOperation databaseOperation=new DatabaseOperation();
		
		RestaurantService restaurantService=new RestaurantService(databaseOperation);
		RestaurantController restaurantController=new RestaurantController(restaurantService);
		
		RestaurantOrderService restaurantOrderService=new RestaurantOrderService(databaseOperation);
		RestaurantOrderController restaurantOrderController=new RestaurantOrderController(restaurantOrderService);
		
		MenuService menuService=new MenuService(databaseOperation);
		MenuController menuController=new MenuController(menuService);
		
		FoodService foodService=new FoodService(databaseOperation);
		FoodController foodController=new FoodController(foodService);
		
		System.out.println("welcome to restaurant page");
		
		boolean flag=true;
		
		while(flag) {
			System.out.println("1 Register");
			System.out.println("2 Login");
			System.out.println("0 Exit");
			
			System.out.println("select the above option");
			
			int choose=sc.nextInt();
			sc.nextLine();
			
			switch(choose) {
				case 1->{
					System.out.println("Enter restaurant name: ");
				    String name = sc.nextLine();
				    System.out.println("Enter email: ");
				    String email = sc.nextLine();
				    System.out.println("Enter password: ");
				    String password = sc.nextLine();
				    System.out.println("Enter location: ");
				    String location = sc.nextLine();

				    boolean success = restaurantController.createRestaurant(new Object[]{name, location,email, password});
				    System.out.println(success ? "Restaurant registered successfully!" : "Registration failed.");				
				    }
				case 2->{
					System.out.println("Enter restaurant email: ");
				    String email = sc.nextLine();
				    System.out.println("Enter password: ");
				    String password = sc.nextLine();
				    
				    try (ResultSet resultSet = restaurantController.login(new Object[]{email, password})) {
				        if (resultSet != null && resultSet.next()) {

				            Restaurant restaurant = new Restaurant(
				                resultSet.getInt("restaurant_id"),
				                resultSet.getString("restaurant_name"),
				                resultSet.getString("restaurant_location"),
				                resultSet.getString("email"),
				                resultSet.getString("password") 
				            );

				            System.out.println("Login Successful! Welcome, " + restaurant.getRestaurantName());
				            System.out.println("Restaurant Details: " + restaurant);
				            
				            boolean flag1=true;
				            
				            while(flag1) {
				            	System.out.println("1. Get Restaurant Orders");
				            	System.out.println("2. update orders");
				            	System.out.println("3. Add menu");
				            	System.out.println("4. Remove Menu");
				            	System.out.println("5. search Menu");
				            	System.out.println("6. get RestaurantMenu");
				            	System.out.println("7. update menu price");
				            	System.out.println("8x. Exit");
				            	
				            	System.out.println("choose the above option");
				            	
				            	choose=sc.nextInt();
				            	sc.nextLine();
				            	
				            	switch (choose) {
					            	case 1->{
					            		try (ResultSet resultSet1 = restaurantOrderController.getRestaurantOrders(new Object[]{restaurant.getRestaurantId()})) {
					            		    
					            		    boolean hasOrders = false;
					            		    System.out.println("Order History for Restaurant: " + restaurant.getRestaurantName());
					            		    System.out.println("--------------------------------------------------------------------------------------");
					            		    System.out.printf("%-10s %-20s %-15s %-30s %-20s %-10s %-10s %-15s %-10s\n",
					            		                      "Order ID", "Customer Name", "Phone Number", "Address", "Food Name", "Price", "Quantity", "Order Date", "Status");
					            		    System.out.println("--------------------------------------------------------------------------------------");

					            		    while (resultSet1 != null && resultSet1.next()) {
					            		        hasOrders = true;
					            		        int orderId = resultSet1.getInt("order_id");
					            		        String customerName = resultSet1.getString("name");
					            		        String phoneNumber = resultSet1.getString("phone_number");
					            		        String address = resultSet1.getString("address");
					            		        String foodName = resultSet1.getString("food_name");
					            		        double price = resultSet1.getDouble("price");
					            		        int quantity = resultSet1.getInt("quantity");
					            		        String orderDate = resultSet1.getString("order_date");
					            		        String status = resultSet1.getString("status");

					            		        System.out.printf("%-10d %-20s %-15s %-30s %-20s %-10.2f %-10d %-15s %-10s\n",
					            		                          orderId, customerName, phoneNumber, address, foodName, price, quantity, orderDate, status);
					            		    }

					            		    if (!hasOrders) {
					            		        System.out.println("No orders found for this restaurant.");
					            		    }
					            		    System.out.println("--------------------------------------------------------------------------------------");

					            		} catch (SQLException e) {
					            		    e.printStackTrace();
					            		}
					            	}
					            	case 2->{
					            		
					            		System.out.println("Enter Order ID: ");
					            	    int orderId = sc.nextInt();
					            	    sc.nextLine();
					            	    System.out.println("Enter New Status (Pending, Confirmed, Delivered, Cancelled): ");
					            	    String newStatus = sc.nextLine();
					            	    
					            	    boolean response = restaurantOrderController.updateOrderStatus(new Object[]{newStatus, orderId, restaurant.getRestaurantId()});

					            	    if (response) {
					            	        System.out.println("Order status updated successfully.");
					            	    } else {
					            	        System.out.println("Failed to update order status. Please check Order ID and Status ID.");
					            	    }
					            	}
					            	case 3->{
					            		// list food item
					            		System.out.println("Available Food Items:");
					            	    try (ResultSet resultSet1 = foodController.getAllFoodItems()) {
					            	        System.out.println("----------------------------------");
					            	        System.out.printf("%-10s %-20s\n", "Food ID", "Food Name");
					            	        System.out.println("----------------------------------");
					            	        while (resultSet != null && resultSet1.next()) {
					            	            int foodId = resultSet1.getInt("food_id");
					            	            String foodName = resultSet1.getString("food_name");
					            	            System.out.printf("%-10d %-20s\n", foodId, foodName);
					            	        }
					            	        System.out.println("----------------------------------");
					            	    } catch (SQLException e) {
					            	        e.printStackTrace();
					            	    }
					            		//add menu
					            	    System.out.println("Enter Food ID: ");
					            	    int foodId = sc.nextInt();
					            	    
					            	    System.out.println("Enter Price: ");
					            	    double price = sc.nextDouble();
					            	    sc.nextLine(); 
					            	    
					            	    boolean response = menuController.createMenu(new Object[] {restaurant.getRestaurantId(),foodId,price});
					            	    
					            	    if (response) {
					            	        System.out.println("Menu item added successfully!");
					            	    } else {
					            	        System.out.println("Failed to add menu item.");
					            	    }
					            	}
					            	case 4->{
					            		//remove menu
					            		System.out.println("Enter Menu ID to delete: ");
					            	    int menuId = sc.nextInt();
					            	    sc.nextLine(); 
					            	    boolean response = menuController.deleteMenu(new Object[] {menuId});
					            	    
					            	    if (response) {
					            	        System.out.println("Menu item deleted successfully!");
					            	    } else {
					            	        System.out.println("Failed to delete menu item. Menu ID might not exist.");
					            	    }
					            	}
					            	case 5->{
					            		//serach on restaurnat menu by food name
					            		System.out.println("Enter Food Name to Search: ");
					            	    String foodName = sc.nextLine();
					            	    
					            	    try (ResultSet resultSet1 = menuController.searchFoodInMenuRestaurant(new Object[] {"%" + foodName + "%",restaurant.getRestaurantId()})) {
					            	        boolean found = false;
					            	        
					            	        System.out.println("--------------------------------------------------");
					            	        System.out.printf("%-20s %-20s %-10s\n", "Restaurant", "Food Name", "Price");
					            	        System.out.println("--------------------------------------------------");

					            	        while (resultSet1 != null && resultSet1.next()) {
					            	            found = true;
					            	            String restaurantName = resultSet1.getString("restaurant_name");
					            	            String food = resultSet1.getString("food_name");
					            	            double price = resultSet1.getDouble("price");

					            	            System.out.printf("%-20s %-20s ₹%-10.2f\n", restaurantName, food, price);
					            	        }

					            	        if (!found) {
					            	            System.out.println("No menu items found for food name: " + foodName);
					            	        }
					            	        System.out.println("--------------------------------------------------");
					            	    } catch (SQLException e) {
					            	        e.printStackTrace();
					            	    }
					            	}
					            	case 6->{
					            		// get restaurant menu
					            		try (ResultSet resultSet1 = menuController.getRestaurantMenu(new Object[] {restaurant.getRestaurantId()})) {
					            	        boolean found = false;

					            	        System.out.println("--------------------------------------------------");
					            	        System.out.printf("%-10s %-20s %-10s\n", "Menu ID", "Food Name", "Price");
					            	        System.out.println("--------------------------------------------------");

					            	        while (resultSet1 != null && resultSet1.next()) {
					            	            found = true;
					            	            int menuId = resultSet1.getInt("menu_id");
					            	            String foodName = resultSet1.getString("food_name");
					            	            double price = resultSet1.getDouble("price");

					            	            System.out.printf("%-10d %-20s ₹%-10.2f\n", menuId, foodName, price);
					            	        }

					            	        if (!found) {
					            	            System.out.println("No menu found for restaurant ID: " + restaurant.getRestaurantName());
					            	        }
					            	        System.out.println("--------------------------------------------------");
					            	    } catch (SQLException e) {
					            	        e.printStackTrace();
					            	    }
					            	}
					            	case 7->{
					            		//update menu price
					            		System.out.println("Enter Menu ID to update price: ");
					            	    int menuId = sc.nextInt();

					            	    System.out.println("Enter new price: ");
					            	    double newPrice = sc.nextDouble();
					            	    
					            	    boolean response=menuController.updateMenu(new Object[] {newPrice,menuId,restaurant.getRestaurantId()});
					            	    if (response) {
					                        System.out.println("Menu price updated successfully.");
					                    } else {
					                        System.out.println("Menu ID not found. Update failed.");
					                    }
					            	}
					            	default->{
					            		flag1=false;
					            	}
				            	}
				            }
				        } else {
				            System.out.println("Invalid email or password. Please try again.");
				        }
				    } catch (SQLException e) {
				        e.printStackTrace();
				    }
				    
				}
				default->{
					flag=false;
				}
			}
		}
	}
}
