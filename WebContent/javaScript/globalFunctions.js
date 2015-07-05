function refreshAll() {
	updateCouponList();
	updateShoppingCart();
	updateBusinessList();
}

function WindowOnload() {
	tryToAutoLogIn();
	getCouponSelectionElement();
	refreshAll();
	getLocalLocation();
}

var latitude;
var longitude;

function getCouponSelectionElement() {
	$.ajax({
		url : 'controller/couponselection',
		data : {},
		success : function(responseText) {
			$('#couponselectionDiv').html(responseText);
		}
	});
}

function getLocalLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			latitude = position.coords.latitude;
			longitude = position.coords.longitude;
		}, function(error) {
			alert('no location')
		});
	}
}

function clearTextFromTextBox(elementFromTheSAmeLeve) {
	var childrenElements = elementFromTheSAmeLeve.parentNode.children;
	var index = 0;

	while (childrenElements[index] != null) {
		var element = childrenElements[index];

		if (element.type == "text") {
			if (element.disabled == false) {
				element.value = "";
			}
		}

		index++;
	}
}

function getLocation() {
	var geocoder = new google.maps.Geocoder();
	var locationInputElement = document.getElementById("locationTXT");
	var address = locationInputElement.value;
	var outoCompliteArray = [];

	$(locationInputElement).autocomplete({
		source : outoCompliteArray,
		change : function(event, ui) {
			getLocation();
		}
	});

	geocoder.geocode({
		'address' : address
	}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {

			latitude = results[0].geometry.location.k;
			longitude = results[0].geometry.location.D;

			for (var i = 0; i < results.length; i++) {
				outoCompliteArray.push(results[i]["formatted_address"])
			}
		}
	});
}

function setRightPanelHeading(str) {
	var panel = document.getElementById("rightBlockHeading");
	panel.innerHTML = str;
}

function showNearMe() {
	getLocalLocation();

	$.ajax({
		url : 'controller/nearme',
		data : {
			wantedRadius : $('#wantedRadius').val(),
			lat : latitude,
			lon : longitude,
		},
		success : function(responseText) {
			$('#couponListDiv').html(responseText);
		}
	});
}

function showCategory() {
	$.ajax({
		url : 'controller/getcouponsbycategory',
		data : {
			category : $('#couponCategoryName').val(),
		},
		success : function(responseText) {
			$('#couponListDiv').html(responseText);
		}
	});
}

function getKeyValuePairs(sessionID) {
	$.ajax({
		url : 'controller/keyvaluepairs',
		data : {
			sessionID : sessionID,
		},
		success : function(responseText) {
			setRightPanelHeading("Session Key Value Pair");
			$('#cartListDiv').html(responseText);
		}
	});
}

function getSessionStatistic() {
	$.ajax({
		url : 'controller/sessionstatistic',
		data : {},
		success : function(responseText) {
			setRightPanelHeading("Session Statistic");
			$('#cartListDiv').html(responseText);
		}
	});
}

function validateTextBox(element) {
	var valide = true;
	
	if (element.value == "") {
		if (!document.getElementById("alertDiv")) {
			var alertDiv = document.createElement("div");
			alertDiv.id = "alertDiv";
			alertDiv.innerHTML = "<div class='alert alert-danger' role='alert'>"
					+ "<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span>"
					+ "<span class='sr-only'>Error:</span>"
					+ "Enter a valid input" + "</div>";
			
			element.parentNode.insertBefore(alertDiv, element);
			valide = false;
		}
	}
	return valide;
}