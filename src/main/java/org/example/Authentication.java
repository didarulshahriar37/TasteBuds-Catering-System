package org.example;

import java.io.*;
import java.util.Scanner;

public class Authentication {
    Scanner sc = new Scanner(System.in);
    String dataFolder = "data";

    public Authentication() {
        File folder = new File(dataFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public String register(){
        System.out.print("Your Role (User/ Driver): ");
        String role = sc.nextLine();

        if(role.equalsIgnoreCase("User")){
            return registerUser();
        } else if (role.equalsIgnoreCase("Driver")) {
            registerDriver();
        }
        else{
            System.out.println("Invalid Role");
        }
        return null;
    }

    private String registerUser(){
        System.out.print("Your Name: ");
        String name = sc.nextLine();
        System.out.print("Your Password: ");
        String password = sc.nextLine();

        try(FileWriter fw = new FileWriter(dataFolder + "/users.txt", true)){
            fw.write(name + ", " + password + "\n");
            System.out.println("User Registration Successful!");
            return name;
        }
        catch (IOException e){
            System.out.println("An error occurred.");
        }
        return null;
    }

    private void registerDriver(){
        System.out.print("Your Name: ");
        String name = sc.nextLine();
        System.out.print("Your Password: ");
        String password = sc.nextLine();
        System.out.print("Driving License: ");
        String license = sc.nextLine();

        try(FileWriter fw = new FileWriter(dataFolder + "/drivers.txt", true)){
            fw.write(name + ", " + password + ", " + license + "\n");
            System.out.println("Driver Registration Successful!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public String login(){
        System.out.print("Your Role (User/ Driver/Head Chef/ Delivery Manager): ");
        String role = sc.nextLine();

        if(role.equalsIgnoreCase("User")){
            return loginUser();
        }
        else if(role.equalsIgnoreCase("Driver")){
            loginDriver();
        }
        else if(role.equalsIgnoreCase("Head Chef")){
            return loginChef();
        }
        else if(role.equalsIgnoreCase("Delivery Manager")){
            return loginManager();
        }
        return null;
    }

    private String loginUser(){
        System.out.print("Your Name: ");
        String name = sc.nextLine();
        System.out.print("Your Password: ");
        String password = sc.nextLine();

        boolean success = authenticate("users.txt", name, password, 2);

        if (success){
            System.out.println("Login Successful!");
            return name;
        }
        System.out.println("Login Failed!");
        return null;
    }

    private void loginDriver(){
        System.out.print("Your Name: ");
        String name = sc.nextLine();
        System.out.print("Your Password: ");
        String password = sc.nextLine();
        System.out.print("Driving license: ");
        String license = sc.nextLine();

        authenticate("drivers.txt", name, password + ", " + license, 3);
    }

    private String loginChef(){
        System.out.print("Username: ");
        String name = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        boolean success = authenticate("headChef.txt", name, password, 2);

        if(success){
            System.out.println("Login Successful!");
            return "Head Chef";
        }
        System.out.println("Login Failed!");
        return null;
    }

    private String loginManager(){
        System.out.print("Username: ");
        String name = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        boolean success = authenticate("deliveryManager.txt", name, password, 2);

        if(success){
            System.out.println("Login Successful!");
            return "Delivery Manager";
        }
        System.out.println("Login Failed!");
        return null;
    }

    private boolean authenticate(String file, String name, String match, int fields) {
        try (BufferedReader br = new BufferedReader((new FileReader(dataFolder + "/" + file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
                if (data[0].equals(name)) {
                    String combined = (fields == 3) ? data[1] + ", " + data[2] : data[1];

                    return combined.equals(match);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return false;
    }
}
