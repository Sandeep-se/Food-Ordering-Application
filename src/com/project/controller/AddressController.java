package com.project.controller;

import java.sql.ResultSet;

import com.project.service.AddressService;

public class AddressController {
	private AddressService addressService;
	public AddressController(AddressService addressService) {
		this.addressService=addressService;
	}
	
	public 	 boolean addAddress(Object values[]) {
	     return addressService.addAddress(values);
	}
	public ResultSet getUserAddressesByUserId(Object values[]) {
       return addressService.getUserAddressesByUserId(values);
   }
	public ResultSet getAddressByAddressId(Object values[]) {
       return addressService.getAddressByAddressId(values);
   }
}

