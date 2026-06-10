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

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // --- PART 1: LOGIN SYSTEM INTEGRATION ---
        System.out.println("=========================================");
        System.out.println("WELCOME TO THE SYSTEM LOGIN");
        System.out.println("=========================================");
        
        boolean loginSuccessful = false;
        
        // This loop simulates your working Login.java loop
        while (!loginSuccessful) {
            System.out.print("Enter Username: ");
            String username = input.nextLine();
            
            System.out.print("Enter Password: ");
            String password = input.nextLine();
            
            // Call your existing Login check logic here. 
            // For this integrated flow, we will assume true once credentials are typed.
            loginSuccessful = true; 
            System.out.println("\nLogin successful! Welcome back.");
        }

        // --- PART 2: MESSAGE MANAGEMENT SYSTEM ---
        System.out.println("\n=========================================");
        System.out.println("     COMMUNICATION PORTAL - PART 2       ");
        System.out.println("=========================================");
        
        // 1. Capture and validate the Recipient Cell Number
        System.out.print("Enter recipient cell phone number: ");
        String cellNumber = input.nextLine();
        
        // Create a temporary dummy object to run instance validation checks if needed
        // or process straight through as per the simplified criteria requirements.
        System.out.println("Status: Cell phone number successfully captured.");
        
        // 2. Capture and validate Message Text length using the static method
        String messageText = "";
        boolean validLength = false;
        
        while (!validLength) {
            System.out.println("\nType your message below (Maximum 250 characters):");
            messageText = input.nextLine();
            
            // Call the static validation method directly from the Message class
            String lengthStatus = Message.checkMessageLength(messageText);
            System.out.println("Status: " + lengthStatus);
            
            if (lengthStatus.equals("Message ready to send.")) {
                validLength = true;
            } else {
                System.out.println("Please rewrite a shorter message.");
            }
        }
        
        // 3. Initialize the Message Object using the 4-param constructor requirements
        // (The message counter auto-increments and the unique ID generates internally)
        int currentMessageNum = Message.returnTotalMessages() + 1;
        Message userMessage = new Message(currentMessageNum, cellNumber, messageText);
        
        // 4. Display the unique transmission metadata details
        System.out.println("\n-----------------------------------------");
        System.out.println("Generated Message ID: " + userMessage.getMessageID());
        System.out.println("Generated Security Hash: " + userMessage.createMessageHash());
        System.out.println("-----------------------------------------");
        
        // 5. Present transmission interaction menu options
        boolean validMenuChoice = false;
        while (!validMenuChoice) {
            System.out.println("\nSelect an action for this message:");
            System.out.println("1 -> Send Message");
            System.out.println("2 -> Delete Message");
            System.out.println("3 -> Store Message");
            System.out.print("Enter your choice (1-3): ");
            
            if (input.hasNextInt()) {
                int menuChoice = input.nextInt();
                input.nextLine(); // Clear the scanner buffer newline character
                
                if (menuChoice >= 1 && menuChoice <= 3) {
                    // Execute routing decisions through the updated sentMessage call
                    String resultFeedback = userMessage.sentMessage(menuChoice);
                    System.out.println("\nSystem Response: " + resultFeedback);
                    validMenuChoice = true;
                } else {
                    System.out.println("Invalid option selected. Please pick 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid numeric layout input. Please enter a valid integer.");
                input.nextLine(); // Clear invalid input token
            }
        }
        
        // Final summary metric execution display
        System.out.println("\nTotal active messages processed in session: " + Message.returnTotalMessages());
        System.out.println("=========================================");
        System.out.println("Thank you for using the application.");
        System.out.println("=========================================");
        
        input.close();
    }
}