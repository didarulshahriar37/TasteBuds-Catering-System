package org.example;

import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Authentication auth = new Authentication();
        HeadChef headChef = new HeadChef();
        OrderService orderService = new OrderService();
        DeliveryManager deliveryManager = new DeliveryManager();

       System.out.println("============= TASTEBUDS CATERING SYSTEM =============");
        System.out.println("============= SELECT AN OPTION =============");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Guest User");
        System.out.print("> ");

        int option = sc.nextInt();
        sc.nextLine();

        String username = null;

        if(option == 1){
            username = auth.register();
        }
        else if (option == 2){
            username = auth.login();
        }
        else if (option == 3){
            System.out.println("Continuing As Guest User");
        }
        else{
            System.out.println("Invalid Option.");
            sc.close();
            return;
        }

        if("Head Chef".equals(username)){
            headChef.headChefInterface();
            sc.close();
            return;
        }

        if("Delivery Manager".equals(username)){
            deliveryManager.deliveryManagerInterface();
            sc.close();
            return;
        }

        boolean running = true;

        while(running){
            System.out.println("\n============= USER MENU =============");
            System.out.println("1.Place an order");
            System.out.println("2.View all orders");
            System.out.println("3.View Delivered orders");
            System.out.println("4.Add Feedback");
            System.out.println("5.View Your Feedbacks");
            System.out.println("6.Exit");
            System.out.print("> ");

            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1){
                if(username != null){
                    orderService.placeOrder(username);
                }
                else{
                    orderService.placeOrder("Guest");
                }
            }
            else if(choice == 2){
                if(username != null){
                    orderService.viewOrders(username);
                }
                else {
                    orderService.viewOrders("Guest");
                }
            }
            else if(choice == 3){
                orderService.viewDeliveredOrders();
            }
            else if (choice == 4){
                orderService.giveFeedback(username, sc);
            }
            else if (choice == 5){
                orderService.viewMyFeedbacks(username);
            }
            else if(choice == 6){
                running = false;
            }
        }

        sc.close();
    }
}
