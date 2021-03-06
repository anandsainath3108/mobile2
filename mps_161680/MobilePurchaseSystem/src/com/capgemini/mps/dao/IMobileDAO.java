package com.capgemini.mps.dao;

import java.util.List;

import com.capgemini.mps.bean.Mobile;
import com.capgemini.mps.exception.MobilePurchaseException;

public interface IMobileDAO {
	public String deleteMobile(Integer mobileId) throws MobilePurchaseException;
	public List<Mobile> getAllMobileDetails() throws MobilePurchaseException;
	public List<Mobile> getMobilesPriceRange(Double lowPrice,Double highPrice) throws MobilePurchaseException;
	public Boolean isValidMobileId(Integer mobileId) throws MobilePurchaseException;
}
