package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OrderService {
    private Scanner sc = new Scanner(System.in);
    private String dataFolder = "data";
    private String orderFile = dataFolder + "/orders.txt";

    public void placeOrder(String username){
        System.out.println("============= PLACE AN ORDER =============");
        System.out.println("1.House Party Combo - 999");
        System.out.println("2.Spicy Zinger & Chicken Meal - 669");
        System.out.println("3.Rice With Beef Chili & Vegetable - 350");
        System.out.println("4.Burger - 210 ");
        System.out.println("5.Pizza - 400");
        System.out.println("6.Faluda - 160");
        System.out.println("Select Your Option: ");
        System.out.print("> ");

        int choice = sc.nextInt();
        sc.nextLine();

        String itemName = "";
        double price = 0;

        if(choice == 1){
            itemName = "House Party Combo";
            price = 999;
        }
        else if(choice == 2){
            itemName = "Spicy Zinger & Chicken Meal";
            price = 669;
        }
        else if(choice == 3){
            itemName = "Rice With Beef Chili & Vegetable";
            price = 350;
        }
        else if(choice == 4){
            itemName = "Burger";
            price = 210;
        }
        else if(choice == 5){
            itemName = "Pizza";
            price = 400;
        }
        else if(choice == 6){
            itemName = "Faluda";
            price = 160;
        }
        else{
            System.out.println("Invalid Choice. Please Try Again.");
        }

        double discount = 0;
        double finalPrice = price;

        if((username != null) && (!username.equals("Guest"))){
            int total_orders = orderCount(username);
            discount = getDiscount(total_orders);
            finalPrice = price - (price*discount);
        }

        System.out.print("Your Delivery Address: ");
        String deliveryAddress = sc.nextLine();

        System.out.println("Select Order Type");
        System.out.println("1.Normal");
        System.out.println("2.Priority");
        System.out.print("> ");
        int choiceType = sc.nextInt();
        sc.nextLine();

        String orderType = choiceType == 1 ? "Normal" : "Priority";
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        String orderId = generateOrderId();

        Order order = new Order(orderId, username, itemName, price, finalPrice, deliveryAddress, orderType, dateTime);

        saveOrder(order);

        System.out.println("Order Placed Successfully.");
        order.displayOrderDetails();
        }

    private String generateOrderId() {
        int count = 1000;
        try (BufferedReader br = new BufferedReader(new FileReader(orderFile))) {
            while (br.readLine() != null) count++;
        } catch (IOException ignored) {}
        return "TBCS-" + count;
    };

    private void saveOrder(Order order) {
        try (FileWriter fw = new FileWriter(orderFile, true)) {
            fw.write(order.toFileString() + "\n");
        } catch (IOException e) {
            System.out.println("Error while writing to file.");
        }
    }

    private int orderCount(String usrename){
        int orderCount = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(orderFile))){
            String line;
            while ((line = br.readLine()) != null){
                String[] data = line.split(", ");
                if (data[1].equals(usrename)) {
                    orderCount++;
                }
            }
        }
        catch(IOException e){
            System.out.println("Error while writing to file.");
        }
        return orderCount;
    }

    private double getDiscount(int orderCount){
        if(orderCount >= 10){
            return 0.15;
        }
        else if(orderCount >= 5){
            return 0.07;
        }
        else if(orderCount >= 3){
            return 0.03;
        }
        return 0.0;
    }

    public void viewOrders(String username){
        if(username == null || username.equals("Guest")){
            System.out.println("Not Available for unregistered user.");

            return ;
        }

        System.out.println("============= PREVIOUS ORDERS =============");
        boolean found = false;
        try(BufferedReader br = new BufferedReader(new FileReader(orderFile))){
            String line;

            while((line = br.readLine()) != null){
                String[] data = line.split(", ");

                if(data[1].equals(username)){
                    found = true;
                    System.out.println("Order Id: " + data[0]);
                    System.out.println("Item Name: " + data[2]);
                    System.out.println("Price: " + data[3]);
                    System.out.println("Discounted Price: " + data[4]);
                    System.out.println("Delivery Address: " + data[5]);
                    System.out.println("Order Type: " + data[6]);
                    System.out.println("Order Date: " + data[7]);
                    System.out.println("-------------------------------------");
                }
            }
        }
        catch (IOException e){
            System.out.println("Error while reading the file.");
            return;
        }
        if(!found){
            System.out.println("No Orders has been placed yet.");
        }
    }
}