window.onload = loadAllProducts();

document.getElementById('liveSearch').oninput = function () {
	var liveSearch = document.getElementById('liveSearch').value;
        
	if(liveSearch == '') {
		loadAllProducts();
	} else {
		$.ajax({
	        url: 'livesearch?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
	        method: 'POST',
	        contentType: 'application/json; charset=UTF-8',
	        dataType: 'json',
	        data: liveSearch,
	        success: function (res) {
	            var all = '';
	            
	            for (var i = 0; i < res.length; i++) {
	            	all += '<div class="col-md-3 col-sm-6"><div class="single-shop-product">' +
	            		'<div class="product-upper"><img src="' + res[i].image + '"></div>' +
	            		'<h2><a href="singleproduct_' + res[i].id + '">' + res[i].name + '</a></h2>' +
	            		'<div class="product-carousel-price">$' + res[i].price + '</div>' +
	            		'<sec:authorize access="isAuthenticated()">' +
	            		'<div class="product-option-shop">' +
	            		'<a class="add_to_cart_button" onclick="addToCart(' + res[i].id + ')">Add to cart</a>' +
	            		'</div></sec:authorize></div></div>';
	            }
	            document.getElementById('box').innerHTML = all;
	        }
	    })
	}
}

document.getElementById('price-slider').oninput = function () {
	var price = document.getElementById('price-slider').value;
	
	$.ajax({
		
		url: 'rangeprice?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'POST',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        data: price,
        success: function (res) {
        	var all = '';
        	
        	for (var i = 0; i < res.length; i++) {
            	all += '<div class="col-md-3 col-sm-6"><div class="single-shop-product">' +
            		'<div class="product-upper"><img src="' + res[i].image + '"></div>' +
            		'<h2><a href="singleproduct_' + res[i].id + '">' + res[i].name + '</a></h2>' +
            		'<div class="product-carousel-price">$' + res[i].price + '</div>' +
            		'<sec:authorize access="isAuthenticated()"' +
            		'<div class="product-option-shop">' + 
            		'<a class="add_to_cart_button" onclick="addToCart(' + res[i].id + ')">Add to cart</a>' +
            		'</div></sec:authorize></div></div>';
            }
            document.getElementById('max-price').innerHTML = "Max price: $" + price;
            document.getElementById('box').innerHTML = all;
        }
		
	})
	
}

document.getElementById('price-slider').oninput = function () {
	var price = document.getElementById('price-slider').value;
	
	$.ajax({
		
		url: 'rangeprice?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'POST',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        data: price,
        success: function (res) {
        	var all = '';
        	
        	for (var i = 0; i < res.length; i++) {
            	all += '<div class="col-md-3 col-sm-6"><div class="single-shop-product">' +
            		'<div class="product-upper"><img src="' + res[i].image + '"></div>' +
            		'<h2><a href="singleproduct_' + res[i].id + '">' + res[i].name + '</a></h2>' +
            		'<div class="product-carousel-price">$' + res[i].price + '</div>' +
            		'<sec:authorize access="isAuthenticated()">' +
            		'<div class="product-option-shop">' + 
            		'<a class="add_to_cart_button" onclick="addToCart(' + res[i].id + ')">Add to cart</a>' +
            		'</div></sec:authorize></div></div>';
            }
            document.getElementById('max-price').innerHTML = "Max price: $" + price;
            document.getElementById('box').innerHTML = all;
        }
		
	})
	
}

function loadAllProducts() {
	
	$.ajax({

        url: 'loadproducts?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'POST',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (res) {
            var all = '';

            for (var i = 0; i < res.length; i++) {
            	all += '<div class="col-md-3 col-sm-6"><div class="single-shop-product">' +
            		'<div class="product-upper"><img src="' + res[i].image + '"></div>' +
            		'<h2><a href="singleproduct_' + res[i].id + '">' + res[i].name + '</a></h2>' +
            		'<div class="product-carousel-price">$' + res[i].price + '</div>' +
            		'<sec:authorize access="isAuthenticated()">' +
            		'<div class="product-option-shop">' + 
            		'<a class="add_to_cart_button" onclick="addToCart(' + res[i].id + ')">Add to cart</a>' +
            		'</div></sec:authorize></div></div>';
            }
            document.getElementById('box').innerHTML = all;
        }
    })
    
    $.ajax({

        url: 'loadcategories?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'POST',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        success: function (res) {
            var all = '<li><a onclick="loadAllProducts()">All products</a></li>';

            for (var i = 0; i < res.length; i++) {
            	all += '<li><a onclick="category(' + res[i].id + ')">' + res[i].name + '</a></li>';
            }
            document.getElementById('categories').innerHTML = all;
        }
    })
	
}

function addToCart(index) {
	
	$.ajax({

        url: 'addtocart?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'POST',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        data: '' + index,
        success: function (res) {
            var all = '';

            for (var i = 0; i < res.length; i++) {
            	if(res[i].id == index) {
	            	all += '<div class="col-md-3 col-sm-6"><div class="single-shop-product">' +
	            		'<div class="product-upper"><img src="' + res[i].image + '"></div>' +
	            		'<h2><a href="singleproduct_' + res[i].id + '">' + res[i].name + '</a></h2>' +
	            		'<div class="product-carousel-price">$' + res[i].price + '</div>' +
	            		'<sec:authorize access="isAuthenticated()">' +
	            		'<div class="product-option-shop">' + 
	            		'<a class="add_to_cart_button">In cart</a>' +
	            		'</div></sec:authorize></div></div>';
            	} else {
            		all += '<div class="col-md-3 col-sm-6"><div class="single-shop-product">' +
	            		'<div class="product-upper"><img src="' + res[i].image + '"></div>' +
	            		'<h2><a href="singleproduct_' + res[i].id + '">' + res[i].name + '</a></h2>' +
	            		'<div class="product-carousel-price">$' + res[i].price + '</div>' +
	            		'<sec:authorize access="isAuthenticated()">' +
	            		'<div class="product-option-shop">' + 
	            		'<a class="add_to_cart_button" onclick="addToCart(' + res[i].id + ')">Add to cart</a>' +
	            		'</div></sec:authorize></div></div>';
            	}
            }
            document.getElementById('box').innerHTML = all;
        }
    })
	
}

function category(index) {
	
	$.ajax({

        url: 'productsincategory?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'POST',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        data: '' + index,
        success: function (res) {
            var all = '';

            for (var i = 0; i < res.length; i++) {
            	all += '<div class="col-md-3 col-sm-6"><div class="single-shop-product">' +
            		'<div class="product-upper"><img src="' + res[i].image + '"></div>' +
            		'<h2><a href="singleproduct_' + res[i].id + '">' + res[i].name + '</a></h2>' +
            		'<div class="product-carousel-price">$' + res[i].price + '</div>' +
            		'<sec:authorize access="isAuthenticated()">' +
            		'<div class="product-option-shop">' + 
            		'<a class="add_to_cart_button" onclick="addToCart(' + res[i].id + ')">Add to cart</a>' +
            		'</div></sec:authorize></div></div>';
            }
            document.getElementById('box').innerHTML = all;
        }
    })
	
}
