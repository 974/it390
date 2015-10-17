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
      <h3>Shop Helper</h3>
    </div>
  </div>
</nav>
    <div class="page header">
    <h1>Shop Helper</h1>
  </div>
  <h2>What are your shopping needs?</h2>
   <form action ="/ShopHelperServlet" method="post" name="listMaker" ng-submit="shopHelper.submitBudget(budget)">
    <h3>Type</h3>
    <select name="options" ng-model="shopHelper.options">
       <option value="continental breakfast2">Continental Breakfast (~2 people)</option>
      <option value="thanksgiving dinner5">Thanksgiving Dinner(~5 people)</option>
      <option value="back to school clothes2">Back-to-school Clothes(~2 people)</option>
      <option value="halloween party5">Halloween party(~5 people)</option>
    </select>
    </br></br>
    <h3>Budget</h3>
    <input ng-model="userBudget" type="text" name="budget">
    </br></br>
    <button class="btn btn-default btn-lg" type="submit">Submit</button>
    </br></br>
    
  </form>

  
  <h2>Your List:</h2>
  <p>
  <table class = "table table-condensed">
  		  <thead><tr>
		  <th class="text-center"><%

		  out.println("Item");
		  %></th>
		  <th class="text-center"><%

		  out.println("Average Reported Price");
		  %></th>
		  <th class="text-center"><%

		  out.println("Quantity");
		  %></th>
		  <th class="text-center"><%

		  out.println("Store");
		  %></th></tr></thead>
  <%
  try{
  ShoppingList s3 = (ShoppingList)request.getAttribute("outputList");
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
			  if(typeOut.equalsIgnoreCase("Type: thanksgiving dinner5")){
				  %><h4><%out.println("Type: Thanksgiving Dinner (~5 people)");%></h4><%
			  }else if (typeOut.equalsIgnoreCase("Type: continental breakfast2")){
				  %><h4><% out.println("Type: Continental Breakfast (~2 people)"); %></h4><%
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
  }
  %> </tbody><%
  %></table> <%
  %><h4> <%out.println(s[idx]);%></h4> <%
  }catch(NullPointerException e){
	  e.printStackTrace();
  }catch(NoSuchElementException k){
	  k.printStackTrace();
  }
  %>
 
 </p>
  </form>
  <nav class="navbar navbar-default navbar-fixed-bottom">
  <div class="container-fluid">
    <div class="navbar-footer">

    </div>
  </div>
</nav> 
</div>
</body>
</html>