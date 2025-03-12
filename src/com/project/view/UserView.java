package com.project.view;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.project.Utility;
import com.project.repository.*;
import com.project.controller.*;
import com.project.model.*;
import com.project.service.*;
import com.project.database.*;


public class UserView {
	public void userView(Scanner sc){
		System.out.println("welcome to user page");
		
		boolean flag=true;
		
		DatabaseRepository databaseOperation=new DatabaseOperation();

		UserValidationRepository userValidationService = new UserValidationService(databaseOperation);
		
		UserRepository userService=new UserService(databaseOperation,userValidationService);
		UserController userController=new UserController(userService);
		
		AuthenticationRepository userAuthenticationService=new UserAuthenticationService(databaseOperation);
		UserAuthenticationController authenticationController=new UserAuthenticationController(userAuthenticationService);
		
		CartValidationRepository cartValidationService=new CartValidationService(databaseOperation);
		
		AddressRepository addressService=new AddressService(databaseOperation);
		AddressController addressController=new AddressController(addressService);
		
		MenuValidationRepository menuValidationService = new MenuValidationService(databaseOperation);
		
		CartRepository cartService=new CartService(databaseOperation,cartValidationService,menuValidationService);
		CartController cartController=new CartController(cartService);
		
		UserOrderRepository userOrderService=new UserOrderService(databaseOperation);
		UserOrderController userOrderController=new UserOrderController(userOrderService);
		
		EmailValidationRepository emailValidationService=new EmailValidationService(databaseOperation);
		RestaurantRepository restaurantService=new RestaurantService(databaseOperation,emailValidationService);
		RestaurantController restaurantController=new RestaurantController(restaurantService);
		
		MenuRepository menuService=new MenuService(databaseOperation);
		MenuController menuController=new MenuController(menuService);
		
		FavoriteRepository favoriteService=new FavoriteService(databaseOperation);
		FavoriteController favoriteController=new FavoriteController(favoriteService);
		
		while(flag) {
			
			System.out.println("1.Register");
			System.out.println("2.Login");
			System.out.println("0.Exit the page");
			
			int choose=sc.nextInt();
			sc.nextLine();
			
			switch(choose) {
				case 1->{
					
					 System.out.println("Enter your name: ");
					    String name = sc.nextLine();
					    
					    System.out.println("Enter your email: ");
					    String email = sc.nextLine();
					    
					    System.out.println("Enter your password: ");
					    String password = sc.nextLine();
					    
					    System.out.println("Enter your phone number: ");
					    String phoneNumber = sc.nextLine();
					    String response=userController.register(new Object[] {name,email,password,phoneNumber});
					    
					    System.out.println(response);
				}
				case 2->{
					try {
						System.out.println("Enetr the email: ");
						String email=sc.next();
						
						System.out.println("Enter password :");
						String password=sc.next();
						
						Object values[]=new Object[] {email,password};
						
						try(ResultSet resultSet=userAuthenticationService.login(values)){
							
						
						
						User user=null;
						if (resultSet != null && resultSet.next()) {
	                        user = new User(
	                                resultSet.getInt("user_id"),
	                                resultSet.getString("name"),
	                                resultSet.getString("email"),
	                                resultSet.getString("password"),
	                                resultSet.getString("phone_number")
	                        );
								
                        	System.out.println(user);
							boolean flag1=true;
							
							while(flag1) {
								System.out.println("11 Home");
								System.out.println("12 Cart");
								System.out.println("13 Orders");
								System.out.println("14.Favorite");
								System.out.println("15.user profile");
								System.out.println("0.exit");
								
								System.out.println("chooose the above option");
								choose=sc.nextInt();
								sc.nextLine();
								
								switch(choose) {
									case 11 ->{
										boolean flag2=true;
										
										while(flag2) {
											System.out.println("1 List Restaurants along with Menu");
											System.out.println("2 search By Restaurant");
											System.out.println("3 search By food name");
											System.out.println("4 add item in cart");
											
											System.out.println("chooose the above option.");
											choose=sc.nextInt();
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
									}
									case 12->{
										
										boolean flag2=true;
										
										while(flag2) {
											System.out.println("1 make order");
											System.out.println("2 remove item in cart");
											System.out.println("3 clear item in cart");
											System.out.println("4 increment item in cart");
											System.out.println("5 decremnt item in cart");
											System.out.println("6 list item in cart");
											System.out.println("0 exit");
											
											System.out.println("choose the above option");
											choose=sc.nextInt();
											sc.nextLine();
											
											switch(choose) {
												case 1->{
													try {
														List<Cart> cartItems=new ArrayList<>();
												        
												        try(ResultSet resultSet1=cartController.getItemInCart(new Object[] {user.getUserId()})){
													        while(resultSet1.next()) {
													        	cartItems.add(new Cart(resultSet1.getInt("user_id"),resultSet1.getInt("menu_id"),resultSet1.getInt("quantity")));
													        }
												        }
												        if (cartItems.isEmpty()) {
												            System.out.println(" No items in cart.");
												            break;
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
												    } finally {
											        sc.nextLine();
												    }
												}
												case 2->{
													//remove item in cart 
													
													System.out.println("Enter Menu ID to remove from cart:");
												    int menuId = sc.nextInt();
												    String removed = cartController.removeItemInCart(new Object[]{user.getUserId(), menuId});
												    System.out.println(removed);
												}
												case 3->{
													//clear item in cart
													String cleared = cartController.clearCart(new Object[]{user.getUserId()});
												    System.out.println(cleared);
												}
												case 4->{
													//increment item in cart
													System.out.println("Enter Menu ID to increment quantity:");
												    int menuId = sc.nextInt();
												    int quantity=1;
												    String increased = cartController.increaseQuantity(new Object[]{quantity,user.getUserId(), menuId});
												    System.out.println(increased);
												}
												case 5->{
													//decrement item in cart
													 System.out.println("Enter Menu ID to decrement quantity:");
												    int menuId = sc.nextInt();
												    String decreased = cartController.decreaseQuantity(new Object[]{user.getUserId(), menuId});
												    System.out.println(decreased);
												}
												case 6->{
																										
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
												default->{
													flag2=false;
												}
											}
										}
									}
									case 13->{
										boolean flag2=true;
										
										while(flag2) {
											System.out.println("Orders page");
											System.out.println("1 list the orders ");
											System.out.println("2 reorder");
											System.out.println("0 exit");
											
											System.out.println("choose the above option");
											choose=sc.nextInt();
											sc.nextLine();
											switch(choose) {
												case 1 -> {
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
													}
												}
												case 2->{
													
													System.out.println("Enter the Orde id: ");
													
													try(ResultSet resultSet1 = userOrderController.getOrderHistory(new Object[]{user.getUserId()})){
													    
													    System.out.println("Order History:");
													    System.out.println("--------------------------------------------------------------------------------");
													    System.out.printf("%-10s %-20s %-20s %-15s %-20s %-10s\n", 
													                      "Order ID", "Restaurant", "Food", "Date", "Address", "Status");
													    System.out.println("--------------------------------------------------------------------------------");
													    boolean hashItem=true;
													    
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
													    
													int id=sc.nextInt();
													sc.nextLine();
												    try(ResultSet resultSet2 = userOrderController.getOrderById(new Object[] {id})) {
												        if (resultSet2.next()) {
												            int menuId = resultSet2.getInt("menu_id");
												            int quantity = resultSet2.getInt("quantity");

												            String response=cartController.addItemInCart(new Object[]{user.getUserId(), menuId, quantity});
												            
												            System.out.println(response);
												        }
												    } catch (SQLException e) {
												        e.printStackTrace();
												    }
												}
												default ->{
													flag2=false;
												}
											}
										}
									}
									case 14->{
										FavoriteView.favoriteView(sc, favoriteController, user);
									}
									case 15->{
										
									}
									default->{
										flag1=false;
									}
								}
								}
							
							}
						else {
							System.out.println("invalid email id or password");
						}
						}
					
					}catch(SQLException e) {
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
	


