<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="jdodb.ShoppingList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="app.js"></script>
<title>Shop Helper</title>
</head>
<body>
<div ng-app="app" ng-controller="shopHelper as shop" class="container">
  <h1>Shop Helper</h1>
  
  <h2>What are your shopping needs?</h2>
   <form action ="/ShopHelperServlet" method="post" name="listMaker" ng-submit="shopHelper.submitBudget(budget)">
  <!--  <form name="listMaker" ng-submit="shopHelper.submitBudget(budget)">-->
    <h3>Budget</h3>
    <input ng-model="userBudget" type="text" name="budget">
    </br></br>
    <select name="options" ng-model="shopHelper.options">
      <option value="food">Food</option>
      <option value="clothing">Clothing</option>
      <option value="party">Party</option>
    </select>
    </br></br>
    <button class="btn btn-default btn-lg" type="submit">Submit</button>
    </br></br>
  </form>
  
  <h2>Your List:</h2>
  <ul>
  <p>
  <%
  try{
  ShoppingList s3 = (ShoppingList)request.getAttribute("outputList");
  String s [] = s3.createStringOutputs();
  for (int i = 0; i < s.length;i++){
	  if (s[i] == null) break;
	  %></br><% 
	  out.println(s[i]);
	  if (i == 0){%></br><% }

  }
  }
  catch(NullPointerException e){
	  e.printStackTrace();
  }
	 //<%=request.getAttribute("outputList")
  %>
 
  	</p>
  </form>
  </ul>
  
</div>
</body>
</html>