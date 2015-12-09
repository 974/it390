<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="jdodb.ShoppingList" %>
<%@page import="java.util.Scanner" %>
<%@page import="sh.*" %>
<%@page import="jdodb.*" %>
<%@page import="javax.jdo.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.NoSuchElementException" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='https://fonts.googleapis.com/css?family=Quicksand:400,700' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="shophelper.css">
<title>Shop Helper: View Shopping Lists</title>
</head>
<body>
<div class="container">
<nav class="navbar navbar-default navbar-static-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <h2><a href="HomePage.jsp">Shop Helper</a></h2>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li><a href="HomePage.jsp">Home</a></li>
        <li><a href="TestingThis.jsp">Budget Shopping</a></li>
        <li><a href="ReportEntryPage.jsp">Report Price</a></li> 
      </ul>
      <ul class="nav navbar-nav navbar-right">
      <%UserService userService = UserServiceFactory.getUserService(); 
 
		User user = userService.getCurrentUser();
   		if (user != null){
        	pageContext.setAttribute("user", user);
    
	  %>
        <li><a href="Account.jsp"><span class="glyphicon glyphicon-user"></span> ${fn:escapeXml(user.nickname)}</a></li>
        <li><a href="<%= userService.createLogoutURL(request.getRequestURI()) %>"><span class="glyphicon glyphicon-log-in"></span>  Sign Out</a></li>
        <%		
        	if(ProcessUser.userExists(user)==false){
				ProcessUser.userCreateAccount(user);
			}
		}else{ 
		%>
		 <li><a href="<%= userService.createLoginURL(request.getRequestURI()) %>"><span class="glyphicon glyphicon-log-in"></span> Sign in</a></li>
	  <%} %>
      </ul>
    </div>
  </div>
</nav>
<%if(user==null){ %>
<c:redirect url="/Account.jsp"/>

<%}%>
<%
	PersistenceManager pm = PMF.get().getPersistenceManager();
	javax.jdo.Extent<jdodb.UserAccount> extent1 = pm.getExtent(jdodb.UserAccount.class, false);
	String locatedUserID = "";
		for (jdodb.UserAccount me : extent1) {
			if(me.getUserName().equalsIgnoreCase(user.getEmail())){
				locatedUserID=me.getUserID();
				break;
			}
		}
		Query userLists = pm.newQuery("select from " + ShoppingList.class.getName() + " where userID == findMe");
		userLists.declareParameters("String findMe");
		List <ShoppingList> results = (List<ShoppingList>)userLists.execute(locatedUserID);
%>

  <h2>Your shopping lists</h2>
  <div class="panel-group" id="accordion">
  <%for(int oL = 0; oL < results.size();oL++){ %>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title" >
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse<%out.print(oL);%>" style="color: #003399;"><% out.print(((ShoppingList)(results.get(oL))).getName());%></a>
        	 <div align="right">
        	 	<form  action ="/RemoveListServlet" method="post"  name="deleteList" onsubmit="return confirmDeletion();">
        	 		<button type="submit" value="<%out.print(((ShoppingList)(results.get(oL))).getShopListID());%>"  name="deleteMe">Delete</button>
        		</form>
        	 </div>
        </h4>
      </div>
      <div id="collapse<%out.print(oL);%>" class="panel-collapse collapse in">
        <div class="panel-body">

      	<table class = "table table-condensed">
  		  <thead><tr>
		  <th class="text-center"><%

		  out.println("Item");
		  %></th>
		  <th class="text-center"><%

		  out.println("Mean Price");
		  %></th>
		  <th class="text-center"><%

		  out.println("Quantity");
		  %></th>
		  <th class="text-center"><%

		  out.println("Store");
		  %></th></tr></thead>
  <%
  try{
  ShoppingList s3 = (ShoppingList)(results.get(oL));
  String s [] = s3.createStringOutputs();
  int idx= 0;
  for (int i = 0; i < s.length;i++){
	  if(s[i].indexOf("Total") == 0){
		  idx= i;
		  break;
	  }
	  if (i == 0 || i == 1){
		  if(i == 1){
			  String typeOut = s[i].trim();
			  if(typeOut.equalsIgnoreCase("Type: thanksgiving dinner10")){
				  %><h4><%out.println("Type: Thanksgiving Dinner (~10 people)");%></h4><%
			  }else if (typeOut.equalsIgnoreCase("Type: continental breakfast2")){
				  %><h4><% out.println("Type: Continental Breakfast for one week(~2 people)"); %></h4><%
			  }else if(typeOut.equalsIgnoreCase("Type: halloween party5")){
				  %><h4><% out.println("Type: Halloween Party (~5 people)");%></h4><%
			  }else if(typeOut.equalsIgnoreCase("Type: back to school clothes2")){
				  %><h4><% out.println("Type: Back-to-school Clothes (~2 people)");%></h4><%
			  }
		  }else{
			  %><h4><%out.println(s[i]); %></h4><%
		  }
		  continue;
	  }
	  %><tr><%
	  %><td><%
		  Scanner scanMe = new Scanner(s[i]);
		  scanMe.useDelimiter(";");
		  String sk = scanMe.next().trim();
		  sk = sk.substring(10,sk.length());
		  out.println(sk);
		  
		  
	  %></td><%
	  %><td><%
		  String k = scanMe.next().trim();
		  k = k.substring(23,k.length());
		  out.println(k);
	  %></td><%
	  %><td><%
		  String j = scanMe.next().trim();
		  j = j.substring(9,j.length());
		  out.println(j);
	  %></td><%
	  %><td><%
		  String p = scanMe.next().trim();
		  p = p.substring(11,p.length());
		  out.println(p);
	  %></td><%
	  %></tr><%
	  scanMe.close();
  }

  %><h4> <%out.println(s[idx]);%></h4> <%
  }catch(NullPointerException e){
	  out.println("No shopping List");
  }catch(NoSuchElementException k){
	  out.println("No shopping List");
  }catch(IndexOutOfBoundsException j){
	  out.println("No shopping List");
  }
  %>
    </tbody>
  </table> 
 </div>
		</div>
         </div>
         
<%} %>

</div>

<script>
	function confirmDeletion(){
		var r = confirm("Are you sure you want to delete the shopping list?");
		if (r == true) {
		   	return true;
		} else {
		    return false;
		}
	}
</script>

<br><br><br>
  <nav class="navbar navbar-default navbar-static-bottom">
  <div class="container-fluid">
    <div class="navbar-footer">

    </div>
  </div>
</nav> 
</div>
</body>
</html>