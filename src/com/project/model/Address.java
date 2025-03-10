package com.project.model;

public class Address {
	private int addressId;
    private int userId;
    private String address;

    public Address(int addressId, int userId, String address) {
        this.addressId = addressId;
        this.userId = userId;
        this.address = address;
    }

    public int getAddressId() { return addressId; }
    public void setAddressId(int addressId) { this.addressId = addressId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "| addressId=" + addressId + ", userId=" + userId + ", address='" + address + "' }";
    }
}
