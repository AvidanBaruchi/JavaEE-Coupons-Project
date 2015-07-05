<jsp:useBean id="coupon" scope="request" class="il.ac.hit.project.model.Coupon">
	<jsp:setProperty name="coupon" property="*" />
</jsp:useBean>

<%@ page language="java" contentType="text/plain; charset=UTF-8" import="java.util.*,il.ac.hit.project.model.*" pageEncoding="UTF-8"%>

<%-- generate form for editing existing coupon --%>
<div class="form-group">
	<label for="usr">ID:</label> 
	<input id="id" type="text" class="form-control" value='<jsp:getProperty property="id" name="coupon"/>' disabled> 
	<label for="usr">Name:</label> 
	<input id="nameTXT" type="text" class="form-control" value='<jsp:getProperty property="name" name="coupon"/>'>
	<label for="usr">Description:</label> 
	<input type="text" class="form-control" id="desTXT" value='<jsp:getProperty property="description" name="coupon"/>'>
	<br /> 
	<label for="usr">Expiration time:</label> 
	<select id="daysToAdd" name="Date" class="selectpicker">
		<option value="0">0 day</option>
		<option value="1">1 day</option>
		<option value="2">2 days</option>
		<option value="5">5 days</option>
		<option value="10">10 day</option>
		<option value="15">15 days</option>
		<option value="30">1 month</option>
		<option value="60">2 months</option>
	</select> 
	<br /> 
	<br /> 
	<button type="button" onclick="editCoupon()">עדכן</button>
</div>