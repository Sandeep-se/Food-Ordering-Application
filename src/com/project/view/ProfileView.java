package com.project.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.project.controller.AddressController;
import com.project.controller.UserController;
import com.project.model.User;

public class ProfileView {
	public static void profileView(Scanner sc,UserController userController,User user,AddressController addressController) {
		boolean flag=true;
		
		while(flag) {
			try {
				System.out.println("User profile");
				
				System.out.println("1.Update profile");
				System.out.println("2.View profile");
				System.out.println("3.View address");
				System.out.println("4.add address");
				System.out.println("0.Exist");
				int choose=sc.nextInt();
				sc.nextLine();
				switch(choose) {
					case 1->{
						System.out.println("Enter Name:");
			            String name = sc.nextLine();
		
			            System.out.println("Enter Email:");
			            String email = sc.nextLine();
		
			            System.out.println("Enter Phone Number:");
			            String phoneNumber = sc.nextLine();
			            
			            String response = userController.updateUser(new Object[]{name, email, phoneNumber, user.getUserId()});
			            System.out.println(response);
					}
					case 2->{
						try {
				            int userId = user.getUserId();

				            ResultSet rs = userController.viewUserProfile(new Object[]{userId});
				            if (rs.next()) {
				            	System.out.println("------------------------------------------");
				                System.out.println("User ID: " + rs.getInt("user_id"));
				                System.out.println("Name: " + rs.getString("name"));
				                System.out.println("Email: " + rs.getString("email"));
				                System.out.println("Phone: " + rs.getString("phone_number"));
				                System.out.println("------------------------------------------");
				            } else {
				                System.out.println("User not found!");
				            }
				        } catch (SQLException e) {
				            System.out.println("Error fetching user details: " + e.getMessage());
				        } 
					}
					case 3->{
						int userId = user.getUserId();

					    try(ResultSet rs = addressController.getUserAddressesByUserId(new Object[]{userId})) {
					        boolean hasAddresses = false;
					        System.out.println("Your Addresses:");
					        System.out.println("------------------------------------------");
					        while (rs.next()) {
					            hasAddresses = true;
					            System.out.println("- " + rs.getString("address"));
					            System.out.println(".....................");
					        }
					        if (!hasAddresses) {
					            System.out.println("No addresses found!");
					        }
					        System.out.println("------------------------------------------");
					    } catch (SQLException e) {
					        System.out.println("Error fetching addresses: " + e.getMessage());
					    }
					}
					case 4->{
						
						int userId = user.getUserId();
						System.out.println("Enter your new address:");
					    String newAddress = sc.nextLine().trim();
					    
					    String response = addressController.addAddress(new Object[]{userId, newAddress});
					    System.out.println(response);
					}
					default ->{
						flag=false;
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
