<%@ page language="java" contentType="text/plain; charset=UTF-8" import="java.util.*,il.ac.hit.project.model.*,il.ac.hit.project.controller.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/myCustomTags.tld" prefix="tag"%>

<%-- get the most updated shopping cart items from the data base --%>
<table id="cartList" class="table table-hover">
	<thead>
		<tr>
			<th>Name</th>
			<th>Quantity</th>
		</tr>
	</thead>
	<tbody>
		<%
			Map<Coupon, ShoppingCartLine> lines = (Map<Coupon, ShoppingCartLine>) request.getAttribute("cartlist");
			Iterator<ShoppingCartLine> iterator = lines.values().iterator();
			//iterate throw the cart line collection
			while (iterator.hasNext())
			{
				ShoppingCartLine line = iterator.next();
		%>
		<tag:CartLine line="<%=line%>"></tag:CartLine>
		<%
			}
		%>
	</tbody>
</table>