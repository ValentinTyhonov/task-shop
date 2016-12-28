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
	<form:form class="form" modelAttribute="product" action="./savechanges_${product.id}" method="post">	
        <form:input path="name" type="text" placeholder="name" value="${product.name}"/>
        <br>
		<form:input path="price" type="number" placeholder="price" value="${product.price}"/>
		<br>
		<form:input path="description" type="text" placeholder="description" value="${product.description}"/>
		<br>
		<select name="categoryId">
			<c:forEach var="category" items="${categories}">
				<c:choose>
					<c:when test="${product.category.id == category.id}">
						<option selected value="${category.id}">${category.name}</option>
					</c:when>
					<c:otherwise>
						<option value="${category.id}">${category.name}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<br><br>
		<input type="submit" value="submit">
	</form:form>
	</div>
</div>