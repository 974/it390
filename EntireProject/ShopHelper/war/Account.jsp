<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="jdodb.ShoppingList" %>
<%@page import="java.util.Scanner" %>
<%@page import="sh.*" %>
<%@page import="java.util.NoSuchElementException" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='https://fonts.googleapis.com/css?family=Quicksand:400,700' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="shophelper.css">
<title>Shop Helper</title>
</head>
<body>
<div class="container">
  <nav class="navbar navbar-default navbar-static-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <h2><a href="HomePage.jsp">Shop Helper</a></h2>
    </div>
      <ul class="nav navbar-nav">
      	<li><h4 align="center"><a href="HomePage.jsp">Home</a></h4></li>
      	<li> <h4 align="center"><a href="TestingThis.jsp">Budget Shopping</a></h4></li>
      	<li><h4 align="center"><a href="ReportEntryPage.jsp">Report price</a></h4></li>
      </ul>
     	<%UserService userService = UserServiceFactory.getUserService(); %>
 <% 
User user = userService.getCurrentUser();
    if (user != null){
        pageContext.setAttribute("user", user);
    
%>
<div class="navbar-form navbar-right"><br>
<h4 align="right"><a href="Account.jsp">My Account</a></h4><h4 align="right"><a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">(Sign out) </a></h4>
<%

		if(ProcessUser.userExists(user)==false){
				ProcessUser.userCreateAccount(user);
		}
}else{
%>
											      
<br>
<h4 align="right">
    <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
    </h4>
<%
    }
%>

</div>
</nav>
<%if(user!=null){ %>
<h2 align="center">Welcome, ${fn:escapeXml(user.nickname)}!</h2>
<h3 align="center">What would you like to do? </h3>
<button type="button" onclick="location.href='ViewShoppingList.jsp';" >View Shopping lists generated</button>
<br>
<%}else{ %>
<h2 align="center">Please Log in to view your account!</h2>
<%} %>
<br>
<br>


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