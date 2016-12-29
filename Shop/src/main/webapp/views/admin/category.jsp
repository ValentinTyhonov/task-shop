<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="<c:url value="/css/form.css"/>" rel="stylesheet">

<style>
	.mainmenu-area ul.navbar-nav li:hover a, .mainmenu-area ul.navbar-nav li.admin-active a {background: #5a88ca; color:#FFF;}
</style>

<div class="main-section">
	<div class="registration-section">
		<h2>category</h2>
		<br>
	    
	    <input type="text" id="categoryname" placeholder="name of category">
	    <input type="submit" value="save" id="save">
	    
	    <br><br>
	    <div class="all"></div>
    </div>
</div>



<input type="hidden" name="csrf_name" value="${_csrf.parameterName}" />
<input type="hidden" name="csrf_value" value="${_csrf.token}" />

<script src="<c:url value="/js/category.js"/>"></script>