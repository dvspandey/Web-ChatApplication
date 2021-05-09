<% // Here Check Session is really login 
	// if yes then show Dashboard
	// if No then forward(-,-) request to welcomePage.jsp 
		// with server message "Session TimeOut, Login Again"
	if(session.getAttribute("userLogin")==null){
	 	RequestDispatcher rd = request.getRequestDispatcher("welcomePage.jsp");
		request.setAttribute("sessionTimeOut", "true");
		rd.forward(request, response);
	}
	
	
	// Result coming from StoreChatServlet
	if(request.getAttribute("msgInsertResult")!=null){
		//output result when we give request to StoreChatServlet
		String msgInsertResult = (String)request.getAttribute("msgInsertResult");
		System.out.println("UserMessage ::"+msgInsertResult);
	}
	
 %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "icon" href = "img/mylogo.png" type = "image/x-icon">
    <link rel="stylesheet" href="css/chatWindows.css">
    <title>ChatWindow</title>
</head>

<body style="background-color: #070e1b;width: 400px;">

	
	<!-- #################################   Iformation About Developing  Team   ###################-->
    
    <!-- Developed by Devesh Pandey :: For more Info send Mail:: dvspandey10@gmail.com -->
    <!-- GitHub:: https://github.com/dvspandey -->
    <!-- Linkdin:: https://www.linkedin.com/in/dvspandey -->
   
    <!-- #################################   Iformation About Developing  Team  End  ###################-->


    <!-- ***************************************  Profile   ***************************************-->
    <div class="profile">
        <div class="users">
            <div class="user">
                <!-- //https://www.toptal.com/designers/htmlarrows/symbols/ -->
                <span class="face">&#9786;</span>  
               <span class="name">
               <%
               		out.println(session.getAttribute("email"));             
                %></span>
                <span class="name" style="color: snow;">YOU</span>
           </div>
           <div class="signs">
                <span class="sign">&#9886;</span>
                <span class="sign" style="color: yellow;">&#9993;</span>
                <span class="sign">&#9887;</span>
           </div>
           <div class="user">
                <span class="face">&#9786;</span>
               <span class="name">
               <% 
               	// Some Logic if User Sends Msg (becuse at that time page"chatWindowMain.jsp" will reload through Form) 
				if(request.getAttribute("msgInsertResult")==null)
				{	//[setting only when page-Reload OR new Get request comes]
         	      	  session.setAttribute("anotherUser",request.getParameter("user")); 
				}//else{ use it, becuse it is already set }
             	  out.println(session.getAttribute("anotherUser"));
               %></span>
               <span class="name" style="color: snow;">FRIEND</span>
           </div>
        </div>
    </div>
    <!-- ***************************************  Profile End  ***************************************-->
    
    


    
    
    <!-- ************************* Navigation Buttons *************************************** -->
    <div class="linkButton">
        <a href="userDashboard.jsp" style="text-decoration: none; color: cornsilk;" >DashBoard</a>
        <a href="userLogOut.jsp" style="text-decoration: none; color: cornsilk;" >LogOut</a>
    </div>
    <!-- ************************* Navigation Buttons End *************************************** -->







    <div class="vl">
        <!-- ***************************************  Iframe  ***************************************-->
        <iframe src="messagesScreen.jsp"> </iframe>
        <!-- ***************************************  Iframe End ***************************************-->
    </div>





    <!-- ***************************************  Form  ***************************************-->
    <div class="form">
        <form action="storeChaturl" method="POST">
            <input type="text" name="message" id=""  autocomplete="off" class="textBox">
            <input type="submit" value="SEND" class="submitButton">
        </form>
    </div>
    <!-- ***************************************  Form end  ***************************************-->


    <div class="vl" style="left:99%"></div>
</body>

</html>