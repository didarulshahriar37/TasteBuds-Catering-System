package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DeliveryManager {
    private Scanner sc = new Scanner(System.in);
    private String chefFile = "data/chefAssigned.txt";
    private String driverFile = "data/drivers.txt";
    private String deliveryFile = "data/onDelivery.txt";

    public void deliveryManagerInterface(){
        while(true){
            System.out.println("============= DELIVERY MANAGEMENT MENU =============");
            System.out.println("1.View Deliverable Orders");
            System.out.println("2.View Drivers List");
            System.out.println("3.Assign Driver");
            System.out.println("4.Exit");
            System.out.print("> ");
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1){
                viewDeliverableOrders();
            }
            else if(choice == 2){
                viewDrivers();
            }
            else if(choice == 3){
                assignDrivers();
            }
            else if(choice == 4){
                return;
            }
            else{
                System.out.println("Invalid Choice");
            }
        }
    }

    private void viewDeliverableOrders(){
        System.out.println("============= DELIVERABLE ORDER LIST =============");
        try(BufferedReader br = new BufferedReader(new FileReader(chefFile))){
            String line;
            while((line = br.readLine()) != null){
                String[] lineData = line.split(",");
                System.out.println("Order ID: " + lineData[0]);
                System.out.println("Ordered By: " + lineData[1]);
                System.out.println("Item Name: " + lineData[2]);
                System.out.println("Price: " + lineData[3]);
                System.out.println("Discounted Price: " + lineData[4]);
                System.out.println("Delivery Address: " + lineData[5]);
                System.out.println("Order Type: " + lineData[6]);
                System.out.println("Order Date: " + lineData[9]);
                System.out.println("-------------------------------------");
            }
        }
        catch(IOException e){
            System.out.println("No Deliverable Orders Found");
        }
    }

    private void viewDrivers(){
        System.out.println("============= DRIVERS LIST =============");
        try(BufferedReader br = new BufferedReader(new FileReader(driverFile))){
            String line;
            while((line = br.readLine()) != null){
                String[] lineData = line.split(",");
                System.out.println("Driver Name: " + lineData[0]);
                System.out.println("Driving License: " + lineData[2]);
                System.out.println("-------------------------------------");
            }
        }
        catch(IOException e){
            System.out.println("No Drivers Found.");
        }
    }

    private void assignDrivers(){
        System.out.print("Enter Order Id: ");
        String orderId = sc.nextLine();

        String order = findOrder(orderId);
        if(order == null){
            System.out.println("Order Not Found");
            return;
        }

        if(isAlreadyAssigned(orderId)){
            System.out.println("Driver Already Assigned");
            return;
        }

        System.out.print("Enter Driver Name: ");
        String driverName = sc.nextLine();
        System.out.print("Estimated Delivery Time: ");
        String deliveryTime = sc.nextLine();

        try(FileWriter fw = new FileWriter(deliveryFile, true)){
            String[] data = order.split(", ");
            fw.write(data[0] + ", " + data[1] + ", " + data[2] + ", " + data[3] + ", " + data[4] + ", " + data[5] + ", " + data[6] + ", " + driverName + ", " + deliveryTime + "\n");

            System.out.println("Driver Assigned Successfully");
            displayDeliveryDetails(data, driverName, deliveryTime);
        }
        catch (IOException e){
            System.out.println("No Drivers Found.");
        }
    }

    private String findOrder(String orderId) {
        try (BufferedReader br = new BufferedReader(new FileReader(chefFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(orderId)) {
                    return line;
                }
            }
        } catch (IOException e) {
//            System.out.println("Error Reading File.");
        }
        return null;
    }

    private boolean isAlreadyAssigned(String orderId) {
        try (BufferedReader br = new BufferedReader(new FileReader(deliveryFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(orderId)) {
                    return true;
                }
            }
        } catch (IOException e) {
//            System.out.println("Error Reading File.");
        }
        return false;
    }

    private void displayDeliveryDetails(String[] data, String driverName, String deliveryTime){
        System.out.println("============= DELIVERY SUMMARY =============");
        System.out.println("Order Id: " + data[0]);
        System.out.println("Ordered By: " + data[1]);
        System.out.println("Item Name: " +  data[2]);
        System.out.println("Price: " +  data[3]);
        System.out.println("Discounted Price: " + data[4]);
        System.out.println("Delivery Address: " + data[5]);
        System.out.println("Order Type: " +  data[6]);
        System.out.println("Drivers Name: " + data[8]);
        System.out.println("Estimated Delivery Time: " + data[9]);
    }
}