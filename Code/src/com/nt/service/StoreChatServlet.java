/**
 * @author Devesh Pandey
 * mailID:: dvspandey10@gmail.com
 */


package com.nt.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.util.SqlQuerySupplier;


@WebServlet("/storeChaturl")
public class StoreChatServlet extends HttpServlet {

	/*
INSERT INTO "ADV_JAVA"."CHATAPP_CHAT" (MESSAGES, time_stamp, MSG_FROM, MSG_TO) VALUES ('i am user1', sysdate, 'user1@gmail.com', 'user2@gmail.com')
	 */


	private static final String STORE_USERS_CHAT_QUERY = SqlQuerySupplier.getQuery("STORE_USERS_CHAT_QUERY");

	/************ For DataSource ***************************
	 * //get Pooled JDBC connection

		//Dependency Injection
		@Resource(name="DsJndi")
		private DataSource ds;


	*********************************************************/	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = (String)req.getSession().getAttribute("email"); //current login user
		String anotherUser = (String)req.getSession().getAttribute("anotherUser");//another Selected user 
		String message = req.getParameter("message");
		String msgInsertResult="msgInserUnSussessfull";

		//validate message
		if(MsgInvalid(message)) {
			RequestDispatcher rd = req.getRequestDispatcher("chatWindowMain.jsp");
			msgInsertResult="msgNotValid";
			//set request attribute
			req.setAttribute("msgInsertResult", msgInsertResult);

			//if msg in not valid as per business rules then
			rd.forward(req, res);	
			System.out.println(" msg not valid as per business rules so, Request Forwarded.....");
		}else {

			
			//************************* Extra Code ****************************		
			
			
			//get access to servletContext obj
			ServletContext sc = getServletContext();
			//read context param values
			String driver = sc.getInitParameter("driver");
			String url = sc.getInitParameter("url");
			String dbuser = sc.getInitParameter("dbuser");
			String dbpwd = sc.getInitParameter("dbpwd");
			
			
			
			
			try {
				Class.forName(driver);
			}catch (ClassNotFoundException cnf) {
				System.out.println(cnf.getMessage());
			}
/*			String url = "jdbc:mysql://remotemysql.com:3306/PyotPCH2wz";
			String user = "PyotPCH2wz";
			String pass = "X1k3KeU9wQ";*/


			try(Connection con = DriverManager.getConnection(url,dbuser,dbpwd); PreparedStatement ps=con.prepareStatement(STORE_USERS_CHAT_QUERY)){
			//********************** Extra Code *******************************
				
				
				//set value to query param
				ps.setString(1, message);
				ps.setString(2, email);
				ps.setString(3, anotherUser);

				//execute the query
				if(ps.executeUpdate()==1) {
					msgInsertResult = "msgInsertSuccess";
				}						

			}catch(java.sql.SQLIntegrityConstraintViolationException fk) {
				System.out.print(fk.getMessage());
				msgInsertResult = "UserUnregistered";
			}
			catch(SQLException se) {
				System.out.print(se.getMessage());
			}
			catch(Exception e) {
				System.out.print(e.getMessage());
			}
			//set request attribute
			req.setAttribute("msgInsertResult", msgInsertResult);

			RequestDispatcher rd = req.getRequestDispatcher("chatWindowMain.jsp");
			rd.include(req, res);	
		}
	}//doGet(-,-);

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}//doPost(-,-);



	//validate message
	private static boolean MsgInvalid(String message) {

		if(message.trim().isEmpty() || message==null) {
			return true;
		}
		return false;
	}//MsgInvalid


}//class
