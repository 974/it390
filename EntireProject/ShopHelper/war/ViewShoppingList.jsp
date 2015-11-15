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

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
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
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" style="color: #003399;">List 1</a>
        
        </h4>
      </div>
      <div id="collapse1" class="panel-collapse collapse in">
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
  ShoppingList s3 = (ShoppingList)(results.get(0));
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

    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse2" style="color: #003399;">List 1</a>
        </h4>
      </div>
      <div id="collapse2" class="panel-collapse collapse">
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
  ShoppingList s4 = (ShoppingList)(results.get(1));
  String s5 [] = s4.createStringOutputs();
  int idx= 0;
  for (int i = 0; i < s5.length;i++){
	  if(s5[i].indexOf("Total") == 0){
		  idx= i;
		  break;
	  }
	  if (i == 0 || i == 1){
		  if(i == 1){
			  String typeOut = s5[i].trim();
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
			  %><h4><%out.println(s5[i]); %></h4><%
		  }
		  continue;
	  }
	  %><tr><%
	  %><td><%
		  Scanner scanMe = new Scanner(s5[i]);
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

  %><h4> <%out.println(s5[idx]);%></h4> <%
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
  
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse3" style="color: #003399;">List 3</a>
        </h4>
      </div>
      <div id="collapse3" class="panel-collapse collapse">
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
  ShoppingList s6 = (ShoppingList)(results.get(2));
  String s7 [] = s6.createStringOutputs();
  int idx= 0;
  for (int i = 0; i < s7.length;i++){
	  if(s7[i].indexOf("Total") == 0){
		  idx= i;
		  break;
	  }
	  if (i == 0 || i == 1){
		  if(i == 1){
			  String typeOut = s7[i].trim();
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
			  %><h4><%out.println(s7[i]); %></h4><%
		  }
		  continue;
	  }
	  %><tr><%
	  %><td><%
		  Scanner scanMe = new Scanner(s7[i]);
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

  %><h4> <%out.println(s7[idx]);%></h4> <%
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
   

    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse4" style="color: #003399;">List 4</a>
        </h4>
      </div>
      <div id="collapse4" class="panel-collapse collapse">
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
  ShoppingList s8 = (ShoppingList)(results.get(3));
  String s9 [] = s8.createStringOutputs();
  int idx= 0;
  for (int i = 0; i < s9.length;i++){
	  if(s9[i].indexOf("Total") == 0){
		  idx= i;
		  break;
	  }
	  if (i == 0 || i == 1){
		  if(i == 1){
			  String typeOut = s9[i].trim();
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
			  %><h4><%out.println(s9[i]); %></h4><%
		  }
		  continue;
	  }
	  %><tr><%
	  %><td><%
		  Scanner scanMe = new Scanner(s9[i]);
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

  %><h4> <%out.println(s9[idx]);%></h4> <%
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



    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse5" style="color: #003399;">List 5</a>
        </h4>
      </div>
      <div id="collapse5" class="panel-collapse collapse">
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
  ShoppingList s10 = (ShoppingList)(results.get(4));
  String s11 [] = s10.createStringOutputs();
  int idx= 0;
  for (int i = 0; i < s11.length;i++){
	  if(s11[i].indexOf("Total") == 0){
		  idx= i;
		  break;
	  }
	  if (i == 0 || i == 1){
		  if(i == 1){
			  String typeOut = s11[i].trim();
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
			  %><h4><%out.println(s11[i]); %></h4><%
		  }
		  continue;
	  }
	  %><tr><%
	  %><td><%
		  Scanner scanMe = new Scanner(s11[i]);
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

  %><h4> <%out.println(s11[idx]);%></h4> <%
  }catch(NullPointerException e){
	  out.println("No shopping List");
  }catch(NoSuchElementException k){
	  out.println("No shopping List");
  }catch(IndexOutOfBoundsException j){
	  out.println("No shopping List");
  }
  pm.close();
  extent1.closeAll();
  %>
    </tbody>
  </table>         
        </div>
      </div>
    </div>
 </div>








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