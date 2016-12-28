<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
	.mainmenu-area ul.navbar-nav li:hover a, .mainmenu-area ul.navbar-nav li.cart-active a {background: #5a88ca; color:#FFF;}
</style>

<div class="single-product-area">
    <div class="container">
        <div class="row">                                            
            <div class="col-md-12">
            	<c:choose>
    				<c:when test="${totalPrice == '0'}">
    					<h4 style="text-align: center;">Cart is empty</h4>
    				</c:when>    
    				<c:otherwise>
		                <form action="./checkout?${_csrf.parameterName}=${_csrf.token}" method="post">
		                    <table class="shop_table cart">
		                        <tbody>
			                    	<c:forEach var="product" items="${products}">
			                            <tr class="cart_item">
			                                <td class="product-remove">
			                                    <a title="Remove this item" class="remove" href="removefromcart/${product.id}">×</a> 
			                                </td>
			                                <td class="product-thumbnail">
			                                    <img width="150" height="150" class="shop_thumbnail" src="${product.image}">
			                                </td>
			                                <td class="product-name">
			                                    <a href="singleproduct_${product.id}">${product.name}</a> 
			                                </td>
			                                <td class="product-price">
			                               		<span class="amount">$${product.price}</span> 
			                                </td>
			                            </tr>
		                            </c:forEach>
		                            <tr>
		                                <td class="actions" colspan="6">
		                                	<input type="text" name="totalPrice" value="${totalPrice}" style="display: none;"/>
		                                	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		                                    <input type="submit" value="checkout">
		                                </td>
		                            </tr>
		                        </tbody>
		                    </table>
		                </form>

		                <div class="cart_totals ">
		                    <h2>Cart Total: <strong><span class="amount">$${totalPrice}</span></strong></h2>               
		                </div>
                	</c:otherwise>
				</c:choose>
            </div>
        </div>
    </div>
</div>