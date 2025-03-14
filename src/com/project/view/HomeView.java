package com.project.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.project.controller.CartController;
import com.project.controller.MenuController;
import com.project.controller.RestaurantController;
import com.project.model.Restaurant;
import com.project.model.User;

public class HomeView {
	public static void homeView(Scanner sc,RestaurantController restaurantController,MenuController menuController,CartController cartController,User user) {
		try {
			boolean flag2=true;
			
			while(flag2) {
				System.out.println("1 List Restaurants along with Menu");
				System.out.println("2 search By Restaurant");
				System.out.println("3 search By food name");
				System.out.println("4 add item in cart");
				
				System.out.println("chooose the above option.");
				int choose=sc.nextInt();
				sc.nextLine();
				switch(choose) {
					case 1->{
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
					case 2->{
						try {
						    System.out.println("Enter restaurant name (partial or full): ");
						    String searchName = sc.nextLine();
						    
						    try (ResultSet resultSet1 = restaurantController.searchRestaurantByName(new Object[]{"%" + searchName + "%"}) ) {
						        boolean found = false;
						        List<Restaurant> restaurants=new ArrayList<>();
								while(resultSet1!=null && resultSet1.next()) {
									restaurants.add(new Restaurant(resultSet1.getInt("restaurant_id"),resultSet1.getString("restaurant_name"),resultSet1.getString("restaurant_location")));
								}
								
						        for(Restaurant restaurant:restaurants) {
						            found = true;
						            int restaurantId =restaurant.getRestaurantId();
						            String restaurantName = restaurant.getRestaurantName();
						            String location = restaurant.getRestaurantLocation();

						            System.out.println("\n===============================");
						            System.out.println("Restaurant: " + restaurantName);
						            System.out.println("Location: " + location);
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

						        if (!found) {
						            System.out.println("No restaurant found with the given name.");
						        }
						    } 
						} catch (SQLException e) {
						    System.err.println("Database error: " + e.getMessage());
						}
				    }
					case 3->{
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
					
					case 4->{
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
						System.out.println("--------------------------------");
						System.out.println("--------------------------------");
						System.out.println("Enter Menu ID to add to cart:");
					    int menuId = sc.nextInt();
					    System.out.println("Enter Quantity:");
					    int quantity = sc.nextInt();
					    String response = cartController.addItemInCart(new Object[]{user.getUserId(), menuId, quantity});
					    System.out.println(response);
					        
					}
					default->{
						flag2=false;
					}
				}
			}
		}catch(InputMismatchException e) {
			System.out.println("Invalid input! Please enter a number.");
            sc.nextLine();
		}
	}
}
