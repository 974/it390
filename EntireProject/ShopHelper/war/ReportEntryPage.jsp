<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="jdodb.ShoppingList" %>
<%@page import="java.util.Scanner" %>
<%@page import="java.util.NoSuchElementException" %>
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
<div ng-app="app" ng-controller="shopHelper as shop" class="container">
  <nav class="navbar navbar-default navbar-fixed-top"">
  <div class="container-fluid">
    <div class="navbar-header">
      <h3><a href="TestingThis.jsp">Shop Helper</a></h3>
      <h4 align="right"><a href="ReportEntryPage.jsp">Report price</a></h4>
    </div>
  </div>
</nav>
    <div class="page header">
    <h1>Shop Helper</h1>
  </div>
  <h2>Report a price</h2>
<div id="reportPrice">


</div>

  
 
  <nav class="navbar navbar-default navbar-fixed-bottom">
  <div class="container-fluid">
    <div class="navbar-footer">

    </div>
  </div>
</nav> 
</div>
</body>
</html>