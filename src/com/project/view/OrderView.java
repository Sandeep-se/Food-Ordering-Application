package com.project.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.project.controller.CartController;
import com.project.controller.UserOrderController;
import com.project.model.User;

public class OrderView {
	public static void orderView(Scanner sc,UserOrderController userOrderController,User user,CartController cartController) {
		boolean flag2=true;
		
		while(flag2) {
			try {
				System.out.println("Orders page");
				System.out.println("1 list the orders ");
				System.out.println("2 reorder");
				System.out.println("0 exit");
				
				System.out.println("choose the above option");
				int choose=sc.nextInt();
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
						} catch (SQLException e1) {
							e1.printStackTrace();
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
					case 0->{
						flag2=false;
					}
					default ->{
						System.out.println("Invalid choice! Please enter a valid option.");
					}
				}
			}catch(InputMismatchException e) {
				System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
			}
			
		}
	}
}
