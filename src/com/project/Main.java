package com.project;

import java.sql.ResultSet;
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

//		System.out.println("user 1");
//		System.out.println("Restaurant 2");
//		System.out.println("Admin 3");
		try {
			UserView userView=new UserView();
			userView.userView(new Scanner(System.in));
		}
		finally {
			System.out.println("connection close");
			Jdbc.closeConnection();
		}
	}
}


//DatabaseOperation dbo=new DatabaseOperation();
//UserService us=new UserService(dbo);
//UserController uc=new UserController(us);
//Object values[]=new Object[] {"sandeep","sandeep@gmail.com","12345","9876543210"};
//System.out.println(uc.register(values));

//AddressService as=new AddressService(dbo);
//AddressController ac=new AddressController(as);
//Object values[]=new Object[] {2,"salem"};
//ResultSet resultSet=ac.getAddressByAddressId(new Object[] {1});
//if(resultSet.next())
//System.out.println(resultSet.getString("address"));
//ac.addAddress(values);

//CartService cs=new CartService(dbo);
//CartController cc=new CartController(cs);
//Object[] values=new Object[] {2,5,1};
//
//ResultSet resultSet=cc.listItemInCart(values);
//System.out.println(cc.addItemInCart(values));
//System.out.println(cc.listItemInCart(values));
//System.out.println(cc.decreaseQuantity(new Object[] {2,4}));

//UserOrderService us=new UserOrderService(dbo);
//UserOrderController uc=new UserOrderController(us);
//Object [] values=new Object[] {2,1,4,1};
////System.out.println(uc.makeOrder(values));
//System.out.println(uc.updateOrder(new Object[] {"confirmed",1}));
//Jdbc.closeConnection();

