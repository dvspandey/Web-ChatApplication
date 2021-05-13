/**
 * @author Devesh Pandey
 * mailID:: dvspandey10@gmail.com
 */


package com.nt.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.bo.RegisterBO;
import com.nt.dao.ChatAppLoginDAO_withOutDS;


@WebServlet("/registerurl")
public class RegisterServlet extends HttpServlet {

/************ For DataSource ***************************
 * //get Pooled JDBC connection

	//Dependency Injection
	@Resource(name="DsJndi")
	private DataSource ds;


*********************************************************/	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		//set ContentType
		res.setContentType("text/html");
		
		//get form data
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		// store into BO object
		RegisterBO bo = new RegisterBO();
		bo.setEmail(email);
		bo.setPassword(password);
		
		/**   Code change For work without DataSource*/
		// call insertNewUser(BO) method
		String 	result = ChatAppLoginDAO_withOutDS.insertNewUser(bo); //persistableResult
			
		//process result
		//if success redirect to login page with with message "Successfully user Registered"
		RequestDispatcher rd = req.getRequestDispatcher("welcomePage.jsp");

		//set request attributes
		req.setAttribute("result", result);
		req.setAttribute("email", email);
		req.setAttribute("jspResponseFor", "RegisterServlet");
		rd.include(req, res);		
		
		pw.close();
	}//doGet(-,-);

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}//doPost(-,-);

}//class
