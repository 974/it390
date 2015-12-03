<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="jdodb.ShoppingList" %>
<%@page import="java.util.Scanner" %>
<%@page import="java.util.List"%>
<%@page import="java.util.NoSuchElementException" %>
<%@page import="jdodb.*" %>
<%@page import="sh.*" %>
<%@page import="javax.jdo.*"%>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
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
<br>

<%if(user==null){ %>
<c:redirect url="/ReportEntryPage.jsp"/>

<%}else{ %>
<form action ="/ReportEntryServlet" method="post" name="reportPriceAndName" onsubmit="validateNumeric();">
	<font size="4"><h2>Report a price </h2>
	<%
	String val = "";
	if(((String)request.getAttribute("chosenStore")) == null){
		val = (String)request.getAttribute("storeNa");
	}else{
		val =(String)request.getAttribute("chosenStore");
	}
	%>
	<input type="text" name="storeN" value="<%=val%>" readonly></font>
    <h3>Enter the item name</h3>
	<font size="4"><input type="text" name="itemN" list="dl" autocomplete="off"></font>
	<datalist id="dl">
	    	<%
	    PersistenceManager pm = PMF.get().getPersistenceManager();
	    String val1 = "";
	    String chosenStore = "";
	    if(((String)request.getAttribute("chosenStore")) == null){
	    	val1 = (String)request.getAttribute("storeNa");
	    	javax.jdo.Extent<jdodb.Store> extent1 = pm.getExtent(jdodb.Store.class, false);
	    	for(Store me : extent1){
	    		if(val1.equalsIgnoreCase(me.getStoreName())){
	    			chosenStore = me.getStoreID();
	    			break;
	    		}
	    	}
	    }else{
	    	val1 =(String)request.getAttribute("chosenStore");
	    	chosenStore = (String)request.getAttribute("storeID");
	    }
		javax.jdo.Extent<jdodb.Stock> extent1 = pm.getExtent(jdodb.Stock.class, false);
		for (Stock me : extent1) {
			//Find store to choose items from
    		if(me.getStoreID().equalsIgnoreCase(chosenStore)){
    			javax.jdo.Extent<jdodb.Item> extent2 = pm.getExtent(jdodb.Item.class, false);
    			//Find item to include in list
    			for(Item me1 : extent2){
    				if(me.getItemID().equalsIgnoreCase(me1.getItemID())){
    					%><option value="<% out.print(me1.getItemName());%>"></option><%
    				}
    			}
    		}
		}
		extent1.closeAll();
		
     	%>
     </datalist>
	<h3>Enter the price</h3>
	<font size="4"><input type="text" name="itemP"></font>
    </br></br>
    <button class="btn btn-default btn-lg" type="submit">Submit</button>  
 <%
 String s = "";
 try{
	 s = (String)request.getAttribute("processedState");
 }catch(Exception e){
	 s = null;
 }
 if(s !=null){
	 if(s.equalsIgnoreCase("T")){
		 %><h4>Report successfully processed!</h4><%
	 }else if(s.equalsIgnoreCase("F")){
		 %><h4>Report was not successfully processed!</h4><%
	 }
 }
 %>  
 </form>
<script>
	function validateNumeric(){
		var result = document.reportPriceAndName.itemP.value;
		result = result.trim();
		var result1 = document.reportPriceAndName.itemN.value;
		result1=result1.trim();
		if((result == null || isNaN(result) || result == "")&&(result1=="" || result1==null)){
			alert("Invalid item name and price!");
			document.getElementById("itemN").value ="n/a";
			document.getElementById("itemP").value ="-1";
		}
		if(result == null || isNaN(result) || result == ""){
			alert("Invalid Price!");
			document.getElementById("itemN").value ="n/a";
			document.getElementById("itemP").value ="-1";
		}else if(result1=="" || result1==null){
			alert("Invalid item name!");
			document.getElementById("itemN").value ="n/a";
			document.getElementById("itemP").value ="-1";
		}
	}
</script>
<%} %>
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