<% //here Inactive the Session
	// Userlogin = "false" [not required]
	session.invalidate();
	RequestDispatcher rd = request.getRequestDispatcher("welcomePage.jsp");
	request.setAttribute("sessionTimeOut", "logOut");
	rd.forward(request, response);
 %>