<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<style>
	#changeImage {
		display: none;
	}
	#changeImage li {
		text-decoration: none;
		display: inline-block;
	}
</style>
 
<div class="single-product-area">
    <div class="container">
        <div class="row">         
            <div class="col-md-12">
                <div class="product-content-right">
                	<sec:authorize access="hasRole('ROLE_ADMIN')">
	                	<div class="product-breadcroumb">
	                        <a href="#" onclick="changeImage()">CHANGE PHOTO</a>
	                        <a href="editproduct_${product.id}">EDIT</a>
	                        <a href="deleteproduct/${product.id}">DELETE</a>
	                    </div>
	                    <div id="changeImage">
		                    <form action="./changeimage/${product.id}?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
							    <ul>
								    <li><input type="file" name="pic"></li>
								    <li><input type="submit" value="submit"></li>
								    <li><input type="reset" value="cancle" onclick="cancle()"></li>
							    </ul>
							</form>
						</div>
					</sec:authorize>
                    <br>
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="product-main-img">
                                <img src="${product.image}" alt="">
                            </div>
                        </div>
                            
                        <div class="col-sm-8">
                            <div class="product-inner">
                                <h2 class="product-name">${product.name}</h2>
                                <div class="product-inner-price">$${product.price}</div>    
                                   
                                <sec:authorize access="isAuthenticated()">
	                                <form action="./singleaddtocart/${product.id}?${_csrf.parameterName}=${_csrf.token}" method="post" class="cart">
	                                    <button class="add_to_cart_button" type="submit" onclick="buy()">Add to cart</button>
	                                </form>   
                                </sec:authorize>
                                <sec:authorize access="!isAuthenticated()">
                                	<h4 style="color: red">Log in to buy this product</h4>
                                </sec:authorize>
                                    
                                <div class="product-inner-category">
                                    <p>Category: <a href="">${product.category.name}</a></p>
                                </div> 
                                    
                                <div class="tab-content">
                                    <h2>Product Description</h2>  
                                    <p>${product.description}</p>
                                </div>                                    
                            </div>
                        </div>
                    </div>
                </div>                    
            </div>
        </div>
    </div>
</div>

<script>
function changeImage() {
	document.getElementById("changeImage").style.display = 'block'; 
}
function cancle() {
	document.getElementById("changeImage").style.display = 'none'; 
}
function buy() {
	document.querySelector('.add_to_cart_button').innerHTML = "In cart";
	document.querySelector('.add_to_cart_button').style.backgroundColor = "green";
}
</script>