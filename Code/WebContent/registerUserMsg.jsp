
<%  // Response for RegisterServlet --> Message for non-technical user   
	
	 /* 	Results can return::		
	  			
	 			insertNewUser
				------------
				InserUnSussessfull
				InsertSuccess
				UserAlreadyExist
				InternalServerError
	*/			
	
	
	String myResponse = "";
	
	if(request.getAttribute("result").equals("InserUnSussessfull")){
		myResponse +=  "<div>User Registration UnSuccessful</div>";
		myResponse +=  "<div style='color:red; font-size:2rem;'>: (</div>";
	}else if(request.getAttribute("result").equals("InsertSuccess")){
		myResponse +=  "<div>Successfully user Registered</div>";
		myResponse +=  "<div style='color:green; font-size:2rem;'>:)</div>";
	}else if(request.getAttribute("result").equals("UserAlreadyExist")){
		myResponse +=  "<div>User Already Registered</div>";
		myResponse +=  "<div style='color:red; font-size:2rem;'>: o</div>";
	}else if(request.getAttribute("result").equals("InternalServerError")){
		myResponse +=  "<div>Internal Server Error</div>";
		myResponse +=  "<div style='color:red; font-size:2rem;'>: |</div>";
		myResponse +=  "<div style='color:yellow;'>Try Again...!</div>";
	}
 	
 	//set request attributes
	request.setAttribute("myResponse", myResponse);
%>