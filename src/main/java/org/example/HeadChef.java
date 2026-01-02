package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HeadChef {
    private Scanner sc = new Scanner(System.in);
    private String orderFile = "data/orders.txt";
    private String chefFile = "data/chefAssigned.txt";

    public void headChefInterface(){
        while(true){
            System.out.println("============= HEAD CHEF DASHBOARD =============");
            System.out.println("1.View All Orders");
            System.out.println("2.Assign Chef(s)");
            System.out.println("3.Exit");
            System.out.print("> ");
            int choice = sc.nextInt();
            sc.nextLine();
            if(choice == 1){
                viewAllOrders();
            }
            else if(choice == 2){
                assignChef();
            }
            else if(choice == 3){
                break;
            }
        }
    }
    public void viewAllOrders(){
        System.out.println("============= ALL ORDERS =============");
        try(BufferedReader br = new BufferedReader(new FileReader(orderFile))){
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(", ");
                System.out.println("Order Id: " + data[0]);
                System.out.println("Ordered By: " + data[1]);
                System.out.println("Item Name: " + data[2]);
                System.out.println("Price: " + data[3]);
                System.out.println("Discounted Price: " + data[4]);
                System.out.println("Delivery Address: " + data[5]);
                System.out.println("Order Type: " + data[6]);
                System.out.println("Order Date: " + data[7]);
                System.out.println("-------------------------------------");
            }
        }
        catch (IOException e){
            System.out.println("Error Reading File.");
        }
    }

    public void assignChef(){
        System.out.print("Enter Order Id: ");
        String orderId = sc.nextLine();

        if(isAlreadyAssigned(orderId)){
            System.out.println("This order already has a chef assigned!");
            return;
        }

        System.out.print("Enter Chef Name(s): ");
        String chefName = sc.nextLine();
        System.out.print("Estimated Time: ");
        String estimatedTime = sc.nextLine();

        String order = findOrder(orderId);

        if(order == null){
            System.out.println("Order Not Found");
            return;
        }

        try(FileWriter fw = new FileWriter(chefFile, true)){
            String[] data = order.split(", ");
            fw.write(data[0] + ", " + data[1] + ", " + data[2] + ", " + data[3] + ", " + data[4] + ", " + data[5] + ", " + data[6] + ", " + chefName + ", " + data[7] + ", " + estimatedTime + "\n");
            System.out.println("Chef Assigned Successfully");
            displayAssignedInfo(data, chefName, estimatedTime);
        }
        catch (IOException e){
            System.out.println("Error Writing File.");
        }

    }

    private String findOrder(String orderId){
        try(BufferedReader br = new BufferedReader(new FileReader(orderFile))){
            String line;
            while((line = br.readLine()) != null){
                if(line.startsWith(orderId)){
                    return line;
                }
            }
        }
        catch(IOException e){
            System.out.println("Couldn't Find The Order.");
        }
        return null;
    }

    private void displayAssignedInfo(String[] data, String chefName, String estimatedTime){
        System.out.println("============= CHEF ASSIGNMENT DETAILS =============");
        System.out.println("Order ID           : " + data[0]);
        System.out.println("Ordered By         : " + data[1]);
        System.out.println("Item Name          : " + data[2]);
        System.out.println("Discounted Price   : " + data[4]);
        System.out.println("Delivery Address   : " + data[5]);
        System.out.println("Order Type         : " + data[6]);
        System.out.println("Assigned Chef(s)   : " + chefName);
        System.out.println("Estimated Time     : " + estimatedTime);
    }

    private boolean isAlreadyAssigned(String orderId){
        try(BufferedReader br = new BufferedReader(new FileReader(chefFile))){
            String line;
            while((line = br.readLine()) != null){
                if(line.startsWith(orderId)){
                    return true;
                }
            }
        } catch(IOException e){
            System.out.println("Error reading assigned chef file.");
        }
        return false;
    }
}
