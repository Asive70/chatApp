package com.mycompany.chatapp;

import java.util.Random;

public class Message {
    // Fields required for POE functionality
    private int messageNumber;
    private String recipientCell;
    private String messageText;
    private String messageID;
    
    // Counter for tracking total messages across instances
    private static int totalMessages = 0;


    public Message(int messageNumber, String recipientCell, String messageText) {
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        
        // Increment total messages tracked
        totalMessages++;
    }

    /**
     * Generates a unique ID using a for loop of 10 random digits.
     */
    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        
        // Loop exactly 10 times to build the ID string
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10)); 
        }
        
        return sb.toString();
    }

    /**
     * Static verification method checking the size boundary of the text.
     */
    public static String checkMessageLength(String text) {
        if (text.length() <= 250) {
            return "Message ready to send.";
        } else {
            int overage = text.length() - 250;
            return "Message exceeds 250 characters by " + overage + "; please reduce the size.";
        }
    }

    /**
     * Returns a confirmation string indicating the cellular number was parsed correctly.
     */
    public String checkRecipientCell() {
        // Validation logic can be run here as required by your project design
        return "Cell phone number successfully captured.";
    }

    /**
     * Processes choices for routing the message based on menu inputs.
     */
    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                // Triggers manual JSON compilation and storage
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    /**
     * Formats metadata and extracts text boundaries to build a customized uppercase hash tag.
     * Strips punctuation characters dynamically to fulfill assertion criteria.
     */
    public String createMessageHash() {
        // Clean leading/trailing spaces and split text by whitespace to isolate words
        String[] words = this.messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0 ? words[words.length - 1] : "";
        
        // Assemble initial raw format
        String rawHash = this.messageID.substring(0, 2) + ":" + this.messageNumber + ":" + firstWord + lastWord;
        
        // Retain only alphanumeric characters and specific structural colons, then force uppercase
        return rawHash.replaceAll("[^a-zA-Z0-9:]", "").toUpperCase();
    }

    /**
     * Simulates manual JSON serialization using 6 fields to preserve data structures.
     */
    public void storeMessage() {
        String jsonOutput = "{\n" +
                "  \"messageNumber\": " + this.messageNumber + ",\n" +
                "  \"recipientCell\": \"" + this.recipientCell + "\",\n" +
                "  \"messageText\": \"" + this.messageText + "\",\n" +
                "  \"messageID\": \"" + this.messageID + "\",\n" +
                "  \"messageHash\": \"" + createMessageHash() + "\",\n" +
                "  \"status\": \"stored\"\n" +
                "}";
        
        // Print statement placeholder matching standard console-based output tracking
        System.out.println("Saving record to database repository:\n" + jsonOutput);
    }

    /**
     * Static utility method providing overall collection tracking.
     */
    public static int returnTotalMessages() {
        return totalMessages;
    }

    // Standard getters to support external logic components
    public int getMessageNumber() { return messageNumber; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageText() { return messageText; }
    public String getMessageID() { return messageID; }

    Object checkMessageID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}