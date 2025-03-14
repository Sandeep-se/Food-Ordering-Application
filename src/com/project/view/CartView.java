package com.project.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.project.Utility;
import com.project.controller.AddressController;
import com.project.controller.CartController;
import com.project.controller.UserOrderController;
import com.project.model.Cart;
import com.project.model.User;

public class CartView {
	public static void cartView(Scanner sc,CartController cartController,User user,AddressController addressController,UserOrderController userOrderController) {
		try {
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
				int choose=sc.nextInt();
				sc.nextLine();
				
				switch(choose) {
					case 1->{
						try {
							List<Cart> cartItems=new ArrayList<>();
					        
					        try(ResultSet resultSet1=cartController.getItemInCart(new Object[] {user.getUserId()})){
						        while(resultSet1.next()) {
						        	cartItems.add(new Cart(resultSet1.getInt("user_id"),resultSet1.getInt("menu_id"),resultSet1.getInt("quantity")));
						        }
					        } catch (SQLException e) {
								e.printStackTrace();
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
		}catch(InputMismatchException e) {
			System.out.println("Invalid input! Please enter a number.");
            sc.nextLine();
		}
	}
}
