package com.project.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.project.controller.FavoriteController;
import com.project.model.User;

public class FavoriteView {
	public static void favoriteView(Scanner sc,FavoriteController favoriteController, User user) {
		boolean flag=true;
		
		while(flag) {
			System.out.println("\n========== Welcome to Favorites ==========");
            System.out.println("1. Add item to favorites");
            System.out.println("2. Remove item from favorites");
            System.out.println("3. Clear all favorite items");
            System.out.println("4. List all favorite items");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choose = sc.nextInt();
            sc.nextLine();
			
			
			switch(choose) {
				case 1->{
					System.out.print("Enter Menu ID to add to favorites: ");
                    int menuId = sc.nextInt();
                    boolean response = favoriteController.addFavorite(new Object[]{user.getUserId(), menuId});
                    System.out.println(response ? "Item added to favorites!" : "Failed to add item.");
				}
				case 2->{
					System.out.print("Enter Menu ID to remove from favorites: ");
                    int menuId = sc.nextInt();
                    boolean response = favoriteController.deleteFavorite(new Object[]{user.getUserId(), menuId});
                    System.out.println(response ? "Item removed from favorites!" : "Failed to remove item.");
				}
				case 3->{
					boolean response = favoriteController.deleteAllFavorite(new Object[]{user.getUserId()});
                    System.out.println(response ? "All favorite items cleared!" : "Failed to clear favorites.");
				}
				case 4->{
					try (ResultSet resultSet = favoriteController.listFavoriteItems(new Object[]{user.getUserId()})) {
                        if (!resultSet.isBeforeFirst()) {
                            System.out.println("No favorite items found.");
                        } else {
                            System.out.println("\n===== Your Favorite Items =====");
                            while (resultSet.next()) {
                                int menuId = resultSet.getInt("menu_id");
                                String foodName = resultSet.getString("food_name");
                                double price = resultSet.getDouble("price");
                                System.out.printf("Menu ID: %d | Food Name :%s | price: ₹%.2f%n", menuId, foodName, price);
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
				}
				case 5 -> {
                    flag = false;
                    System.out.println("Exiting Favorites Menu...");
                }
                default -> System.out.println("Invalid choice. Please try again.");

			}
		}
		
	}
}
