<jsp:useBean id="business" scope="request" class="il.ac.hit.project.model.Business">
	<jsp:setProperty name="business" property="*" />
</jsp:useBean>

<%@ page language="java" contentType="text/plain; charset=UTF-8" import="java.util.*,il.ac.hit.project.model.*" pageEncoding="UTF-8"%>

<%-- generate form for editing existing business --%>
<div class="form-group">
	<label for="usr">ID:</label> <input id="id" type="text" class="form-control" value='<jsp:getProperty property="id" name="business"/>' disabled> <br /> <label for="usr">Name:</label> <input id="nameTXT"
		type="text" class="form-control" value='<jsp:getProperty property="name" name="business"/>'> <br /> <label for="usr">Location:</label>
	<div class="ui-widget">
		<input type="text" id="locationTXT" class="form-control" value='<jsp:getProperty property="location" name="business"/>' onkeydown="getLocation()" />
	</div>
	<br /> <br />
	<center>
		<button class="btn" type="button" onclick="editBusiness()">ערוך</button>
	</center>
</div>