package com.capgemini.mps.presentation;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.capgemini.mps.dto.Mobile;
import com.capgemini.mps.exception.MobilePurchaseException;
import com.capgemini.mps.service.IMobileService;
import com.capgemini.mps.service.MobileServiceImpl;

public class MPSTester {
	private static Scanner scanner=new Scanner(System.in);
	private static IMobileService mobileService=new MobileServiceImpl();
	
	public static void main(String[] args) {		
		while (true) {
			// show menu
			System.out.println();
			System.out.println();
			System.out.println("    Mobile Purchase System    ");
			System.out.println("_______________________________\n");
            System.out.println("1.Add Mobile ");
			System.out.println("2.Search Mobile Based On Price Range");
			System.out.println("3.Retrive All Mobiles");
			System.out.println("4.Delete Mobile");
			System.out.println("5.Purchase Mobile");
			System.out.println("6.Update Mobile Price");
			System.out.println("7.Get Mobile Details");
			System.out.println("8.Exit");
			System.out.println("________________________________");
			System.out.println("Select an option:");
			// accept option
			try {
				int option = scanner.nextInt();
				switch (option) {
				case 1: Mobile mobile = new Mobile();
			      getMobileDetails(mobile);
			      int n = addNewMobile(mobile);
				  System.out.println(n);
					  //TODO
					  break;
				 case 2: 
				  System.out.println("Enter mobileId: ");
				  Long mobileId2=scanner.nextLong();
				  mobile = mobileService.getMobileDetails(mobileId2);
				  System.out.println(mobile);
				  break;
					 
				case 3:
					  List<Mobile> mobileList2= getAllMobileDetails();
					  showMobiles(mobileList2);
					  break;
				case 4:
					  System.out.println("Enter mobileId: ");
					  Long mobileId=scanner.nextLong();
					  int status=deleteMobile(mobileId);
					  System.out.println(status);
					  break;
				/*case 5: 
					  System.out.println("Enter customer name(begin with uppercase and name cannot exceed 20 characters): ");
					  String name=scanner.nextLine();					 
					  System.out.println("Enter EmailId: ");
					  String emailId= scanner.nextLine();
					  scanner.nextLine();//clear KBD buffer
					  System.out.println("Enter 10-digit phone number");
					  Long phoneNumber=scanner.nextLong();
					  CustomerValidator validator=new CustomerValidator();
					  if(validator.isValidCustomerName(name)) {
						  if(validator.isValidEmail(emailId)) {
							  if(validator.isValidPhoneNumber(phoneNumber)) {
								  PurchaseDetails purchaseDetails=
										  	new PurchaseDetails();
								  purchaseDetails.setCustomerName(name);
								  purchaseDetails.setEmailId(emailId);
								  purchaseDetails.setPhoneNumber(phoneNumber);
								  System.out.println("Enter mobile Id: ");
								  Integer mid=scanner.nextInt();
								  try {
									if(mobileService.isValidMobileId(mid)) {
										  purchaseDetails.setMobileId(mid);
										  //TODO
									}else {
										System.out.println("Enter valid mobileId");  
									}
								} catch (MobilePurchaseException e) {									
									  System.out.println(e.getMessage());
								}
							  }else {
								  System.out.println("Enter valid phone number");
							  }
						  }else {
							  System.out.println("Enter valid emailId");
						  }
					  }else {
						  System.out.println("Enter valid customer name");
					  }
					  break;*/
				case 6:
				  System.out.println("Enter mobileId: ");
				  Long mobileId1=scanner.nextLong();
				  System.out.println("New Price");
				  Double newPrice = scanner.nextDouble();
				  int result = updateMobile(mobileId1, newPrice);
				  System.out.println(result);
				  break;
				case 7:
				case 8:
					System.out.print("Exit Mobile Purchase System");
					System.exit(0);					
				default:
					System.out.println("Enter a valid option[1-6]");
				}// end of switch
			}catch (InputMismatchException e) {
				scanner.nextLine();
				System.err.println("Please enter a numeric value, try again");
			} catch (MobilePurchaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}// end of while
	}

	private static int updateMobile(Long mobileId1, Double newPrice) throws MobilePurchaseException {
		// TODO Auto-generated method stub
		return mobileService.updateMobilePrice(mobileId1, newPrice);
	}

	private static int deleteMobile(Long mobileId) {
		try {
			int status=mobileService.deleteMobile(mobileId);
			return status;
		} catch (MobilePurchaseException e) {
			e.printStackTrace();
			 //Log to file
			 System.out.println(e.getMessage());
		}
		return 0;
	}

	/*private static List<Mobile> getMobilesPriceRange(double lowPrice, double highPrice) {
		try {
			return mobileService.getMobilesPriceRange(lowPrice, highPrice);
		} catch (MobilePurchaseException e) {	
			//Log to file
			System.out.println(e.getMessage());
		}
		return null;
	}
*/
	private static int addNewMobile(Mobile mobile) throws MobilePurchaseException{
		return mobileService.addNewMobile(mobile);
	}
	
 private static void getMobileDetails(Mobile mobile) {
	 
	 System.out.println("Enter mobile brand name");
	 scanner.nextLine();// to clear string buffer
	 mobile.setName(scanner.nextLine());
	 System.out.println("Enter mobile price");
	 mobile.setPrice(scanner.nextDouble()); 
	 System.out.println("Enter mobile quantity");
	 mobile.setQuantity(scanner.nextInt());
 }

	private static void showMobiles(List<Mobile> mobileList) {
		Iterator<Mobile> iterator=mobileList.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}

	private static List<Mobile> getAllMobileDetails() {
		List<Mobile> mobileList;
		try {
			mobileList = mobileService.getAllMobileDetails();
			return mobileList; 
		} catch (MobilePurchaseException e) {	
			//log to file
			System.out.println(e.getMessage());
		}
		return null;
	}

}


