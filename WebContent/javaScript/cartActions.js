function addToCart(couponId) {
	$.ajax({
		url : 'controller/addtocart',
		data : {
			id : couponId
		},
		success : function(responseText) {
			updateShoppingCart();
		}
	});
}

function dropFromCart(couponId) {
	$.ajax({
		url : 'controller/removefromcart',
		data : {
			id : couponId
		},
		success : function(responseText) {
			updateShoppingCart();
		}
	});
}