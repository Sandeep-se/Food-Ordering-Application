package com.project;

import java.sql.SQLException;
import java.util.Scanner;

import com.project.controller.AddressController;
import com.project.controller.CartController;
import com.project.controller.UserController;
import com.project.controller.UserOrderController;
import com.project.database.DatabaseOperation;
import com.project.database.Jdbc;
import com.project.service.AddressService;
import com.project.service.CartService;
import com.project.service.UserOrderService;
import com.project.service.UserService;
import com.project.view.UserView;

public class Main {
	
	public static void main(String args[]) throws SQLException {

		try {
			UserView userView=new UserView();
			userView.userView(new Scanner(System.in));
//			RestaurantView view=new RestaurantView();
//			view.restaurantView(new Scanner(System.in));
		} catch (Exception e) {  
	        e.printStackTrace();  
	    } finally {
	        System.out.println("Closing database connection...");
			Jdbc.closeConnection();
	    }
	}
}
