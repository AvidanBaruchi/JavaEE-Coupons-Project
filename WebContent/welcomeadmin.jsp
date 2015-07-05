<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	//get admin name from the session
	String username = (String) session.getAttribute("adminUsername");
%>
<%-- if admin successfully logged in there is a form that saying hello and have logout option --%>
<div class="navbar-collapse collapse" id="navbar-main">
	<form class="navbar-form navbar-right" role="search">
		<strong><span>welcome, <%=username%></span></strong>
		<button type="button" class="btn btn-default" onclick="logOut()">Log Out</button>
	</form>
</div>

