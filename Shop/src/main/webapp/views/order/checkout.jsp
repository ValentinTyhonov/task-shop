<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="single-product-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
            	<h5 id="error" align="center"></h5>
                <form action="./placeorder?${_csrf.parameterName}=${_csrf.token}" class="checkout" method="post" name="checkout">
                    <div class="col-md-6 checkout-form">
                        <h3>Shipping Details</h3>                                       
                        <p>
                            <label for="shipping_first_name">First Name</label>
                            <input type="text" value="${user.name}" placeholder="First Name" id="shipping_first_name" name="shipping_first_name">
                        </p>
                        <p>
                            <label for="shipping_last_name">Last Name</label>
                            <input type="text" value="${user.surname}" placeholder="Last Name" id="shipping_last_name" name="shipping_last_name">
                        </p>
                        <p>
                            <label for="shipping_phone">Phone</label>
                            <input type="number" value="" placeholder="(xxx)-xx-xx-xxx" id="shipping_phone" name="shipping_phone">
                        </p>
                        <p>
                            <label for="shipping_notes">Order Notes</label>
                            <input type="text" value="" placeholder="" id="shipping_notes" name="shipping_notes">
                        </p>
					</div>

                    <div class="col-md-6 checkout-form">
                        <h3>Payment</h3>
                        <h4>You must pay: $${totalPrice}</h4>
                        <p>
                            <label for="billing_card_number">Card Number</label>
                            <input type="number" value="" placeholder="xxxx xxxx xxxx xxxx" id="billing_card_number" name="billing_card_number">
                        </p>
                        <p>
                            <label for="billing_card_date">Card Date</label>
                            <input type="text" value="" placeholder="mm/yyyy" id="billing_card_date" name="billing_card_date">
                        </p>
                        <p>
                            <label for="billing_card_cvv2">Card CVV2</label>
                            <input type="password" value="" placeholder="xxx" id="billing_card_cvv2" name="billing_card_cvv2">
                        </p>
                        <p>
                            <label class="" for="billing_first_name">First Name</label>
                            <input type="text" value="" id="billing_first_name" name="billing_first_name">
                        </p>
                        <p>
                            <label class="" for="billing_last_name">Last Name</label>
                            <input type="text" value="" id="billing_last_name" name="billing_last_name">
                        </p>
                    </div>

                    <div class="col-md-12">
                    	<input type="text" name="totalPrice" value="${totalPrice}" style="display: none;"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="submit" value="Place order" id="place-order">
                    </div>
                </form>
			</div>
        </div>
    </div>
</div>

<script>

document.getElementById('place-order').onclick = function () {
	
	var shipping_first_name = document.getElementById('shipping_first_name').value;
	var shipping_last_name = document.getElementById('shipping_last_name').value;
	var shipping_phone = document.getElementById('shipping_phone').value;
	var billing_card_number = document.getElementById('billing_card_number').value;
	var billing_card_date = document.getElementById('billing_card_date').value;
	var billing_card_cvv2 = document.getElementById('billing_card_cvv2').value;
	var billing_first_name = document.getElementById('billing_first_name').value;
	var billing_last_name = document.getElementById('billing_last_name').value;
	
	if(shipping_first_name == '', shipping_last_name == '', shipping_phone == '', 
			billing_card_number == '', billing_card_date == '', billing_card_cvv2 == '',
			billing_first_name == '', billing_last_name == '') {
		document.getElementById('error').innerHTML = 'PLEASE_FILL_ALL_REQUIRED_FIELDS';
		return false;
	} else if(billing_card_number.length != 16) {
		document.getElementById('error').innerHTML = 'INCORRECT_CARD_NUMBER';
		return false;
	} else if(billing_card_cvv2.length != 3) {
		document.getElementById('error').innerHTML = 'INCORRECT_CVV2';
		return false;
	}
	return true;	
}

</script>