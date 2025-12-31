package org.example;

public class Customer {
    private String name;
    private String password;
    private int monthlyOrders;

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
        this.monthlyOrders = 0;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public int getMonthlyOrders(){
        return monthlyOrders;
    }

    public void increaseMonthlyOrders(){
        monthlyOrders++;
    }
}
