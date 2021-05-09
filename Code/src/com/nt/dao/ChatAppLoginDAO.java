/**
 * @author Devesh Pandey
 * mailID:: dvspandey10@gmail.com
 */


/**
 * 	Results can return::		
  			
 			insertNewUser
			------------
			InserUnSussessfull
			InsertSuccess
			UserAlreadyExist
			InternalServerError
			
			
			
			userLoginWithCredentials 
			------------------------ 
			NotFound
			FoundAndMatched
			FoundButPasswordNotMatched
			InternalServerError
*/



package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.nt.bo.RegisterBO;
import com.nt.util.SqlQuerySupplier;

public final class ChatAppLoginDAO {
	private static final String NEW_USER_INSERT_QUERY = SqlQuerySupplier.getQuery("NEW_USER_INSERT_QUERY");
	private static final String USER_LOGIN_SELECT_QUERY = SqlQuerySupplier.getQuery("USER_LOGIN_SELECT_QUERY");
	
	public static String insertNewUser(RegisterBO bo,DataSource ds){
		String result="InserUnSussessfull";
		//write JDBC code for registration process
		try(Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(NEW_USER_INSERT_QUERY)){
			//set value to query param
			ps.setString(1, bo.getEmail());
			ps.setString(2, bo.getPassword());
			
			//execute the query
			if(ps.executeUpdate()==1) {
				result = "InsertSuccess";
			}
			 
		}catch(java.sql.SQLIntegrityConstraintViolationException sqlICV) {
			System.out.println(sqlICV.getMessage());
			result="UserAlreadyExist";
		}catch(SQLException sql) {
			System.out.println(sql.getMessage());
			result="InternalServerError";
		}catch(Exception e) {
			System.out.println(e.getMessage());
			result="InternalServerError";
		}
		
		return result;
	}//insertNewUser(-,-)

	
	
	public static String userLoginWithCredentials(RegisterBO bo, DataSource ds) {
		String result="NotFound";
		//write JDBC code for registration process
		try(Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(USER_LOGIN_SELECT_QUERY)){
			//set value to query param
			ps.setString(1, bo.getEmail());
			
			try(ResultSet rs=ps.executeQuery()){
			     //process the ResultSet object
			    if(rs.next()) {//a row selected
			    	String email = rs.getString(1);
			    	String password = rs.getString(2);
			    	
			    	if(bo.getEmail().equals(email) && bo.getPassword().equals(password)) {
			    		result = "FoundAndMatched";
			    	}else if(bo.getEmail().equals(email)) {
			    		result = "FoundButPasswordNotMatched";
			    	}
			    }
			    else {//no row selected
			    	result = "NotFound";//no row selected
			    }
		  }
		}catch(SQLException sql) {
			System.out.println(sql.getMessage());
			result="InternalServerError";
		}catch(Exception e) {
			System.out.println(e.getMessage());
			result="InternalServerError";
		}
		
		return result;
	}//userLoginWithCredentials(-,-)
	
		
}//class
