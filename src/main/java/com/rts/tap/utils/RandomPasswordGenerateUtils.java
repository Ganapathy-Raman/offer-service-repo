package com.rts.tap.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class RandomPasswordGenerateUtils {

	
	private String generateRandomPassword(int length) {
	    // Define the characters to be used in the password
	    String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String lowerCase = "abcdefghijklmnopqrstuvwxyz";
	    String numbers = "0123456789";
	    String specialCharacters = "!@#$%^&*()-_=+<>?";
	    
	    // Combine all characters
	    String allCharacters = upperCase + lowerCase + numbers + specialCharacters;

	    // Use SecureRandom for better randomness
	    SecureRandom random = new SecureRandom();
	    StringBuilder password = new StringBuilder(length);

	    // Ensure that the password contains at least one upper case, one lower case, one number, and one special character
	    password.append(upperCase.charAt(random.nextInt(upperCase.length())));
	    password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
	    password.append(numbers.charAt(random.nextInt(numbers.length())));
	    password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

	    // Fill the rest of the password length with random characters
	    for (int i = 4; i < length; i++) {
	        password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
	    }

	    // Shuffle the password to avoid any predictable patterns
	    return shuffleString(password.toString());
	}

	// Helper method to shuffle the characters in the password
	private String shuffleString(String input) {
	    char[] characters = input.toCharArray();
	    SecureRandom random = new SecureRandom();
	    
	    for (int i = 0; i < characters.length; i++) {
	        int randomIndex = random.nextInt(characters.length);
	        // Swap characters
	        char temp = characters[i];
	        characters[i] = characters[randomIndex];
	        characters[randomIndex] = temp;
	    }
	    
	    return new String(characters);
	}
	
	
}
