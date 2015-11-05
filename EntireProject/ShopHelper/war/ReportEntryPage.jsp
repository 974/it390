<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="jdodb.ShoppingList" %>
<%@page import="java.util.Scanner" %>
<%@page import="java.util.List"%>
<%@page import="java.util.NoSuchElementException" %>
<%@page import="jdodb.*" %>
<%@page import="javax.jdo.*"%>
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
      <h2 align="left"><a href="HomePage.jsp">Shop Helper</a></h2>
      <ul class="nav navbar-nav">
      	<li><h4 align="center"><a href="HomePage.jsp">Home</a></h4></li>
      	<li> <h4 align="center"><a href="TestingThis.jsp">Budget Shopping</a></h4></li>
      	<li><h4 align="center"><a href="ReportEntryPage.jsp">Report price</a></h4></li>
      </ul>

    </div>
  </div>
</nav>


  <br>
<div>

<form action ="/ReportEntryStoreServlet" method="post" name="listMaker">
<h2>Report a price</h2>
    <h3>Enter the Shop first</h3>
    <br>
    <font size="4">
    <select name="store">
    	<%
    	PersistenceManager pm = PMF.get().getPersistenceManager();
		javax.jdo.Extent<jdodb.Store> extent1 = pm.getExtent(jdodb.Store.class, false);
		for (Store me1 : extent1) {
    		%><option value="<% out.print(me1.getStoreID());%>"><% out.print(me1.getStoreName());%></option><%	
		}
		extent1.closeAll();
     	%>
      
    </select>
    </font>
    </br></br></br></br></br></br>
    <button class="btn btn-default btn-lg" type="submit">Submit</button>   
    <br><br> 
 </form>
</div>
 
 
  <nav class="navbar navbar-default navbar-static-bottom">
  <div class="container-fluid">
    <div class="navbar-footer">

    </div>
  </div>
</nav> 
</div>
</body>
</html>