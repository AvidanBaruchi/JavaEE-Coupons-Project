function editCoupon() {
	var inputCheck = false;
	inputCheck = validateTextBox(nameTXT);
	if (inputCheck) {
		inputCheck = validateTextBox(desTXT);
		if (inputCheck) {
			$.ajax({
				url : 'controller/editcoupon',
				data : {
					id : $('#id').val(),
					name : $('#nameTXT').val(),
					des : $('#desTXT').val(),
					daysToAdd : $('#daysToAdd').val()
				},
				success : function(responseText) {
					updateCouponList();
					clearTextFromTextBox(desTXT);
				}
			});
		}
	}
}

function addCupon() {
	var inputCheck = false;
	inputCheck = validateTextBox(nameTXT);
	if (inputCheck) {
		inputCheck = validateTextBox(desTXT);
		if (inputCheck) {
			$.ajax({
				url : 'controller/addcoupon',
				data : {
					name : $('#nameTXT').val(),
					des : $('#desTXT').val(),
					daysToAdd : $('#daysToAdd').val(),
					businessID : $('#addCouponBusinessName').val(),
					category : $('#addCouponCategoryName').val()
				},
				success : function(responseText) {
					updateCouponList();
					clearTextFromTextBox(desTXT);
				}
			});
		}
	}
}

function removeCuponClick(idTodelete) {
	$.ajax({
		url : 'controller/deletecoupon',
		data : {
			id : idTodelete
		},
		success : function(responseText) {
			updateCouponList();

		}
	});
}

function editCuponClick(idToUpdate) {
	$.ajax({
		url : 'controller/editcouponform',
		data : {
			id : idToUpdate
		},
		success : function(responseText) {
			setRightPanelHeading("Edit Coupon");
			$('#cartListDiv').html(responseText);
		}
	});
}

function addCuponClick() {

	$.ajax({
		url : 'controller/addcouponform',
		data : {},
		success : function(responseText) {
			setRightPanelHeading("Add Coupon");
			$('#cartListDiv').html(responseText);
		}
	});
}