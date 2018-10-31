package com.capgemini.mps.dao;

public interface QueryMapper {
	public static final String RETRIEVE_ALL_MOBILES="SELECT * FROM mobiles";
	public static final String DELETE_MOBILE="DELETE FROM mobiles WHERE mobileid=?";
	public static final String QUERY_MOBILE_RANGE="SELECT * from mobiles where price >= ? AND price <= ?";
	public static final String CHECK_VALID_MOBILEID="SELECT * FROM mobiles WHERE mobileid=?";
	public static final String ADD_PURCHASE_DETAILS="INSERT INTO purchasedetails VALUES(purchaseid_sequence.NEXTVAL,?,?,?,SYSDATE,?)";

}
