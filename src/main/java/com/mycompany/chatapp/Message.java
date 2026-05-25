package com.mycompany.chatapp;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public final class Message {

    static String checkMessageLength(String validText) {
        return null;
    }

    // --- Instance Variables (Fields for a single message) ---
    private String messageID;
    private int messageNumber;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private String sendStatus;
    
// --- Static Variables (Session data across all messages) ---
    private static int totalMessages = 0;
    private static List<Message> sessionMessages = new ArrayList<>();

    // --- Constructor ---
    public Message(String messageID, int messageNumber, String recipientCell, String messageText) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        
        // Auto-generate the hash upon creation
        this.messageHash = createMessageHash();
        
        // Add to session tracking
        totalMessages++;
        sessionMessages.add(this);
    }

    Message(int i, String string, String test_message_text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    public boolean checkMessageID() {
        // Returns true if the message ID is not more than 10 characters
        if (this.messageID != null && this.messageID.length() <= 10) {
            return true;
        }
        return false;
    }

    public String checkRecipientCell() {
        // Validates recipient number: expects international code (e.g., +27) and max 10 chars after
        if (this.recipientCell == null) {
            return "Failure: Cell number cannot be empty.";
        }
        
        if (this.recipientCell.startsWith("+27") && this.recipientCell.length() <= 13) {
            return "Success: Valid recipient cell number.";
        } else {
            return "Failure: Invalid cell number format. Ensure it includes the country code.";
        }
    }

    public String createMessageHash() {
        // Builds and returns a simple message hash string based on other fields
        // Example logic: Concatenate ID and Number
        return "HASH_" + this.messageID + "_" + this.messageNumber;
    }

    public String sentMessage() {
        // Asks the user to Send, Disregard, or Store
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do with Message " + this.messageNumber + "?");
        System.out.println("1)Send");
        System.out.println("2) Store");
        System.out.println("3) Disregard");
        System.out.print("Choice: ");
        
        int choice = 0;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        }

        switch (choice) {
            case 1:
                this.sendStatus = "Sent";
                break;
            case 2:
                this.sendStatus = "Stored";
                storeMessage(); // Call the JSON save method
                break;
            case 3:
                this.sendStatus = "Disregarded";
                break;
            default:
                this.sendStatus = "Disregarded"; // Default fallback
                System.out.println("Invalid choice. Message disregarded.");
                break;
        }
        
        return "Message status updated to: " + this.sendStatus;
    }

    public String printMessages() {
        // Returns a formatted string of ALL messages sent during the session
          StringBuilder allMessages = new StringBuilder();
        allMessages.append("--- Session Messages ---\n");
        for (Message msg : sessionMessages) {
            allMessages.append("Message ").append(msg.messageNumber).append("\n");
            allMessages.append("To: ").append(msg.recipientCell).append("\n");
            allMessages.append("Status: ").append(msg.sendStatus).append("\n");
            allMessages.append("Text: ").append(msg.messageText).append("\n\n");
        }
        return allMessages.toString();
    }

    public int returnTotalMessages() {
        // Returns the total count of messages created
        return totalMessages;
    }

    public void storeMessage() {
        // Saves the message to a JSON file format manually (without external libraries)
        String fileName = "Message_" + this.messageID + ".json";
        
        // Constructing a basic JSON string manually
        String jsonOutput = "{\n" +
                "  \"messageID\": \"" + this.messageID + "\",\n" +
                "  \"messageNumber\": " + this.messageNumber + ",\n" +
                "  \"recipientCell\": \"" + this.recipientCell + "\",\n" +
                "  \"messageText\": \"" + this.messageText + "\",\n" +
                "  \"messageHash\": \"" + this.messageHash + "\",\n" +
                "  \"sendStatus\": \"" + this.sendStatus + "\"\n" +
                "}";

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonOutput);
            System.out.println("Message successfully saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the message: " + e.getMessage());
        }
    }

    Object sentMessage(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    }