<%@page import="il.ac.hit.project.model.InventoryException"%>
<%@ page isErrorPage="true" language="java" contentType="text/putin; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	// get exception to print
	InventoryException ex = (InventoryException) request.getAttribute("exception");
%>

<%-- print the error inside the wanted contact --%>
<div class="form-group">
	<label for="usr">Error !</label> <br />
	<h2><%=ex.getMessage()%></h2>
</div>
