function updateBusinessList() {

	$.ajax({
		url : 'controller/businesses',
		data : {},
		success : function(responseText) {
			$('#businessList').html(responseText);
		}
	});
}

function updateCouponList() {
	$.ajax({
		url : 'controller/coupons',
		data : {},
		success : function(responseText) {
			$('#couponListDiv').html(responseText);
		}
	});
}

function updateShoppingCart() {
	$.ajax({
		url : 'controller/shoppingcart',
		data : {},
		success : function(responseText) {
			$.ajax({
				url : 'controller/rightlabel',
				data : {},
				success : function(responseText) {
					setRightPanelHeading(responseText);
				}
			});
			$('#cartListDiv').html(responseText);
		}
	});
}