package com.project.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.Utility;
import com.project.controller.AddressController;
import com.project.controller.AuthenticationController;
import com.project.controller.CartController;
import com.project.controller.MenuController;
import com.project.controller.RestaurantController;
import com.project.controller.UserController;
import com.project.controller.UserOrderController;
import com.project.database.DatabaseOperation;
import com.project.model.User;
import com.project.service.AddressService;
import com.project.service.AuthenticationService;
import com.project.service.CartService;
import com.project.service.MenuService;
import com.project.service.RestaurantService;
import com.project.service.UserOrderService;
import com.project.service.UserService;
import com.project.model.Cart;
import com.project.model.Restaurant;

public class UserView {
	public void userView(Scanner sc){
		System.out.println("welcome to user page");
		
		boolean flag=true;
		
		DatabaseOperation databaseOperation=new DatabaseOperation();
		
		AuthenticationService authenticationService=new AuthenticationService(databaseOperation);
		AuthenticationController authenticationController=new AuthenticationController(authenticationService);
		
		UserService userService=new UserService(databaseOperation);
		UserController userController=new UserController(userService);
		
		AddressService addressService=new AddressService(databaseOperation);
		AddressController addressController=new AddressController(addressService);
		
		CartService cartService=new CartService(databaseOperation);
		CartController cartController=new CartController(cartService);
		
		UserOrderService userOrderService=new UserOrderService(databaseOperation);
		UserOrderController userOrderController=new UserOrderController(userOrderService);
		
		RestaurantService restaurantService=new RestaurantService(databaseOperation);
		RestaurantController restaurantController=new RestaurantController(restaurantService);
		
		MenuService menuService=new MenuService(databaseOperation);
		MenuController menuController=new MenuController(menuService);
		
		while(flag) {
			
			System.out.println("1.Register");
			System.out.println("2.Login");
			System.out.println("0.Exit the page");
			
			int choose=sc.nextInt();
			
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
					    
					    if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
					        System.out.println("All fields are required!");
					        break;
					    }
					    
				}
				case 2->{
					try {
						System.out.println("Enetr the email: ");
						String email=sc.next();
						if(!email.contains("@gmail.com")){
							System.out.println("Email should contain @gmail.com");
							break;
						}
						
						System.out.println("Enter password :");
						String password=sc.next();
						
						Object values[]=new Object[] {email,password};
						
						ResultSet resultSet=authenticationService.login(values);
						
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
								
								System.out.println("chooose the above option.enter the integer");
								choose=sc.nextInt();
								sc.nextLine();
								switch(choose) {
									case 11 ->{
										boolean flag2=true;
										
										while(flag2) {
											System.out.println("111 List Restaurants along with Menu");
											System.out.println("222 search By Restaurant");
											System.out.println("333 search By food name");
											System.out.println("444 add item in cart");
											
											System.out.println("chooose the above option.enter the integer");
											choose=sc.nextInt();
											sc.nextLine();
											switch(choose) {
												case 111->{
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
												case 222->{
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
												case 333->{
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
												
												case 444->{
													System.out.println("Enter Menu ID to add to cart:");
												    int menuId = sc.nextInt();
												    System.out.println("Enter Quantity:");
												    int quantity = sc.nextInt();
												    boolean added = cartController.addItemInCart(new Object[]{user.getUserId(), menuId, quantity});
												    if (added) {
												        System.out.println("Item added to cart successfully!");
												    } else {
												        System.out.println("Failed to add item to cart.");
												    }
												        
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
												        int addressId=Utility.addressIdHelper(user.getUserId(), sc, addressService);
//												        System.out.println(addressId);
												        boolean response=false;
												        
												        response=userOrderController.makeOrder(cartItems,new Object[] {user.getUserId(),addressId});
												        if(response) {
												        	boolean response1=cartController.clearCart(new Object[] {user.getUserId()});
												        	if(response1) {
												        		System.out.println("cart clear succesfull");
												        	}
												        	else {
												        		System.out.println("failed in cart clearing");
												        		break;
												        	}
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
												    boolean removed = cartController.removeItemInCart(new Object[]{user.getUserId(), menuId});
												    if (removed) {
												        System.out.println("Item removed from cart successfully!");
												    } else {
												        System.out.println("Failed to remove item from cart.");
												    }
												}
												case 3->{
													//clear item in cart
													boolean cleared = cartController.clearCart(new Object[]{user.getUserId()});
												    if (cleared) {
												        System.out.println("Cart cleared successfully!");
												    } else {
												        System.out.println("Failed to clear cart.");
												    }
												}
												case 4->{
													//increment item in cart
													System.out.println("Enter Menu ID to increment quantity:");
												    int menuId = sc.nextInt();
												    boolean increased = cartController.increaseQuantity(new Object[]{user.getUserId(), menuId});
												    if (increased) {
												        System.out.println("Item quantity increased successfully!");
												    } else {
												        System.out.println("Failed to increase item quantity.");
												    }
												}
												case 5->{
													//decrement item in cart
													 System.out.println("Enter Menu ID to decrement quantity:");
												    int menuId = sc.nextInt();
												    boolean decreased = cartController.decreaseQuantity(new Object[]{user.getUserId(), menuId});
												    if (decreased) {
												        System.out.println("Item quantity decreased successfully!");
												    } else {
												        System.out.println("Failed to decrease item quantity.");
												    }
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

												            boolean response=cartController.addItemInCart(new Object[]{user.getUserId(), menuId, quantity});
												            
												            System.out.println(response?"Successfully added in cart":"failed in adding to cart");
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
									default->{
										flag1=false;
									}
									
								}
							
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
	


