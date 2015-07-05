<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- giving options to the admin --%>
<div class="btn-group btn-group-justified navbar-form navbar-left" role="group" aria-label="..." id="adminFunctions">
	<div class="btn-group" role="group">
		<button type="button" class="btn btn-primary" onclick="addCuponClick()">
			<span class="glyphicon glyphicon-plus"></span> Add Coupons
		</button>
	</div>
	<div class="btn-group" role="group">
		<button type="button" class="btn btn-primary" onclick="addBusinessClick()">
			<span class="glyphicon glyphicon-plus"></span> Add Business
		</button>
	</div>
	<div class="btn-group" role="group">
		<button type="button" class="btn btn-primary" onclick="getSessionStatistic()">
			<span class="glyphicon glyphicon-question-sign"></span> Statistic
		</button>
	</div>
</div>