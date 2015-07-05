<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- generate form for adding new business --%>
<div class="form-group">
	<label for="usr">Name:</label> <input type="text" id="nameTXT" class="form-control" /> <br /> <br /> <label for="usr">Location:</label>
	<div class="ui-widget">
		<input type="text" id="locationTXT" class="form-control" onkeydown="getLocation()" />
	</div>
	<br /> <br />
	<center>
		<button class="btn" type="button" onclick="addBusiness()">הוסף</button>
	</center>
</div>