package org.example;

import com.sun.security.jgss.GSSUtil;

public class Order {
    private String orderId;
    private String username;
    private String itemName;
    private double price;
    private double discountedPrice;
    private String deliveryAddress;
    private String orderType;
    private String dateTime;

    public Order(String orderId, String username, String itemName, double price, double discountedPrice, String deliveryAddress, String orderType, String dateTime) {
        this.orderId = orderId;
        this.username = username;
        this.itemName = itemName;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.deliveryAddress = deliveryAddress;
        this.orderType = orderType;
        this.dateTime = dateTime;
    }

    public String toFileString(){
        return orderId + ", " + username + ", " + itemName + ", " + price +  ", " + discountedPrice + ", " + deliveryAddress + ", " + orderType + ", " + dateTime;
    }

    public void displayOrderDetails(){
        System.out.println("============= ORDER RECEIPT =============");
        System.out.println("Order ID         : " + orderId);
        System.out.println("Item Name        : " + itemName);
        System.out.println("Price            : " + price);
        System.out.println("Discounted Price : " + discountedPrice);
        System.out.println("Delivery Address : " + deliveryAddress);
        System.out.println("Order Type       : " + orderType);
        System.out.println("Date & Time      : " + dateTime);
    }
}
