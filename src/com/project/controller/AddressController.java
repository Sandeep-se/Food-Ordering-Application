package com.project.controller;

import java.sql.ResultSet;

import com.project.repository.AddressRepository;

public class AddressController {
	private AddressRepository addressRepository;
	public AddressController(AddressRepository addressRepository) {
		this.addressRepository=addressRepository;
	}
	
	public boolean addAddress(Object values[]) {
	     return addressRepository.addAddress(values);
	}
	public ResultSet getUserAddressesByUserId(Object values[]) {
       return addressRepository.getUserAddressesByUserId(values);
   }
	public ResultSet getAddressByAddressId(Object values[]) {
	   return addressRepository.getAddressByAddressId(values);
	}
}

