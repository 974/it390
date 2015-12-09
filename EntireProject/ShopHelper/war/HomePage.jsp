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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='https://fonts.googleapis.com/css?family=Quicksand:400,700' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="shophelper.css">
<title>Shop Helper: Home</title>
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
<h2 align="center">Shopping Made Easy</h2>
<br>  
<p>Shop Helper is a web application that aims to address the problems of creating a shopping list. When you get ready to go shopping, you may not know what you want to get or need to get. In addition, you may waste money by not finding the right store within your budget to shop at. This wastes your time and money. This application can be used by millions of people. </p>
<p>There are two main features that our application offers: budget shopping list generation and an account system. The budget shopping list generation is our barebones list generation. It will allow you to supply a budget and a list type (i.e. thanksgiving for 10, part of 5, etc.). Based on this information, the appropriate store will be selected and a list of goods will be shown to you that fit within your budget. The account system allows users to login with their Gmail accounts once they have paid the subscription fee which we will discuss later. By logging in users have more features open to them. Reporting prices, adding names to shopping lists, seeing past generated shopping lists, and deleting shopping lists are the additional features. </p>
<p>The value proposition to the user is that you will save time and money by generating a budget shopping list and you can save even more time by viewing past generated shopping lists when you login if you need to. Another benefit of our list generation is that the user can be assured that the prices are correct. This is due to our reporting a price feature. It has an algorithm in place to calculate the mean prices while excluding outliers. </p>
<p>Shop Helper is different from its competitors as it offers a great value for the features it offers. Most of our competitors allow you to create shopping lists manually which doesn't really help you. For a monthly fee of $4.00 you can have access to our account system and gain access to the features listed above in addition to removing advertisements.</p>
 
  <nav class="navbar navbar-default navbar-static-bsottom">
  <div class="container-fluid">
    <div class="navbar-footer">

    </div>
  </div>
</nav> 
</div>
</body>
</html>