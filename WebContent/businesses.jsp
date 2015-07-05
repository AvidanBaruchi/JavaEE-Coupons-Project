<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,il.ac.hit.project.model.*"%>
<%@ taglib uri="/WEB-INF/tld/myCustomTags.tld" prefix="tag"%>

<%-- get the most updated businesses list from the data base by demand --%>
<table class="table table-hover">
	<thead>
		<tr>
			<th>Name</th>
			<th>See Coupons</th>
		</tr>
	</thead>
	<tbody>
		<%
			//scan wanted businesses and put them in the HTML table
			Collection<Business> businesses = (Collection<Business>) request.getAttribute("businesses");
			for (Business business : businesses)
			{
		%>
		<tag:AdminBusinessLine admin="<%=false%>" business="<%=business%>"></tag:AdminBusinessLine>
		<%
			}
		%>
	</tbody>
</table>