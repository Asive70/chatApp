/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Scanner;

/**
 *
 * @author Student
 */

public class MainApp {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
         Login login = new Login();

//---REGISTRATION SECTION----
System.out.print("Enter a username: ");
String username = input.nextLine();

System.out.print("Enter a passsword: ");
String password = input.nextLine();

System.out.print("Enter your South Africa phone number (+27...): ");
String phone = input.nextLine();

//Call the registerUser method and store the message it returns
String response = login.registerUser(username,password,phone);

//Show the registration message
System.out.println(response);

//---LOGIN SECTION---
System.out.println("\n=== USER LOGIN ===");

System.out.print("Enter your username: ");
String loginUsername = input.nextLine();

System.out.print("Enter your password:");
String loginPassword = input.nextLine();

//Call loginUser to check if details match the Stored ones
boolean loggedIn = login.loginUser(loginUsername,loginPassword);

//Print out the correct login message
String loginMessage = login.returnLoginStatus(loggedIn);
System.out.println (loginMessage);
    }

}
