/**
 * @author Devesh Pandey
 * mailID:: dvspandey10@gmail.com
 */

//            Without  using DataSource Object


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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nt.bo.RegisterBO;
import com.nt.util.SqlQuerySupplier;

public final class ChatAppLoginDAO_withOutDS {
	private static final String NEW_USER_INSERT_QUERY = SqlQuerySupplier.getQuery("NEW_USER_INSERT_QUERY");
	private static final String USER_LOGIN_SELECT_QUERY = SqlQuerySupplier.getQuery("USER_LOGIN_SELECT_QUERY");
	
	public static String insertNewUser(RegisterBO bo){
		String result="InserUnSussessfull";
		
//************************* Extra Code ****************************		
		
		String url = "jdbc:mysql://remotemysql.com:3306/PyotPCH2wz";
		String user = "PyotPCH2wz";
		String pass = "X1k3KeU9wQ";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException cnf) {
			System.out.println(cnf.getMessage());
		}
		
		//write JDBC code for registration process
		try(Connection con = DriverManager.getConnection(url,user,pass); PreparedStatement ps = con.prepareStatement(NEW_USER_INSERT_QUERY)){
//********************** Extra Code *******************************

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

	
	
	public static String userLoginWithCredentials(RegisterBO bo) {
		String result="NotFound";

//************************* Extra Code ****************************		
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				}catch (ClassNotFoundException cnf) {
					System.out.println(cnf.getMessage());
				}
				String url = "jdbc:mysql://remotemysql.com:3306/PyotPCH2wz";
				String user = "PyotPCH2wz";
				String pass = "X1k3KeU9wQ";
		
		//write JDBC code for Login process
			try(Connection con = DriverManager.getConnection(url,user,pass); PreparedStatement ps = con.prepareStatement(USER_LOGIN_SELECT_QUERY)){
//********************** Extra Code *******************************
				
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
