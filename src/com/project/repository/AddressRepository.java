package com.project.repository;

import java.sql.ResultSet;

public interface AddressRepository {
	public String addAddress(Object values[]);
	public ResultSet getUserAddressesByUserId(Object values[]);
	public ResultSet getAddressByAddressId(Object values[]);
	public String  removeAddress(Object values[]);
}
