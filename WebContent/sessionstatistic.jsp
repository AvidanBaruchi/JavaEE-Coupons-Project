<%@ page language="java" contentType="text/plain; charset=UTF-8" import="java.util.*,il.ac.hit.project.model.*,il.ac.hit.project.controller.*" pageEncoding="UTF-8"%>

<%
	// hash table tahat represent session id and atributs
	Map<String, List<String>> atributesByID = (Map<String, List<String>>) request.getAttribute("statistic");
%>

<%-- generate form to show to the admin what sessions created on the serverand there's attributes --%>
<div class="form-group">
	<label for="usr">Number of sessions that created:</label>
	<center>
		<h2><%=atributesByID.size()%></h2>
	</center>

	<table id="sessionIDTable" class="table table-hover">
		<thead>
			<tr>
				<th>Session ID</th>
			</tr>

		</thead>

		<tbody>
			<%
				//print all session id's from the hash table
				for (String sessionID : atributesByID.keySet())
				{
			%>
			<tr>
				<td title="<%=sessionID%>" onclick="getKeyValuePairs('<%=sessionID%>')"><span><%=sessionID%></span></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</div>
