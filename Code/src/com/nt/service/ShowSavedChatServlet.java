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


@WebServlet("/showSavedChaturl")
public class ShowSavedChatServlet extends HttpServlet {

	/*
	 Select Query refrence
	 =====================
	  select messages, to_char(time_stamp, 'dd-mon-yyyy hh24:mi:ss') as time_stamp, msg_from, msg_to from chatapp_chat
where msg_from IN ('user1@gmail.com', 'user2@gmail.com') AND
msg_to IN ('user1@gmail.com', 'user2@gmail.com')
ORDER by time_stamp ASC;

	Data
	====
	"MESSAGES"	"TIME_STAMP"				"MSG_FROM"			"MSG_TO"
	"Eleven"	"05/may/2021 08:41:12"	"user1@gmail.com"	"user2@gmail.com"
	"Ten"		"05/may/2021 08:43:02"	"user1@gmail.com"	"user2@gmail.com"
	"Nine"		"05/may/2021 08:43:10"	"user1@gmail.com"	"user2@gmail.com"
	"Eight"		"05/may/2021 08:43:17"	"user1@gmail.com"	"user2@gmail.com"
	"Seven"		"05/may/2021 08:43:23"	"user1@gmail.com"	"user2@gmail.com"


	 */


	private static final String SELECT_USERS_CHAT_QUERY = SqlQuerySupplier.getQuery("SELECT_USERS_CHAT_QUERY");

	/************ For DataSource ***************************
	 * //get Pooled JDBC connection

	//Dependency Injection
	@Resource(name="DsJndi")
	private DataSource ds;

	//try(Connection con=ds.getConnection()){
	 *********************************************************/	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = (String)req.getSession().getAttribute("email"); //current login user
		String anotherUser = (String)req.getSession().getAttribute("anotherUser");//another Selected user 

		String chat = null;


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

/*		String url1 = "jdbc:mysql://remotemysql.com:3306/PyotPCH2wz";
		String user = "PyotPCH2wz";
		String pass = "X1k3KeU9wQ";*/

		try(Connection con = DriverManager.getConnection(url,dbuser,dbpwd)){
		//********************** Extra Code *******************************

			try(PreparedStatement ps=con.prepareStatement(SELECT_USERS_CHAT_QUERY)){
				//set value to query param
				ps.setString(1, email);
				ps.setString(2, anotherUser);
				ps.setString(3, email);
				ps.setString(4, anotherUser);

				try(ResultSet rs=ps.executeQuery()){
					chat = "";
					while(rs.next()) {

						if(email.equals(rs.getString("MSG_FROM"))) {
							chat += "<li class='user1'>"+rs.getString("MESSAGES")+"</li><br>";
						}else {
							chat += "<li class='user2'>"+rs.getString("MESSAGES")+"</li><br>";
						}
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
		req.setAttribute("chat", chat);//PersistentData

		System.out.println("ShowChat RequestComming::"+email+" -- "+anotherUser );
	}//doGet(-,-);

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}//doPost(-,-);

}//class
