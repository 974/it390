<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="jdodb.ShoppingList" %>
<%@page import="java.util.Scanner" %>
<%@page import="java.util.List"%>
<%@page import="java.util.NoSuchElementException" %>
<%@page import="jdodb.*" %>
<%@page import="javax.jdo.*"%>
<%@page import="sh.*"%>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">-->
<script src="app.js"></script>
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
        <li><a href="Account.jsp"><span class="glyphicon glyphicon-user"></span> My Account</a></li>
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

 <br>
 <br>
 <br>
 <br>
 <br>
 
 <h4>Under Construction</h4>
<h2 align="center">Shopping Made Easy</h2>
<br>  

<h3>Get started by choosing one of the below options from the navigation bar:</h3>

<h4 align="center">Generate Shopping List based off budget</h4>
<h4 align="center">Report a price of an item</h4>
  <nav class="navbar navbar-default navbar-static-bottom">
  <div class="container-fluid">
    <div class="navbar-footer">

    </div>
  </div>
</nav> 
</div>
</body>
</html>