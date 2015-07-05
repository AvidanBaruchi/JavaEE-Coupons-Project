function tryToAutoLogIn() {
	$.ajax({
		url : 'controller/autologin',
		data : {},
		success : function(responseText) {
			$('#loginFunctions').html(responseText);
			tryToGetAdminControlDiv();
		}
	});
}

function tryToLogIn() {
	$.ajax({
		type : "POST",
		url : 'controller/login',
		data : {
			username : $('#username').val(),
			password : $('#password').val()
		},
		success : function(responseText) {
			$('#loginFunctions').html(responseText);
			tryToGetAdminControlDiv();
			refreshAll();
		}
	});
}

function tryToGetAdminControlDiv() {
	$.ajax({
		url : 'controller/adminfunctionsform',
		data : {},
		success : function(responseText) {
			$('#adminFunctions').html(responseText);
		}
	});
}

function logOut() {

	$.ajax({
		type : "POST",
		url : 'controller/logout',
		data : {},
		success : function(responseText) {
			$('#loginFunctions').html(responseText);
			tryToGetAdminControlDiv();
			refreshAll();
		}
	});
}