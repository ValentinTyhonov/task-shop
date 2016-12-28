<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<style>
	.mainmenu-area ul.navbar-nav li:hover a, .mainmenu-area ul.navbar-nav li.home-active a {background: #5a88ca; color:#FFF;}

 	img {
		height: 200px;
	}
</style>

<div class="single-product-area">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <div class="single-sidebar">
                    <h2 class="sidebar-title">Search Products</h2>
                    <input type="text" placeholder="Search products..." id="liveSearch">
                </div>
                    
                <div class="single-sidebar">
                    <h2 class="sidebar-title" id="max-price">Max price: $${priceLimit[1]}</h2>
                    <div class="thubmnail-recent">
				        <input type="range" name="price-slider" id="price-slider" value="${priceLimit[1]}" min="${priceLimit[0]}" max="${priceLimit[1]}" step="1">
				    </div>
                </div>
                    
                <div class="single-sidebar">
                    <h2 class="sidebar-title">Categories</h2>
                    <ul id="categories"></ul>
                </div>
            </div>
                
            <div id="box"></div>
                
        </div>
    </div>
</div>
    
<input type="hidden" name="csrf_name" value="${_csrf.parameterName}" />
<input type="hidden" name="csrf_value" value="${_csrf.token}" />

<script src="<c:url value="/js/products.js"/>"></script>