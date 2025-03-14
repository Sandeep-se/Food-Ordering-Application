package com.project.view;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.project.Utility;
import com.project.Validation;
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
		UserValidationController userValidationController=new UserValidationController(userValidationService);
		
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
		
		FavoriteValidationRepository favoriteValidationService=new FavoriteValidationService(databaseOperation);
		FavoriteRepository favoriteService=new FavoriteService(databaseOperation,favoriteValidationService,menuValidationService);
		FavoriteController favoriteController=new FavoriteController(favoriteService);
		
		while(flag) {
			try {
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
						    if(userValidationController.isEmailExists(email)) {
						    	System.out.println("Email already exist.try different email");
						    	break;
						    }
						    if(!Validation.isValidEmail(email)) {
						    	System.out.println("invalid email");
						    	break;
						    }
						    System.out.println("Enter your password: ");
						    String password = sc.nextLine();
						    
						    System.out.println("Enter your phone number: ");
						    String phoneNumber = sc.nextLine();
						    if(userValidationController.isPhoneNumberExists(phoneNumber)) {
						    	System.out.println("Phone number already exist. Enter the new number");
						    }
						    if(!Validation.isValidPhoneNumber(phoneNumber)) {
						    	System.out.println("invalid phone number");
						    	break;
						    }
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
							
							try(ResultSet resultSet=authenticationController.login(values)){
								
							
							
							User user=null;
							if (resultSet != null && resultSet.next()) {
		                        user = new User(
		                                resultSet.getInt("user_id"),
		                                resultSet.getString("name"),
		                                resultSet.getString("email"),
		                                resultSet.getString("password"),
		                                resultSet.getString("phone_number")
		                        );
									
	                        	System.out.println("Welcome !"+user.getName());
								boolean flag1=true;
								
								while(flag1) {
									System.out.println("11 Home");
									System.out.println("12 Cart");
									System.out.println("13 Orders");
									System.out.println("14.Favorite");
									System.out.println("15.user profile");
									System.out.println("0.Logout");
									
									System.out.println("chooose the above option");
									choose=sc.nextInt();
									sc.nextLine();
									
									switch(choose) {
										case 11 ->{
											HomeView.homeView(sc,restaurantController,menuController,cartController,user);
										}
										case 12->{
											
											CartView.cartView(sc,cartController,user,addressController,userOrderController);
										}
										case 13->{
											OrderView.orderView(sc,userOrderController,user,cartController);
										}
										case 14->{
											FavoriteView.favoriteView(sc, favoriteController, user);
										}
										case 15->{
											ProfileView.profileView(sc, userController, user, addressController);
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
					case 0->{
						flag=false;
					}
					default->{
						System.out.println("Invalid choice! Please enter a valid option.");
					}
				}
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid input! Please enter a number.");
				sc.nextLine();
			}
		}
	}
}
	


