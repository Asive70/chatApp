/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {
    
    @Test
    public void testMessageLengthValid() {
        String validText = "This is a standard message under the limit.";
        String expected = "Message ready to send.";
        String actual = Message.checkMessageLength(validText);
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageLengthInvalid() {
        String invalidText = "A".repeat(260); 
        String expected = "Message exceeds 250 characters by 10; please reduce the size.";
        String actual = Message.checkMessageLength(invalidText);
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckMessageID() {
        Message msg = new Message(1, "+27718693002", "Test message text");
        assertEquals("The generated Message ID should be 10 characters or fewer.", msg.checkMessageID());
    }

    @Test
    public void testCheckRecipientCellValid() {
        Message msg = new Message(1, "+27718693002", "Are we still on for tonight?");
        String expected = "Cell phone number successfully captured.";
        assertEquals(expected, msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCellInvalid() {
        Message msg = new Message(1, "0718693002", "Are we still on for tonight?");
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        assertEquals(expected, msg.checkRecipientCell());
    }

    @Test
    public void testCreateMessageHash() {
        Message msg = new Message(0, "+27718693002", "HI THANKS TONIGHT");
        String generatedHash = msg.createMessageHash();
        assertEquals("Hash should end with the correct number and words format", generatedHash.endsWith(":0:HITONIGHT"));
    }

    @Test
    public void testSentMessageSend() {
        Message msg = new Message(1, "+27718693002", "Hello!");
        String expected = "Message successfully sent.";
        assertEquals(expected, msg.sentMessage(1)); 
    }
    
    @Test
    public void testSentMessageStore() {
        Message msg = new Message(1, "+27718693002", "Hello!");
        String expected = "Message successfully stored.";
        assertEquals(expected, msg.sentMessage(3)); 
    }
    
    @Test
    public void testSentMessageDisregard() {
        Message msg = new Message(1, "+27718693002", "Hello!");
        String expected = "Press 0 to delete the message.";
        assertEquals(expected, msg.sentMessage(2)); 
    }
}