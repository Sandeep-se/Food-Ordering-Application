package com.project.model;

public class Order {
	private int orderId;
    private int userId;
    private int menuId;
    private int addressId;
    private int quantity;
    private String status;
    private String orderDate;

    public Order(int orderId, int userId, int menuId, int addressId, int quantity, String status, String orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.menuId = menuId;
        this.addressId = addressId;
        this.quantity = quantity;
        this.status = status;
        this.orderDate = orderDate;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getMenuId() { return menuId; }
    public void setMenuId(int menuId) { this.menuId = menuId; }

    public int getAddressId() { return addressId; }
    public void setAddressId(int addressId) { this.addressId = addressId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    @Override
    public String toString() {
        return "Order{ orderId=" + orderId + ", userId=" + userId + ", menuId=" + menuId + ", quantity=" + quantity + ", status=" + status + " }";
    }
}
