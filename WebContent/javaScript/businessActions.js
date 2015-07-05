function addBusinessClick() {
	$.ajax({
		url : 'controller/addbusinessform',
		data : {},
		success : function(responseText) {
			setRightPanelHeading("Add business");
			$('#cartListDiv').html(responseText);
		}
	});
}

function addBusiness() {
	var inputCheck = false;
	inputCheck = validateTextBox(nameTXT);
	if (inputCheck) {
		inputCheck = validateTextBox(locationTXT);
		if (inputCheck) {
			$.ajax({
				url : 'controller/addbusiness',
				data : {
					name : $('#nameTXT').val(),
					location : $('#locationTXT').val(),
					lat : latitude,
					lon : longitude,
				},
				success : function(responseText) {
					updateBusinessList();
					clearTextFromTextBox(locationTXT);
					clearTextFromTextBox(nameTXT);
				}
			});
		}
	}
}

function showBusinessCoupons(businessId) {
	$.ajax({
		url : 'controller/getcouponsbybusiness',
		data : {
			businessId : businessId,
		},
		success : function(responseText) {
			$('#couponListDiv').html(responseText);
		}
	});
}

function removeBusinessClick(businessId) {
	$.ajax({
		url : 'controller/deletebusiness',
		data : {
			businessId : businessId
		},
		success : function(responseText) {
			updateBusinessList();
			updateCouponList();
		}
	});
}

function editBusinessClick(idToUpdate) {
	$.ajax({
		url : 'controller/editbusinessform',
		data : {
			id : idToUpdate
		},
		success : function(responseText) {
			setRightPanelHeading("Edit Business");
			$('#cartListDiv').html(responseText);
		}
	});
}

function editBusiness() {
	var inputCheck = false;
	inputCheck = validateTextBox(nameTXT);
	if (inputCheck) {
		inputCheck = validateTextBox(locationTXT);
		if (inputCheck) {
			$.ajax({
				url : 'controller/editbusiness',
				data : {
					id : $('#id').val(),
					name : $('#nameTXT').val(),
					location : $('#locationTXT').val(),
					lat : latitude,
					lon : longitude,
				},
				success : function(responseText) {
					updateBusinessList();
					clearTextFromTextBox(locationTXT);
					clearTextFromTextBox(nameTXT);
				}
			});
		}
	}
}