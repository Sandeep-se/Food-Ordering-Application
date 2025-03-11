package com.project.service;

import java.sql.ResultSet;
import java.util.List;

import com.project.database.DatabaseOperation;
import com.project.database.Queries;
import com.project.repository.AddressRepository;


public class AddressService implements AddressRepository{
	private DatabaseOperation databaseOperation;
	
	public AddressService(DatabaseOperation databaseOperation) {
		
		this.databaseOperation=databaseOperation;
	}
	@Override
	public boolean addAddress(Object values[]) {
		boolean response=databaseOperation.executeUpdate(Queries.ADD_ADDRESS.getQuery(),values);
		return response;
    }
	@Override
	public ResultSet getUserAddressesByUserId(Object values[]) {
		ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_ADDRESSES_BY_USER.getQuery(),values);
		
        return resultSet;
    }
	@Override
    public ResultSet getAddressByAddressId(Object values[]) {
    	ResultSet resultSet=databaseOperation.executeQuery(Queries.GET_ADDRESS_BY_ID.getQuery(),values);
    	return resultSet;
    }
	@Override
    public boolean  removeAddress(Object values[]) {
    	boolean response=databaseOperation.executeUpdate(null,values);
    	return response;
    }
}
