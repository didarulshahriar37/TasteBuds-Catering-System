package org.example;

import java.util.Scanner;

public class Main {
   public static void main() {
        Scanner sc = new Scanner(System.in);
        Authentication auth = new Authentication();

        System.out.println("=========== SELECT AN OPTION ===========");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Guest User");
        System.out.print("> ");

        int option = sc.nextInt();
        sc.nextLine();

        if(option == 1){
            auth.register();
        }
        else if (option == 2){
            System.out.println("Login");
        }
        else if (option == 3){
            System.out.println("Guest");
        }
        else{
            System.out.println("Invalid Option.");
        }

        sc.close();
    }
}
