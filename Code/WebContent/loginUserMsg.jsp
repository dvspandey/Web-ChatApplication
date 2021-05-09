
<% // Response for LoginServlet --> Message for non-technical user    
	
	 /* 	Results can return::		
	  			
				userLoginWithCredentials 
				------------------------ 
				NotFound
				FoundAndMatched
				FoundButPasswordNotMatched
				InternalServerError
	*/	
	
	String myResponse = "";
	
	if(request.getAttribute("result").equals("NotFound")){
	
		myResponse +=  "<div>User dosen't Exist,</div>";
		myResponse +=  "<div style='color:red; font-size:2rem;'>: ( </div>";
		myResponse +=  "<div style='color:yellow; font-size:2rem;'>Register 1st...!</div>";
	
	}else if(request.getAttribute("result").equals("FoundAndMatched")){
	
		myResponse +=  "<div>User Login Successfully</div>";
		myResponse +=  "<div style='color:green; font-size:2rem;'>:)</div>";
		myResponse +=  "<div>Your DashBoard is Ready..! <a href='userDashboard.jsp' style='text-decoration: none; color: Yellow;'>Click Me</a></div>";
	
	}else if(request.getAttribute("result").equals("FoundButPasswordNotMatched")){
		
		myResponse +=  "<div>Password Not Matching</div>";
		myResponse +=  "<div style='color:yellow; font-size:2rem;'>: O</div>";
		myResponse +=  "<div style='color:pink;'>Try one more time..! </div>";
	
	}else if(request.getAttribute("result").equals("InternalServerError")){
		
		myResponse +=  "<div>Internal Server Error</div>";
		myResponse +=  "<div style='color:red; font-size:2rem;'>: |</div>";
		myResponse +=  "<div style='color:yellow;'>Try Again...!</div>";
	}
 	
 	//set request attributes
	request.setAttribute("myResponse", myResponse); 	
%>