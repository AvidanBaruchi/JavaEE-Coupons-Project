<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- get right form label depending on admin existence --%>
<%
	Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
	if (isAdmin)
	{
%>
Session Statistic
<%
	}
	else
	{
%>
Shopping Cart
<%
	}
%>