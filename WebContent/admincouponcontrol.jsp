<%@ page language="java" contentType="text/plain; charset=UTF-8" import="java.util.*, il.ac.hit.project.model.*, java.text.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/myCustomTags.tld" prefix="tag"%>

<%-- get the most updated coupon list from the data base by demand with admin options --%>
<table id="couponList" class="table table-hover">
	<thead>
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Expiration date</th>
			<th>Is expired</th>
			<th>Business Name</th>
			<th>Controls</th>
		</tr>
	</thead>
	<tbody>
		<%!private static Collection<Coupon> coupons;%>
		<%
			//scan wanted coupons and put them in the HTML table
			coupons = (Collection) request.getAttribute("coupons");
			for (Coupon coupon : coupons)
			{
		%>
		<tag:CouponLine admin="<%=true%>" coupon="<%=coupon%>"></tag:CouponLine>
		<%
			}
		%>
	</tbody>
</table>