<%@ page language="java" contentType="text/plain; charset=UTF-8" import="java.util.*,il.ac.hit.project.model.*,il.ac.hit.project.controller.*" pageEncoding="UTF-8"%>

<%-- get key value pairs attributes from selected session --%>
<%
	List<String> keys = (List<String>) request.getAttribute("keys");
	String sessionID = (String) request.getParameter("sessionID");
%>
<center>
	<label for="usr">Session ID:</label>
</center>
<center>
	<h2><%=sessionID%></h2>
</center>

<table id="keyValuePairTable" class="table table-hover">
	<thead>
		<tr>
			<th>Key</th>
			<th>Value</th>
		</tr>

	</thead>

	<tbody>
		<%
			//print to the form key value pairs
			for (String key : keys)
			{
		%>
		<tr>
			<td><span><%=key%></span></td>
			<td title="<%=session.getAttribute(key)%>"><span><%=session.getAttribute(key)%></span></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>

<button type="button" onclick="getSessionStatistic()">Back</button>