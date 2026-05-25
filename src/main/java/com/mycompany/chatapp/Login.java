package com.mycompany.chatapp;

/**
 *
 * @author Student
 */
public class Login {
    String username;
    String password;
    String phoneNumber;
//Checks if the user name is 5 charactetrs or less and contains an underscore
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
//Checks if the password is 8 charaters long and if it has a capital letter,number and a special character
    public boolean checkPasswordComplexity(String password){
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++){
            
            char c = password.charAt(i); 
            
            if(Character.isUpperCase(c)){
                hasCapital = true;
                
            } else if (Character.isDigit(c)){
                hasNumber= true;
                
            } else if(!Character.isLetterOrDigit(c))  { 
                hasSpecial= true;
            }
         }
         return password.length() >= 8 && hasCapital && hasNumber && hasSpecial; 
    }

    public boolean checkCellPhoneNumber(String phone) {
        return phone.startsWith("+27") && phone.length() <= 12;
    }
    
    public boolean loginUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    
    public String returnLoginStatus(boolean success) {
        if (success){
            return "Welcome " + username + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public String registerUser(String username, String password, String phone) {
        
        if (!checkUserName(username)) {
            return "Username is incorrectly formatted.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return "Password does not meet the complexity requirements.";
        }
        
        if (!checkCellPhoneNumber(phone)) {
            return "Phone number format is incorrect.";
        }
        
        this.username = username;
        this.password = password;
        this.phoneNumber = phone;
        
        return "User has been registered successfully.";
    }
    }
