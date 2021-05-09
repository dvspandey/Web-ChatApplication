/**
 * @author Devesh Pandey
 * mailID:: dvspandey10@gmail.com
 */



package com.nt.util;

import javax.servlet.ServletContext;

public class SqlQuerySupplier {
	
	// database = "oracle" | database = "mysql" 
	static String dataBase = "mysql";
	//ServletContext ref variable
	static ServletContext sc;
	
	
	public static void setServletContext(ServletContext s) {
		SqlQuerySupplier.sc =s;
	}
	
	
	//return query based on given purpose from properties file
	public static String getQuery(String purpous){
		String str = dataBase+"_"+purpous;
		String query = sc.getInitParameter(str);
		System.out.println("RquestString:: "+str+"/nQuery:: "+query);
		return query;
	}
	
}//class
