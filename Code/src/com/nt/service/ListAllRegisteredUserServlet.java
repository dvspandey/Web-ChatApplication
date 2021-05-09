/**
 * @author Devesh Pandey
 * mailID:: dvspandey10@gmail.com
 */


package com.nt.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.util.SqlQuerySupplier;

@WebServlet("/listuserurl")
public class ListAllRegisteredUserServlet extends HttpServlet {
	private static final String SELECT_ALL_USER_QUERY = SqlQuerySupplier.getQuery("SELECT_ALL_USER_QUERY");

/************ For DataSource ***************************
 * //get Pooled JDBC connection

	//Dependency Injection
	@Resource(name="DsJndi")
	private DataSource ds;

	//try(Connection con=ds.getConnection()){
*********************************************************/		
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String listOfuser = null;
		
//************************* Extra Code ****************************		
	
		//get access to servletContext obj
		ServletContext sc = getServletContext();
		//read context param values
		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String dbuser = sc.getInitParameter("dbuser");
		String dbpwd = sc.getInitParameter("dbpwd");
		
		try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				}catch (ClassNotFoundException cnf) {
					System.out.println(cnf.getMessage());
				}
		
/*				String url1 = "jdbc:mysql://remotemysql.com:3306/PyotPCH2wz";
				String user = "PyotPCH2wz";
				String pass = "X1k3KeU9wQ";
*/
		
		try(Connection con = DriverManager.getConnection(url,dbuser,dbpwd)){
//********************** Extra Code *******************************
			
			try(PreparedStatement ps=con.prepareStatement(SELECT_ALL_USER_QUERY)){
			    	 try(ResultSet rs=ps.executeQuery()){
			    		 listOfuser = "";
			    		 while(rs.next()) {
			    			 String email = rs.getString(1);
			    			 
			    			 //skip requested user
			    			 if(email.equals(req.getSession().getAttribute("email")))
			    				 continue;
			    			 
			    			 listOfuser += "<tr>";
			    			 listOfuser += "<td> <input type='radio' name='user' value='"+email+"'>"+email+"</td>";
			    			 listOfuser += "</tr>";
			    		 }//while
			    	 }//try2
			     }//try1
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//set request attribute
		req.setAttribute("listOfuser", listOfuser);
		
	}//doGet(-,-);

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}//doPost(-,-);

}//class
