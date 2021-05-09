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
    <link rel="stylesheet" href="css/userDashboard.css">
    
    <title>userDashboard</title>

	<!-- <script type="text/javascript" language="JavaScript"  src="js/Validation.js">   </script>  -->
	<script>
        function validate(frm){

        //empty the existing form validation error messages
        document.getElementById("indicator").innerHTML="";



        //read form data
        let  user=frm.user.value;
        let flag=true;
        
        
        //----Client side form validations ---------
        if(user==""){
            flag=false;
            document.getElementById("indicator").innerHTML="Please Selcet AnyOne*"
            //frm.user.focus();
        }

        
        return flag;
        }

    </script>
    <noscript>
          <h2 style="color:green;text-align:center"> Please Enable Java script </h2>
    </noscript>
	
</head>

<body style="margin: 0px; background-color: #030133; font-family: 'Reggae One', cursive;">

    <!-- #################################   Iformation About Developing  Team   ###################-->
    
    <!-- Developed by Devesh Pandey :: For more Info send Mail:: dvspandey10@gmail.com -->
    <!-- GitHub:: https://github.com/dvspandey -->
    <!-- Linkdin:: https://www.linkedin.com/in/dvspandey -->
   
    <!-- #################################   Iformation About Developing  Team  End  ###################-->



    <div style="text-align: center; color: cornsilk; height: 35rem;">
       
        <lable style="font-size: 50px; margin-top: 2px; margin-bottom: 0px;">Welcome</lable>
       
        <a href="userLogOut.jsp" class="logOut">LogOut</a>
        <br>
       
        <lable style="margin-top: 0px; margin-bottom: 5rem;">To <lable style="color: rgb(158, 255, 113); margin: 0px;">Chatting</lable> WebApplication !</lable>
        <br>

        <lable style="    font-size: 20px;
        margin-top: 2px;
        margin-bottom: 2rem;
        float: left;
        position: absolute;
        left: 2rem;
        top: 5rem;">DashBoard</lable>
        <br>
        <hr>


        <!-- ************************************** Server Message Area ******************************************-->
        <div class="ServerMsg">
            <% 
	            out.println("<div>Welcome<lable style='color: rgb(158, 255, 113); margin: 0px;'>"+session.getAttribute("ChatApp-userName")+"</lable></div>");
	            out.println("<div>:)</div>");
	            out.println("<div><lable style='color: rgb(158, 255, 113); margin: 0px;'>"+session.getAttribute("email")+"</lable></div>");
            %>
        </div>
        <!-- ************************************** Server Message Area End ******************************************-->







        <!-- ************************************** Search Another User ******************************************-->
        <div class="ServerMsg" style="float: right; justify-content: space-between;">
            <form action="chatWindowMain.jsp" method="GET" onsubmit="return validate(this)">
                <div>Select<lable style="color: rgb(158, 255, 113); margin: 0px;">User!</lable></div>
                <div style="
                	overflow-y: scroll;
				    max-height: 10rem;
				    min-height: 10rem;
				    padding-top: 1rem;">
                    <table style="text-align: left;">
                    
	                    
	                    <tr><td><span id="indicator" style="color:red; text-align:center;"> </span></td></tr>
	                    <% 
	                     //<tr><td><input type="radio" name="user" value="user1@gmail.com" checked>user1@gmail.com</td></tr>
	                     
	                    	RequestDispatcher rd = request.getRequestDispatcher("listuserurl");
	                  		rd.include(request, response);
	                  		if(request.getAttribute("listOfuser")!=null){
	                  			out.println(request.getAttribute("listOfuser"));
	                  		}else{
	                  			out.println("<tr><td style='color:yellow;'>Internal Server Error,</td></tr> ");
	                  		}
	                    %>
                    </table>
                </div>
                <div><input type="submit" value="OpenChat" id="userSubmit"></div>
                
            </form>
        </div>
        <!-- ************************************** Search Another User End ******************************************-->
        


    </div>


    <hr>


    <footer>
        <div style="color: cornsilk;">
            <div style="text-align: center; font-size: 10px;">
                <div class="footer">
                    <h3 style="margin-bottom: 0px; margin-top: 0px;">ChatApp | All Rights Reserved 2021&copy; | Friend's
                        Productions</h3>
                </div>
                dvspandey10@gmail.com | <a href="https://github.com/dvspandey" style="text-decoration: none; color: cornsilk;" >https://github.com/dvspandey</a>
            </div>
        </div>
    </footer>
</body>

</html>
