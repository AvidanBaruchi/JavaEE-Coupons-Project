<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- form for admin log in --%>
<div class="navbar-collapse collapse" id="navbar-main">
	<form class="navbar-form navbar-right" role="search">
		<div class="form-group">
			<input type="text" class="form-control" id="username" placeholder="Username">
		</div>
		<div class="form-group">
			<input type="password" class="form-control" id="password" placeholder="Password">
		</div>
		<button type="button" class="btn btn-default" onclick="tryToLogIn()">Sign In</button>
	</form>
</div>