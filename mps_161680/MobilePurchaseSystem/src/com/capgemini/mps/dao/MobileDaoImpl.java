package com.capgemini.mps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.mps.bean.Mobile;
import com.capgemini.mps.exception.MobilePurchaseException;
import com.capgemini.mps.util.DBConnection;

public class MobileDaoImpl implements IMobileDAO{

	@Override
	public String deleteMobile(Integer mobileId) throws MobilePurchaseException {
		try(
				Connection connection=DBConnection.getConnection();
				PreparedStatement preparedStatement=
					connection.prepareStatement(QueryMapper.DELETE_MOBILE);
		){
			preparedStatement.setInt(1, mobileId);
			int n= preparedStatement.executeUpdate();
			if(n>0) {
				return "SUCCESS";
			}else {
				return "FAIL";
			}
		}catch(SQLException e) {
			//log file
			throw new MobilePurchaseException("Technical Error. Refer to Logs");
		}
		
	}

	@Override
	public List<Mobile> getAllMobileDetails() throws MobilePurchaseException {
		int mobileCount=0;
		
		try(
			Connection connection=DBConnection.getConnection();	
			Statement statement=connection.createStatement();
		){
			ResultSet resultSet=
					statement.executeQuery(QueryMapper.RETRIEVE_ALL_MOBILES);
			List<Mobile> mobileList=new ArrayList<>();
			while(resultSet.next()) {
				mobileCount++;
				Mobile mobile=new Mobile();
				populateMobile(mobile,resultSet);
				mobileList.add(mobile);
			}
			if(mobileCount!=0) {
				return mobileList;
			}else {
				return null;
			}
		
		}catch(SQLException e) {
			//Log to file
			throw new MobilePurchaseException("Technical Error. Refer to Logs");
		}		
	}

	private void populateMobile(Mobile mobile, ResultSet resultSet) throws SQLException {
		mobile.setMobileId(resultSet.getInt("mobileid"));
		mobile.setName(resultSet.getString("name"));
		mobile.setPrice(resultSet.getDouble("price"));
		mobile.setQuantity(resultSet.getInt("quantity"));
	}

	@Override
	public List<Mobile> getMobilesPriceRange(Double lowPrice, Double highPrice) throws MobilePurchaseException {
		int mobileCount=0;
		try(
			Connection connection=DBConnection.getConnection();
			PreparedStatement preparedStatement=
			  connection.prepareStatement(QueryMapper.QUERY_MOBILE_RANGE);
		){
			preparedStatement.setDouble(1, lowPrice);
			preparedStatement.setDouble(2, highPrice);
			ResultSet resultSet=preparedStatement.executeQuery();
			List<Mobile> mobileList=new ArrayList<>();
			while(resultSet.next()) {
				mobileCount++;
				Mobile mobile=new Mobile();
				populateMobile(mobile,resultSet);
				mobileList.add(mobile);
			}
			if(mobileCount!=0) {
				return mobileList;
			}else {
				return null;
			}
			
		}catch(SQLException e) {
			//Log to file
			throw new MobilePurchaseException("Technical Error. Contact Logs");
		}
		
	}

	@Override
	public Boolean isValidMobileId(Integer mobileId) throws MobilePurchaseException {
		try(
			Connection connection=DBConnection.getConnection();
			PreparedStatement preparedStatement=
				connection.prepareStatement(QueryMapper.CHECK_VALID_MOBILEID);
		){
			preparedStatement.setInt(1, mobileId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			return false;
		}catch(SQLException e) {
			//log to file
			throw new MobilePurchaseException("Technical Error. Contact Logs");
		}
		
	}

}
