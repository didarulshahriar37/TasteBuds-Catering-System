package org.example;

public class Order {
    private String orderId;
    private String username;
    private String itemName;
    private double price;
    private String deliveryAddress;
    private String orderType;
    private String dateTime;

    public Order(String orderId, String username, String itemName, double price, String deliveryAddress, String orderType, String dateTime) {
        this.orderId = orderId;
        this.username = username;
        this.itemName = itemName;
        this.price = price;
        this.deliveryAddress = deliveryAddress;
        this.orderType = orderType;
        this.dateTime = dateTime;
    }

    public String toFileString(){
        return orderId + ", " + username + ", " + itemName + ", " + price +  ", " + deliveryAddress + ", " + orderType + ", " + dateTime;
    }

    public void displayOrderDetails(){
        System.out.println("Order ID         : " + orderId);
        System.out.println("Item Name        : " + itemName);
        System.out.println("Price            : " + price);
        System.out.println("Delivery Address : " + deliveryAddress);
        System.out.println("Order Type       : " + orderType);
        System.out.println("Date & Time      : " + dateTime);
    }
}
