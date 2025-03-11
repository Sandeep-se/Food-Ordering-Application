package com.project.repository;

import java.sql.ResultSet;

public interface AddressRepository {
	public boolean addAddress(Object values[]);
	public ResultSet getUserAddressesByUserId(Object values[]);
	public ResultSet getAddressByAddressId(Object values[]);
	public boolean  removeAddress(Object values[]);
}
