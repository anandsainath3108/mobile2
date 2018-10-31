package com.capgemini.mps.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.mps.dto.Mobile;
import com.capgemini.mps.exception.MobilePurchaseException;
import com.capgemini.mps.utility.DBConnection;
import com.capgemini.mps.utility.MYSQLConnection;

public class MobileDaoImpl implements IMobileDAO {

    @Override
    public Integer addNewMobile(Mobile mobile) throws MobilePurchaseException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try     {
            connection=MYSQLConnection.getConnection();
            preparedStatement=connection.prepareStatement(QueryMapper.INSERT_MOBILE);
            preparedStatement.setString(1,mobile.getName());
            preparedStatement.setDouble(2, mobile.getPrice());
            preparedStatement.setInt(3, mobile.getQuantity());
            int n= preparedStatement.executeUpdate();
            return n;                
        }catch(SQLException e)      {
            //e.printStackTrace();
            //TODO:Log to file
            throw new MobilePurchaseException("Unable to add new mobile"+e.getMessage());   
            
        }catch(Exception e)     {
            throw new MobilePurchaseException(e.getMessage());
            
        }finally{               
            try {
                connection.close();
            } catch (SQLException e) {              
                e.printStackTrace();
            }
        }
    
    }

    @Override
    public Integer deleteMobile(Long MobileId) throws MobilePurchaseException {
        try (
           Connection connection=MYSQLConnection.getConnection();
           PreparedStatement preparedStatement=connection.prepareStatement(QueryMapper.DELETE_MOBILE);
        ){
        	preparedStatement.setLong(1, MobileId);
        	int n = preparedStatement.executeUpdate();
        	return n;
        }catch (SQLException e ) {
        	e.printStackTrace();
        	throw new MobilePurchaseException("Unable to delete mobile");
        }catch (IOException e) {
        	throw new MobilePurchaseException(e.getMessage());
        }
    	
        		
    }

    @Override
    public Mobile getMobileDetails(Long mobileId) throws MobilePurchaseException {
        // TODO Auto-generated method stub
    	try( 
				Connection connection=MYSQLConnection.getConnection();
		        CallableStatement callable = connection.prepareCall(QueryMapper.GET_MOBILE_PRICE);
		       ){
		           callable.registerOutParameter(2, java.sql.Types.VARCHAR);
		           callable.registerOutParameter(3, java.sql.Types.DOUBLE);
		           callable.registerOutParameter(4, java.sql.Types.INTEGER);
		           callable.setLong(1, mobileId);
		           callable.executeQuery();
		           Mobile mobile = new Mobile();
		           mobile.setMobileId(mobileId);
		           populateMobile(mobile, callable);
		           return mobile;
		           
		}catch (SQLException e ) {
        	e.printStackTrace();
        	throw new MobilePurchaseException("Unable to retrieve mobile");
        }catch (IOException e) {
        	throw new MobilePurchaseException(e.getMessage());
        }
    }

    @Override
    public List<Mobile> getAllMobileDetails() throws MobilePurchaseException {
        try(
        		Connection connection = MYSQLConnection.getConnection();
        		Statement statement = connection.createStatement();
        		){
        	ResultSet resultSet = statement.executeQuery(QueryMapper.SELECT_ALL_MOBILE);
        	//TODO
        	List<Mobile> mobileList = new ArrayList<>();
        	while(resultSet.next()) {
        		Mobile mobile = new Mobile();
        		populateMobile(resultSet, mobile);
        	    mobileList.add(mobile);
        	}
    		return mobileList;
    }catch(SQLException e) {
    	e.printStackTrace();
    	//LOG TO THE FILE
    	throw new MobilePurchaseException(e.getMessage()); 
    }catch(IOException e) {
    	e.printStackTrace();
    	throw new MobilePurchaseException(e.getMessage()); 
    }

}

	private void populateMobile(ResultSet resultSet, Mobile mobile) throws SQLException {
		mobile.setMobileId(resultSet.getLong("mobile_id"));
		mobile.setName(resultSet.getString("name"));
		mobile.setPrice(resultSet.getDouble("price"));
		mobile.setQuantity(resultSet.getInt("quantity"));
	}

	@Override
	public Integer updateMobilePrice(Long mobileId, Double newPrice) throws MobilePurchaseException {
		// TODO Auto-generated method stub
		try (
		           Connection connection=MYSQLConnection.getConnection();
		           PreparedStatement preparedStatement=connection.prepareStatement(QueryMapper.UPDATE_MOBILE_PRICE);
		        ){
			preparedStatement.setDouble(1,newPrice);    	
			preparedStatement.setLong(2,mobileId);
		        	int n = preparedStatement.executeUpdate();
		        	return n;
		        }catch (SQLException e ) {
		        	e.printStackTrace();
		        	throw new MobilePurchaseException("Unable to update mobile");
		        }catch (IOException e) {
		        	throw new MobilePurchaseException(e.getMessage());
		        }
	}

	@Override
	public Double getMobilePrice(Long mobileId) throws MobilePurchaseException {
		// TODO Auto-generated method stub
		return null;
		}

	private void populateMobile(Mobile mobile, CallableStatement callable) throws SQLException {
		// TODO Auto-generated method stub
		mobile.setName(callable.getString(2));
		mobile.setPrice(callable.getDouble(3));
		mobile.setQuantity(callable.getInt(4));
	}
	}

