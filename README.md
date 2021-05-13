# Web-ChatApplication  `v1.0` :sunflower: 
### [JAVA Based WebApplication]    :100: 	:vertical_traffic_light:
                                              
- [x] Web-ChatApplication developer [@dvspandey](https://github.com/dvspandey) 	:sunglasses: 
- [x] Web-ChatApplication Deployment [Link](https://web-chatapp-dvs.herokuapp.com/). :clap: :horse_racing:
- [x] Web-ChatApplication [Flow Chart](https://github.com/dvspandey/Web-ChatApplication/blob/master/Document/PersistenceFlow.png) :+1:


<div style="text-align:center">
    <table>
     <tr>
      <td align=center> LOGO 	:heavy_check_mark: </td>
     </tr> 
     <tr>
     <td> <img src="https://github.com/dvspandey/Web-ChatApplication/blob/master/Code/WebContent/img/logo.jfif" /> </td>
     </tr>
    </table>
</div>

<!-- ![alt text](https://github.com/dvspandey/Web-ChatApplication/blob/master/Document/PersistenceFlow.png?raw=true) -->

### Application with `JSPs` and `Servlets` 


## Objective :eyes:
 **It provides client application which runs on the users’ desktop and server application which runs on any machine on the network. To start chatting our client should get connected to a server where they can do private chatting with registered users.**
 
 
## Software requirements of the project :rainbow:
Windows & Higher version (32/64-bit) license copies as per requirement
Tomcat9 Server
JDBC connectivity


## Implementation Languages used to develop the project :snowman_with_snow:
- Java :comet:
- HTML :comet:
- CSS :comet:
- JavaScript :comet:  
- Not using AJAX | Socket Programming here, but use Seprate two Frames. :umbrella:


## Database :blossom::blossom:
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


## Backend :surfing_woman:
**JDBC**: JDBC stands for Java Database Connectivity. It is a part of JavaSE (Java Standard Edition). JDBC API uses JDBC drivers to connect with the database.


## JSP & Servlets: :parrot:
- Java Server Pages (JSP) is a server-side technology that allows development of Web-based applications.

- JSP stands for Java Server Page. It is used to create a web application just like Servlet technology. We can think of JSP as an extension to Servlet because it provides more functionality than servlets such as expression language, JSTL, etc.




## Frontend :basketball_man:
###### HTML (HyperText Markup Language) :monkey:

- HTML stands for HyperText Markup Language and it is a standard markup language for creating Web pages. It describes the structure of Web pages.

###### CSS (Cascading Style Sheets) :monkey:

- CSS is a language that describes the style of an HTML document. It describes how HTML elements should be displayed.


## web.xml :elephant:
* Here i use web.xml configuration file as my Properties file.
  - Because we can't use direct Properties file in our webapplication as per my knowledge But it is possible in Standalone java applications 
    - `Now It is possible to locate .properties file through Servlet Check Closed Issues` [Click-Me](https://github.com/dvspandey/Web-ChatApplication/issues/1#issue-890667159) 
  - using **java.util.Properties** which helps to read properies file value because it is subclass of hashtable so it contains keys and values pair
* web.xml is using for configur **welcomePage.jsp** as my welcome file
* web.xml is using to configure **LoginServlet.java**  mapping with url and enable LOAD-ON-STARTUP because at this time i don't know with Annotations to enable.
* web.xml contains JDBC related properties into **Servlet-Context** parameter to access into whole webapplication, | give reusability of code | esay to modification 
  - Becuse it provide 
    - consistency
    - Reusability


## Servlets used in the project :parrot:
* Servlets used in the project 5
1. LoginServlet.java :feet:
   - This servlet is use to handel Login related request and provide Non-techencal guidence to user to handel next steps.
   - This uses a helper class "com.nt.dao package" where login SQL Queries and Database connection logics exsist.
   - The - LoginServlet - is **Lode-on-startup=0** Servlet which contain init() method with user defined code
     - This code is a logic for giving **Servlet-Context** object to that helper class 
     - becuse helper classs is using driver class name, url, pwd, and SQL Queries from **web.xml** configuration file with the help of getInitparameter() method
     - of ServeltContext object

2. RegisterServlet.java :feet:
   - This servlet is use to handel register request comming to server and provide Non-techencal guidence to user to guide for next steps..
   - It also uses that same helper class which contains logic to register user into Database
   - That helper class also take query and JDBC properties form web.xml using ServletContext obj which is provided by **LoginServlet.java** on Load-on-startup init() method.
   
3. ListAllRegisteredUserServlet.java :feet:
   - This servlet get request and sends all user who are registered (except that user who requested for List of registered user) in webApplication as response
   - It not take any help of helper class but it self contains logic to connect DataBase and perform SQL operations 
   - It take JDBC properties and SQL query for select all users from database with the help of **ServletContext** object and **web.xml** configuration file
   
4. ShowSavedChatServlet.java :feet:
   - This servlet get request and sends all private Chat messages as a responce of perticular user.
   - It not take any help of helper class but it self contains logic to connect DataBase and perform SQL operations 
   - It take JDBC properties and SQL query for select all users from database with the help of **ServletContext** object and **web.xml** configuration file

5. StoreChatServlet.java :feet:
   - This servlet helps to store private chat into database.
   - It not take any help of helper class but it self contains logic to connect DataBase and perform SQL operations 
   - It take JDBC properties and SQL query for select all users from database with the help of **ServletContext** object and **web.xml** configuration file
   

## Helper class  :eagle:
1. com.nt.util 	:dolphin:
   - SqlQuerySupplier.java
     - This class is help Login(LoginServlet.java) | Register(RegisterServlet.java) Servlet.
     - It takes Servlet Context object with help of init() method of Login(LoginServlet.java) servlet and,
     - Store that Object into Static Refrence Variable of ServletContext Type
     - Using this ServletContext object this class get all properties of JDBC and SQL queries from web.xml and,
     - This class have a getQuery(String purpose) method which returns SQL query as String to caller
2. com.nt.dao 	:dolphin:
   - ChatAppLoginDAO.java (Data Access Object)
     - Basically  this class is develop with feeling as Spring bean class which useful for perform persistent logic
     - but that need injuctions and injuction will happens with IOC container but i'm develop this application without Spring Framework so there is no IOC container
     - so there is no dependency injuction without IOC Container and no DataSource injucted that'sWhy there is no chance to get DataSouces object direct but,
     - DataSource it injucted to Servlet only. [I'm not using DataSource "pooled conection object"]
     - I use standard approch and work with DriverManager class | to load DataBase driver class and get connection.
     - But i prefer write one time that JDBC property at single place and use it multiple places 
     - so i decided to work with web.xml (becuse properties file not working ) and set all JDBC properties into ServletContext obj Parameters.
     - Servlet context objec is only accessble through servlet only that'swhy i use init() method of LoginServlet.java to give ServletContext obj indirectly to SqlQuerySupplier class. and,
     - finally ChatAppLoginDAO class get JDBC propertes using ServletContext object Or we can supply properties manually and,
     - SqlQuerySupplier class give query to perform Operations to ChatAppLoginDAO class

## JSP Files (JAVA SERVER PAGES) :construction:
- welcomePage.jsp :watermelon:
  - loginUserMsg.jsp :lemon:
  - registerUserMsg.jsp 	:pineapple:
- userDashboard.jsp :strawberry:
  - userLogOut.jsp :green_apple:
- chatWindowMain.jsp :grapes:
  - messagesScreen.jsp :orange:


## Conclusion :tokyo_tower:
This project is a chat application where you can chat with registerd friends. You will have to ensure that the tomcat9 server and the apache server are connected and they both should be open while running the project. This project covers many important fundamental aspect of Java language form OOPs concepts to the web development concepts.
