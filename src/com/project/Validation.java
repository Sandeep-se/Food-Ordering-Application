package com.project;

import java.util.regex.Pattern;

public class Validation {
	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
	private static final String PHONE_REGEX = "^[6-9]\\d{9}$";
	
	public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
	public static boolean isValidPhoneNumber(String phoneNumber) {
	        return Pattern.matches(PHONE_REGEX, phoneNumber);
	}
}
