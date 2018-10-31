package com.capgemini.mps.presentation;

import java.util.Scanner;

import com.capgemini.mps.dto.Mobile;
import com.capgemini.mps.exception.MobilePurchaseException;
import com.capgemini.mps.service.IMobileService;
import com.capgemini.mps.service.MobileServiceImpl;

public class MPSApp {
private static Scanner scanner = new Scanner(System.in);
private static IMobileService mobileService = new MobileServiceImpl();
	
public static void main(String[] args) {
		// TODO Auto-generated method stub
      Mobile mobile = new Mobile();
      getMobileDetails(mobile);
	  try {
      int n = addNewMobile(mobile);
	  System.out.println(n);
	  } catch(MobilePurchaseException e) {
		// Log error messages
		  System.out.println(e.getMessage());
	  }
	}
	private static int addNewMobile(Mobile mobile) throws MobilePurchaseException{
		return mobileService.addNewMobile(mobile);
	}
	
 private static void getMobileDetails(Mobile mobile) {
	 System.out.println("Enter mobile brand name");
	 mobile.setName(scanner.nextLine());
	 System.out.println("Enter mobile price");
	 mobile.setPrice(scanner.nextDouble());
	 System.out.println("Enter mobile quantity");
	 mobile.setQuantity(scanner.nextInt());
 }

}
