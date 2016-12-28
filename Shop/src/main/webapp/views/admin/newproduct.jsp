<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<link href="<c:url value="/css/form.css"/>" rel="stylesheet">

<style>
	.mainmenu-area ul.navbar-nav li:hover a, .mainmenu-area ul.navbar-nav li.admin-active a {background: #5a88ca; color:#FFF;}
</style>

<div class="main-section">
	<div class="registration-section">
	<h2>new product</h2>
	<h5>${exception}</h5>
	<br>
	<form:form class="form" modelAttribute="product" action="./saveproduct?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">	
        <form:input path="name" type="text" placeholder="name"/>
        <br>
		<form:input path="price" type="number" placeholder="price"/>
		<br>
		<form:input path="description" type="text" placeholder="description"/>
		<br>
		<select name="categoryId">
			<c:forEach var="category" items="${categories}">
				<option value="${category.id}">${category.name}</option>
			</c:forEach>
		</select>
		<br>
		<input type="file" name="pic">
		<br><br>
		<input type="submit" value="submit">
	</form:form>
	</div>
</div>