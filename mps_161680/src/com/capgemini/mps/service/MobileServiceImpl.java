package com.capgemini.mps.service;

import java.util.List;

import com.capgemini.mps.dao.IMobileDAO;
import com.capgemini.mps.dao.MobileDaoImpl;
import com.capgemini.mps.dto.Mobile;
import com.capgemini.mps.exception.MobilePurchaseException;

public class MobileServiceImpl implements IMobileService {
            private IMobileDAO mobileDAO = new MobileDaoImpl();
	@Override
	public Integer addNewMobile(Mobile mobile) throws MobilePurchaseException {
		//TODO: Validate mobile Details
		return mobileDAO.addNewMobile(mobile);
	}

	@Override
	public Integer deleteMobile(Long MobileId) throws MobilePurchaseException {
		// TODO Auto-generated method stub
		return mobileDAO.deleteMobile(MobileId);
	}

	@Override
	public Mobile getMobileDetails(Long mobileId) throws MobilePurchaseException {
		// TODO Auto-generated method stub
		return mobileDAO.getMobileDetails(mobileId);
	}

	@Override
	public List<Mobile> getAllMobileDetails() throws MobilePurchaseException {
		// TODO Auto-generated method stub
		return mobileDAO.getAllMobileDetails();
	}

	@Override
	public Integer updateMobilePrice(Long mobileId, Double newPrice) throws MobilePurchaseException {
		// TODO Auto-generated method stub
		return mobileDAO.updateMobilePrice(mobileId, newPrice);
	}
}
