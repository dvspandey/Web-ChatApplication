<% // Here Check Session is really login 
	// if yes then show Dashboard
	// if No then forward(-,-) request to welcomePage.jsp 
		// with server message "Session TimeOut, Login Again"
	if(session.getAttribute("userLogin")==null){
	 	RequestDispatcher rd = request.getRequestDispatcher("welcomePage.jsp");
		request.setAttribute("sessionTimeOut", "true");
		rd.forward(request, response);
	}
	
 %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "icon" href = "img/mylogo.png" type = "image/x-icon">
    <link rel="stylesheet" href="css/messageScreen1.css">
    <title>messagesScreen</title>
</head>
<body style="color: blanchedalmond; background-color: #070e1b; height: 500px;width: 360px; overflow-x: hidden; font-family: 'Reggae One', cursive;" >

    <!-- #################################   Iformation About Developing  Team   ###################-->
    
    <!-- Developed by Devesh Pandey :: For more Info send Mail:: dvspandey10@gmail.com -->
    <!-- GitHub:: https://github.com/dvspandey -->
    <!-- Linkdin:: https://www.linkedin.com/in/dvspandey -->
   
    <!-- #################################   Iformation About Developing  Team  End  ###################-->


    <div id="ecran" >
        <ul style=" padding: 0px;">
           
           <% 
   	           // Set refresh, autoload time as 5 seconds
   				response.setIntHeader("Refresh", 5); 
           	
           	 RequestDispatcher rd = request.getRequestDispatcher("showSavedChaturl");
           	 rd.include(request, response);
           	 if(request.getAttribute("chat")!=null){
        		out.println(request.getAttribute("chat"));
          	 }else{ 
        		out.println("<tr><td style='color:yellow;'>Internal Server Error,</td></tr> ");
        	}
           	 
           	 // For AutoScroll Down messageScreen and show new Messages [It is not standard approch but we called it Jugaad..]
           	out.println("<li><input type='text' autocomplete='off' autofocus class='autoScrollDown'></li>");
           %>
        </ul>
    </div>
    

</body>
</html>