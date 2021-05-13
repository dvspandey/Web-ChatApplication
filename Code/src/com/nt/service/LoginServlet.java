/**
 * @author Devesh Pandey
 * mailID:: dvspandey10@gmail.com
 */

package com.nt.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nt.bo.RegisterBO;
import com.nt.dao.ChatAppLoginDAO_withOutDS;
import com.nt.util.SqlQuerySupplier;


//@WebServlet("/loginurl")
public class LoginServlet extends HttpServlet {

	/************ For DataSource ***************************
	 * //get Pooled JDBC connection

	//Dependency Injection
	@Resource(name="DsJndi")
	private DataSource ds;


	 *********************************************************/	

	@Override
	public void init() throws ServletException {
		ServletContext sc  = getServletContext();
		SqlQuerySupplier.setServletContext(sc);
		System.out.println("ServletContext is given to SqlQuerySupplier..");
	}

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
		// call userLoginWithCredentials(BO) method
		String result = ""+ChatAppLoginDAO_withOutDS.userLoginWithCredentials(bo);

		//create session if loginSuccess
		if(result.equals("FoundAndMatched")) {
			HttpSession ses = req.getSession();

			//flag
			ses.setAttribute("userLogin","true");
			//email
			ses.setAttribute("email",email);
			//userName extract from email
			String mailDomain = email.replaceAll("[^@]*?.@"," ").trim();
			ses.setAttribute("ChatApp-userName", email.replaceAll(mailDomain,""));//uses a regular expression pattern.
		}

		//process result
		RequestDispatcher rd = req.getRequestDispatcher("welcomePage.jsp");

		//set request attributes
		req.setAttribute("result", result);
		req.setAttribute("email", email);
		req.setAttribute("jspResponseFor", "LoginServlet");
		rd.include(req, res);		
		pw.close();
	}//doGet(-,-);

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}//doPost(-,-);

}//class
