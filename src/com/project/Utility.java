package com.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.project.service.AddressService;

public class Utility {
	public static int addressIdHelper(int userId, Scanner sc, AddressService addressService) {
		int id=-1;
		try(ResultSet resultSet2=addressService.getUserAddressesByUserId(new Object[] {userId});){
			System.out.println("Select address Id from below: ");
	        while(resultSet2.next()) {
	        	System.out.println("|| Address Id: "+ resultSet2.getInt("address_id")+" Address : "+resultSet2.getString("address")+" ||");
	        }
	        System.out.println("<--------------->");
	        System.out.println("(Select address Id from address and enter )or (new address or No address in the list enetr -1)");
	        int option=sc.nextInt();
	        sc.nextLine();
	        
	        if(option==-1) {
	        	System.out.println("Enter new Address :");
	        	String address=sc.nextLine();
	        	addressService.addAddress(new Object[] {userId,address});
	        	return addressIdHelper(userId,sc,addressService);
	        	
	        }
	        else {
	        	return option;
	        }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

}
