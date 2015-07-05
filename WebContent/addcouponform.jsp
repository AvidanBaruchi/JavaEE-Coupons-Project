<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,il.ac.hit.project.model.*"%>

<%-- generate form for adding new coupon --%>
<div class="form-group">
	<label for="usr">Name:</label> <input type="text" id="nameTXT" class="form-control" /> <br /> <br /> <label for="usr">Description:</label> <input type="text" id="desTXT" class="form-control" /> <br />
	<br /> <label for="usr">Expiration time:</label> <select id="daysToAdd" name="Date" class="selectpicker">
		<option value="1">1 day</option>
		<option value="2">2 days</option>
		<option value="5">5 days</option>
		<option value="10">10 days</option>
		<option value="15">15 days</option>
		<option value="30">1 month</option>
		<option value="60">2 months</option>
	</select> <br /> <br /> <label for="usr">Category:</label> <select id="addCouponCategoryName" name="categoryName" class="selectpicker">
		<%
			//print categories from the category enum
			for (ECategory catetgory : ECategory.values())
			{
		%>
		<option value="<%=catetgory.ordinal()%>"><%=catetgory.name()%></option>
		<%
			}
		%>
	</select> <br /> <br /> <label for="usr">Business name:</label> <select id="addCouponBusinessName" name="businessName" class="selectpicker">
		<%
			//print business from the server to counct the coupon with
			Collection<Business> businesses = (Collection<Business>) request.getAttribute("businesses");
			for (Business business : businesses)
			{
		%>
		<option value="<%=business.getId()%>"><%=business.getName()%></option>

		<%
			}
		%>
	</select>
	<button type="button" onclick="addCupon()">הוסף</button>
</div>

