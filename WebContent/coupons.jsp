<%@ page language="java" contentType="text/plain; charset=UTF-8" import="java.util.*,il.ac.hit.project.model.*,java.text.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/myCustomTags.tld" prefix="tag"%>

<%-- get the most updated coupon list by demand from data base --%>
<table id="couponList" class="table table-hover">
	<thead>
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Expiration Date</th>
			<th>Location</th>
			<th>Business</th>
			<th>Purchase</th>
		</tr>
	</thead>
	<tbody>

		<%!private static Collection<Coupon> coupons;%>
		<%
			//scan wanted coupons and put them in the HTML table
			coupons = (Collection<Coupon>) request.getAttribute("coupons");
			for (Coupon coupon : coupons)
			{
				if (!coupon.isExpired())
				{
		%>
		<tag:CouponLine admin="<%=false%>" coupon="<%=coupon%>"></tag:CouponLine>


		<%
			}
			}
		%>
	</tbody>
</table>