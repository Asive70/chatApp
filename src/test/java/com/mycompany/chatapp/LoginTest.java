package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    // Created the Login object to test its methods
    Login login = new Login();

    // ==========================================
    // USERNAME TESTS
    // ==========================================

    @Test
    public void testValidUsername() {
        // "u_1" is valid because it has an underscore and is <= 5 characters
        boolean actual = login.checkUserName("u_1");
        assertTrue(actual, "Expected a correctly formatted username to return true.");
    }

    @Test
    public void testInvalidUsername() {
        // "user_123" is invalid because it is longer than 5 characters
        boolean actual = login.checkUserName("user_123");
        assertFalse(actual, "Expected an incorrectly formatted username to return false.");
    }

    @Test
    public void testInvalidUsernameMessage() {
        String expectedMessage = "Username is incorrectly formatted."; 
        String actualMessage = login.registerUser("user_123", "ValidPass1!", "+27861234567");
        
        assertEquals(expectedMessage, actualMessage);
    }

    // ==========================================
    // PASSWORD TESTS
    // ==========================================

    @Test
    public void testValidPassword() {
        // Valid password has >= 8 characters, a capital, a number, and a special character
        boolean actual = login.checkPasswordComplexity("Ch&&s312!");
        assertTrue(actual, "Expected a strong password to return true.");
    }

    @Test
    public void testInvalidPassword() {
        // Invalid because it lacks capitals, numbers, and special characters
        boolean actual = login.checkPasswordComplexity("password");
        assertFalse(actual, "Expected a weak password to return false.");
    }

    @Test
    public void testInvalidPasswordMessage() {
        String expectedMessage = "Password does not meet the complexity requirements."; 
        // Using a VALID username ("u_1") so it passes the username check and specifically fails on the password
        String actualMessage = login.registerUser("u_1", "password", "+2782123456");
        
        assertEquals(expectedMessage, actualMessage);
    }

    // ==========================================
    // PHONE NUMBER TESTS
    // ==========================================

    @Test
    public void testValidPhoneNumber() {
        // Valid SA number starting with +27 and length <= 12
        boolean actual = login.checkCellPhoneNumber("+27821234567");
        assertTrue(actual, "Expected a valid SA phone number to return true.");
    }

    @Test
    public void testInvalidPhoneNumber() {
        // Invalid because it starts with 0 instead of +27
        boolean actual = login.checkCellPhoneNumber("0821234567");
        assertFalse(actual, "Expected an invalid phone number to return false.");
    }

    @Test
    public void testInvalidPhoneNumberMessage() {
        String expectedMessage = "Phone number format is incorrect."; 
        // Using VALID username and password so it specifically fails on the phone number
        String actualMessage = login.registerUser("u_1", "ValidPass1!", "0821234567");
        
        assertEquals(expectedMessage, actualMessage);
    }

    // ==========================================
    // REGISTRATION & LOGIN TESTS
    // ==========================================
    
    @Test
    public void testValidRegistrationMessage() {
        String expectedMessage = "User has been registered successfully."; 
        String actualMessage = login.registerUser("u_1", "ValidPass1!", "+27821234567");
        
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testLoginSuccess() {
        // 1. Register the user first
        login.registerUser("u_1", "ValidPass1!", "+27821234567");
        
        // 2. Then test login with the exact same credentials
        boolean actual = login.loginUser("u_1", "ValidPass1!");
        assertTrue(actual, "Expected login to succeed with correct credentials.");
    }

    @Test
    public void testLoginFailure() {
        // 1. Register the user first
        login.registerUser("u_1", "ValidPass1!", "+27821234567");
        
        // 2. Then test login with the WRONG password
        boolean actual = login.loginUser("u_1", "WrongPass2@");
        assertFalse(actual, "Expected login to fail with incorrect credentials.");
    }
}