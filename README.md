# Web-ChatApplication
## Application with JSPs and Servlets
.
.
.
 **It provides client application which runs on the users’ desktop and server application which runs on any machine on the network. To start chatting our client should get connected to a server where they can do group and private chatting.**
 
 
## Software requirements of the project
Windows & Higher version (32/64-bit) license copies as per requirement
Tomcat9 Server
JDBC connectivity


## Implementation Languages used to develop the project
- Java
- HTML
- CSS
- JavaScript
- Not using AJAX | Socket Programming here, but use Seprate two Frames.


## Database
The total number of tables in the database that was identified to build our system is 2.
1. CHATAPP_LOGIN 
   - This Table have several coulumns and this helps to **Login | Register | List all user **
     - EMAIL (pk)
     - PASSWORD
     - TIME_STAMP
2. CHATAPP_CHAT
   - This table have 4 columns and these columns helps to **Store | Retrive(Select)**  Chat messages into DataBase
     - MESSAGES
     - TIME_STAMP
     - MSG_FROM (FK)
       - REFERENCES CHATAPP_LOGIN ("EMAIL") ON DELETE CASCADE
     - MSG_TO (FK)
       - REFERENCES CHATAPP_LOGIN ("EMAIL") ON DELETE CASCADE


## Backend
**JDBC**: JDBC stands for Java Database Connectivity. It is a part of JavaSE (Java Standard Edition). JDBC API uses JDBC drivers to connect with the database.

## JSP & Servlets:
- Java Server Pages (JSP) is a server-side technology that allows development of Web-based applications.

- JSP stands for Java Server Page. It is used to create a web application just like Servlet technology. We can think of JSP as an extension to Servlet because it provides more functionality than servlets such as expression language, JSTL, etc.




## Frontend
###### HTML (HyperText Markup Language)

- HTML stands for HyperText Markup Language and it is a standard markup language for creating Web pages. It describes the structure of Web pages.

###### CSS (Cascading Style Sheets)

- CSS is a language that describes the style of an HTML document. It describes how HTML elements should be displayed.


## web.xml
* Here i use web.xml configuration file as my Properties file.
  - Because we can't use direct Properties file in our webapplication as per my knowledge But it is possible in Standalone java applications 
  - using **java.util.Properties** which helps to read properies file value because it is subclass of hashtabe so it contains keys and values pair
* web.xml is using for configur **welcomePage.jsp** as my welcome file
* web.xml is using to configure **LoginServlet.java**  mapping with url and enable LOAD-ON-STARTUP because at this time i don't know with Annotations to enable.
* web.xml contains JDBC related properties into **Servlet-Context** parameter to access into whole webapplication, | give reusability of code | esay to modification 
  - Becuse it provide 
    - consistency
    - Reusability


## Servlets used in the project
* Servlets used in the project 5
1. LoginServlet.java 
   - This servlet is use to handel Login related request and provide Non-techencal guidence to user to handel next steps.
   - This uses a helper class "com.nt.dao package" where login SQL Queries and Database connection logics exsist.
   - The - LoginServlet - is **Lode-on-startup=0** Servlet which contain init() method with user defined code
     - This code is a logic for giving **Servlet-Context** object to that helper class 
     - becuse helper classs is using driver class name, url, pwd, and SQL Queries from **web.xml** configuration file with the help of getInitparameter() method
     - of ServeltContext object

2. RegisterServlet.java
   - This servlet is use to handel register request comming to server and provide Non-techencal guidence to user to guide for next steps..
   - It also uses that same helper class which contains logic to register user into Database
   - That helper class also take query and JDBC properties form web.xml using ServletContext obj which is provided by **LoginServlet.java** on Load-on-startup init() method.
   
3. ListAllRegisteredUserServlet.java
   - This servlet get request and sends all user who are registered (except that user who requested for List of registered user) in webApplication as response
   - It not take any help of helper class but it self contains logic to connect DataBase and perform SQL operations 
   - It take JDBC properties and SQL query for select all users from database with the help of **ServletContext** object and **web.xml** configuration file
   
4. ShowSavedChatServlet.java
   - This servlet get request and sends all private Chat messages as a responce of perticular user.
   - It not take any help of helper class but it self contains logic to connect DataBase and perform SQL operations 
   - It take JDBC properties and SQL query for select all users from database with the help of **ServletContext** object and **web.xml** configuration file

5. StoreChatServlet.java
   - This servlet helps to store private chat into database.
   - It not take any help of helper class but it self contains logic to connect DataBase and perform SQL operations 
   - It take JDBC properties and SQL query for select all users from database with the help of **ServletContext** object and **web.xml** configuration file
   
