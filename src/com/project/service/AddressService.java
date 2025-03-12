package com.project.service;

import java.sql.ResultSet;
import java.util.List;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.AddressRepository;
import com.project.repository.DatabaseRepository;


public class AddressService implements AddressRepository{
	private DatabaseRepository databaseOperation;
	
	public AddressService(DatabaseRepository databaseOperation) {
		
		this.databaseOperation=databaseOperation;
	}
	@Override
	public String addAddress(Object values[]) {
		Integer userId = (Integer) values[0];
	    String address = (String) values[1];
	    
	    if (userId == null) {
	        return "Invalid User ID!";
	    }
	    
	    if (address == null || address.trim().isEmpty()) {
	        return "Address cannot be empty!";
	    }
	    
		boolean response=databaseOperation.executeUpdate(Queries.ADD_ADDRESS.getQuery(),values);
		return response?"successfully added address":"address adding is failed";
    }
	@Override
	public ResultSet getUserAddressesByUserId(Object values[]) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_ADDRESSES_BY_USER.getQuery(),values);
		
        return resultSet;
    }
	@Override
    public ResultSet getAddressByAddressId(Object values[]) {
    	ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_ADDRESS_BY_ADDRESS_ID.getQuery(),values);
    	return resultSet;
    }
	@Override
    public String  removeAddress(Object values[]) {
		Integer userId = (Integer) values[0];
	    Integer addressId = (Integer) values[1];
	    
	    if (userId == null || userId <= 0 || addressId == null || addressId <= 0) {
	        return "Invalid User ID or Address ID!";
	    }
	    
    	boolean response=databaseOperation.executeUpdate(Queries.REMOVE_ADDRESS_BY_ADDRESS_ID.getQuery(),values);
    	return response?"Successfully remove address":"failed for removing address";
    }
}
