<%@ page language="java" contentType="text/plain; charset=UTF-8" import="java.util.*,il.ac.hit.project.model.*,java.text.*" pageEncoding="UTF-8"%>

<%-- giving options to select coupons --%>
<br />
<center>
	<label for="usr">Sort By : </label> <select id="couponCategoryName" name="categoryName" class="selectpicker">
		<%
			for (ECategory catetgory : ECategory.values())
			{
		%>
		<option value="<%=catetgory.ordinal()%>"><%=catetgory.name()%></option>

		<%
			}
		%>
	</select>
	<div class="btn-group" role="group">
		<button type="button" class="btn" onclick="showCategory()">
			<span class="glyphicon glyphicon-sort"></span> Category
		</button>
	</div>
</center>
<br />
<center>
	<div class="btn-group" role="group">
		<button type="button" class="btn" onclick="updateCouponList()">
			<span class="glyphicon glyphicon-plus"></span> Show All
		</button>
	</div>
</center>
<br />
<center>
	<div class="btn-group" role="group">
		<button type="button" class="btn" onclick="showNearMe()">
			<span class="glyphicon glyphicon-globe"></span> Near me
		</button>
	</div>
	<select id="wantedRadius" class="selectpicker">
		<option value="5">5</option>
		<option value="10">10</option>
		<option value="15">15</option>
		<option value="20">20</option>
		<option value="30">30</option>
		<option value="50">50</option>
	</select> <label for="usr"> Kilometers</label>
</center>
<br />