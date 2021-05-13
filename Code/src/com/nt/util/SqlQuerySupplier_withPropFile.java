/**
 * @author Devesh Pandey
 * mailID:: dvspandey10@gmail.com
 */


/**
 *  [NOT in USE THIS CLASS FOR NOW]
 * i'm unable to locate Properties file on tomcat server 
 * but it is possible with Standalone java application
 * we can do only if we use absolute path which make our WebApplication
 * OS-dependent
 * For Example You can Run This class as JavaApplication and it will Run
 * main method code and here it will successfully locate properties file
 * but in webapplication not possible as per my knowledge we go with Spring
 * FrameWorks
 * 
 * So i'm take help of ServletContext Object
 * 
 * SOLVED PROBLEM
 * ==============
 * 
 * By getServletContext().getResourceAsStream("path") ---> returns InputStream
===================================================

static Properties props;
props = new Properties();
props.load(getServletContext().getResourceAsStream("/WEB-INF/classes/com/nt/commons/sql.properties"));
System.out.println(props.getProperty("key"));


By getServletContext().getRealPath("path") ----> returns String
==========================================

static Properties props;
//locate properties file
String path = getServletContext().getRealPath("/WEB-INF/classes/com/nt/commons/sql.properties");
//Convert String to InputStream
InputStream is = new FileInputStream(path);
props = new Properties();
props.load(is);
System.out.println(props.getProperty("key"));

 * */


package com.nt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlQuerySupplier_withPropFile {
	
	// database = "oracle" | database = "mysql" 
	static String dataBase = "mysql";
	
	static Properties props;
	static String path = "";//getServletContext().getRealPath("/WEB-INF/classes/com/nt/commons/sqlquery.properties");
	static {
			
		try {
			//locate properties file
			InputStream is = new FileInputStream(path);
			//create Dependent class obj
			props = new Properties();
			props.load(is);
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//return query based on given purpose from properties file
	public static String getQuery(String purpous){
		String str = dataBase+"."+purpous;
		String query = props.getProperty(str);
		System.out.println("RquestString:: "+str+"/nQuery:: "+query);
		return query;
	}
	
	
	public static void main(String[] args) {
		System.out.println(new File(".").getAbsolutePath());
		System.out.println(getQuery("STORE_USERS_CHAT_QUERY"));
	}
	
	
}//class
