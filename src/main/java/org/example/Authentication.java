package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Authentication {
    Scanner sc = new Scanner(System.in);
    String dataFolder = "data";

    public Authentication() {
        File folder = new File(dataFolder);
        if (!folder.exists()) {
            folder.mkdirs(); // create folder if it doesn't exist
        }
    }

    public void register(){
        System.out.print("Your Role (User/ Driver): ");
        String role = sc.nextLine();

        if(role.equalsIgnoreCase("User")){
            registerUser();
        } else if (role.equalsIgnoreCase("Driver")) {
            registerDriver();
        }
        else{
            System.out.println("Invalid Role");
        }
    }

    private void registerUser(){
        System.out.print("Your Name: ");
        String name = sc.nextLine();
        System.out.print("Your Password: ");
        String password = sc.nextLine();

        try(FileWriter fw = new FileWriter(dataFolder + "/users.txt", true)){
            fw.write(name + ", " + password + "\n");
            System.out.println("User Registration Successful!");
        }
        catch (IOException e){
            System.out.println("An error occurred.");
        }
    }

    private void registerDriver(){
        System.out.print("Your Name: ");
        String name = sc.nextLine();
        System.out.println("Your Password: ");
        String password = sc.nextLine();
        System.out.println("Driving License: ");
        String license = sc.nextLine();

        try(FileWriter fw = new FileWriter(dataFolder + "/drivers.txt", true)){
            fw.write(name + ", " + password + ", " + license + "\n");
            System.out.println("Driver Registration Successful!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
