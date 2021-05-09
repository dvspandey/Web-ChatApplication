<%@page import="java.util.Date"%>
<% //For Developer Monitoring OutPut on Server-Console
java.util.Date date = new Date();

if(request.getAttribute("jspResponseFor")!=null){
	System.out.println("\n\n*********** "+date+" ************");
	System.out.println("Value of RequestParam:: ");
	System.out.println("email::"+request.getParameter("email"));
	System.out.println("password::"+request.getParameter("password"));
	System.out.println("\nOperation Perform:: "+request.getAttribute("jspResponseFor"));
	System.out.println("Result status:: "+request.getAttribute("result"));
	System.out.println("************************************\n\n");
}else{
	System.out.println("User Visit on WelcomePage:: "+date);
}
%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "icon" href = "img/mylogo.png" type = "image/x-icon">
 	<link rel="stylesheet" href="css/welcomePage.css">
    <title> welcomePage</title>

</head>

<body
    style="margin: 0px; background-color: #030133; font-family: 'Reggae One', cursive;">
    
    
        <!-- #################################   Iformation About Developing  Team   ###################-->
    
    <!-- Developed by Devesh Pandey :: For more Info send Mail:: dvspandey10@gmail.com -->
    <!-- GitHub:: https://github.com/dvspandey -->
    <!-- Linkdin:: https://www.linkedin.com/in/dvspandey -->
   
    <!-- #################################   Iformation About Developing  Team  End  ###################-->
    
    
    
    <div style="text-align: center; color: cornsilk; height: 35rem;">
        <h1 style="font-size: 50px; margin-top: 2px; margin-bottom: 0px;">Welcome</h1>
        <h3 style="margin-top: 0px; margin-bottom: 5rem;">To <lable style="color: rgb(158, 255, 113); margin: 0px;">Chatting</lable> WebApplication !</h3>
        <hr>
        <div class=LoginForm>
            <form action="loginurl" method="POST">
                <div style="height: 8rem;">
                    <label>Email ID:: </label><input type="email" name="email" id="" required autocomplete="off"
                        autofocus class="apply"
                    
                    <% // this scriplet use for re-write email if only password not matched But email matched || And add right tick
                    if(request.getAttribute("jspResponseFor")!=null){
                    	if(request.getAttribute("jspResponseFor").equals("LoginServlet")){
                    		if(request.getAttribute("result").equals("FoundButPasswordNotMatched")){
                    			out.println("value='"+request.getAttribute("email")+"'><");
                    			out.println("<span style='font-family: wingdings; font-size: 100%; color: rgb(158, 255, 113);'>&#252;</span>");       
                    		}else if(request.getAttribute("result").equals("FoundAndMatched")){
								out.println(">< <span style='font-family: wingdings; font-size: 100%; color: rgb(158, 255, 113);'>&#252;</span>");                    		
                    		}
                    	}
                    }
                    %> ><br><br>
                    
                    <label>PassWord:: </label><input type="password" name="password" id="" required class="apply"
                    
                    <% // this scriplet use add wrong tick
                    if(request.getAttribute("jspResponseFor")!=null){
                    	if(request.getAttribute("jspResponseFor").equals("LoginServlet")){
                    		if(request.getAttribute("result").equals("FoundButPasswordNotMatched")){
                    			out.println(">< <span style='font-family: wingdings; font-size: 100%; color: rgb(255, 0, 0);'>&#10008;</span>");       
                    		}else if(request.getAttribute("result").equals("FoundAndMatched")){
                    			out.println(">< <span style='font-family: wingdings; font-size: 100%; color: rgb(158, 255, 113);'>&#252;</span>");
                    		}
                    	}
                    }
                    %> ><br><br>
                </div>
                <input type="submit" value="LogIn" class="SubmitButton">
            </form>
        </div>

        <div class=SignUpForm>
            <form action="registerurl" method="POST">
                <div style="height: 8rem;">
                    <label>Email ID:: </label>&nbsp;&nbsp;&nbsp;<input type="email" name="email" id="" required
                        autocomplete="false" class="apply"
                       
                       <% // this scriplet use for re-write email if user Already Register OR Successfully Register 
                    if(request.getAttribute("jspResponseFor")!=null){
                    	if(request.getAttribute("jspResponseFor").equals("RegisterServlet")){
                    			out.println("value='"+request.getAttribute("email")+"'");
                    	}
                    }
                    %>  ><br><br>
                    <label>PassWord:: </label><input type="password" name="password" id="" required class="apply"><br><br>
                </div>
                <input type="submit" value="SignUp" class="SubmitButton">
            </form>
        </div>

        <!-- ************************************** Server Message Area ******************************************-->
        <div class="ServerMsg">
        <%  
        		if(request.getAttribute("jspResponseFor")==null && request.getAttribute("sessionTimeOut")==null && session.getAttribute("userLogin")==null){
   			       
   			        out.println("<div>Welcome User!</div>");
     				out.println("<div style='font-size:1.5rem;'>:)</div>");
        		
        		}else if(request.getAttribute("sessionTimeOut")!=null){
        			
        			if(request.getAttribute("sessionTimeOut").equals("true")){
        		    	out.println("<div>Session TimeOut,</div>");
     					out.println("<div style='font-size:1.5rem;'>Login Again..</div>");
     				}else if(request.getAttribute("sessionTimeOut").equals("logOut")){
     			    	out.println("<div>LogOut Successfully,</div>");
     					out.println("<div style='font-size:1.5rem;'>Thank.. You..  : )</div>");
     				}
        		
        		}else if(session.getAttribute("userLogin")!=null && request.getAttribute("jspResponseFor")==null){
        			
        			if(session.getAttribute("userLogin").equals("true")){
 						out.println("<div>You Already Login,</div>");
 						out.println("<div>With:: <span style='font-size:1.5rem; color:#9eff71;'>"+session.getAttribute("email")+"</span> </div>");
 						out.println("<div style='font-size:1.2rem;'>Go back to DashBoard <a href='userDashboard.jsp' style='text-decoration: none; color: Yellow;'>Click Me</a></div>");
        			}

        		}else if(request.getAttribute("jspResponseFor").equals("RegisterServlet")){
				
					RequestDispatcher rd = request.getRequestDispatcher("registerUserMsg.jsp");
					rd.include(request, response);

					//write final message
					out.println(request.getAttribute("myResponse"));				
				
				}else if(request.getAttribute("jspResponseFor").equals("LoginServlet")){
				
					RequestDispatcher rd = request.getRequestDispatcher("loginUserMsg.jsp");
					rd.include(request, response);
					
					//write final message
					out.println(request.getAttribute("myResponse"));
				}else{
				
        		    out.println("<div>Welcome User! [elseBlock]</div>");
     				out.println("<div style='font-size:1.5rem;'>:)</div>");
     			}
         %>
        </div>
        <!-- ************************************** Server Message Area End ******************************************-->

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