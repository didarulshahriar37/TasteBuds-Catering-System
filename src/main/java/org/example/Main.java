package org.example;

import java.util.Scanner;

public class Main {
   public static void main() {
        Scanner sc = new Scanner(System.in);
        Authentication auth = new Authentication();
        OrderService orderService = new OrderService();

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
        }

        if(username != null){
            orderService.placeOrder(username);
        }
        else{
            orderService.placeOrder("Guest");
        }

        sc.close();
    }
}
