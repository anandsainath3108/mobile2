package com.capgemini.mps.service;

import java.util.regex.Pattern;

/**
 * 
 * @author Admin
 * This class validates customer details
 */
public class CustomerValidator {
		
	/**
	 * 
	 * @param customer
	 * @return true if customer name is a sequence of 20 alphabets,
	 *  and should begin with upper-case letter else return false
	 */
	public Boolean isValidCustomerName(String name) {
		String regex= "^[A-Z][a-zA-Z]{0,19}]$";
		return Pattern.matches(regex,name);
	}
	
	/**
	 * 
	 * @param customer
	 * @return true if email is valid else return false.
	 * 
	 */
	public Boolean isValidEmail(String emailId) {
		String regex="^[a-zA-Z0-9._]+@[a-zA-Z]+.[a-zA-Z]{2,3}$";
		return Pattern.matches(regex,emailId);
	}
	
	/**
	 * 
	 * @param customer
	 * @return true if phone number is 10-digit number
	 * else returns false
	 */
	public Boolean isValidPhoneNumber(Long phoneNumber ) {
		String mobile=phoneNumber.toString();
		String regex="^[1-9][0-9]{9}$";
		return Pattern.matches(regex, mobile);
	}	
}
