<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="<c:url value="/css/form.css"/>" rel="stylesheet">

<style>
	.mainmenu-area ul.navbar-nav li:hover a, .mainmenu-area ul.navbar-nav li.admin-active a {background: #5a88ca; color:#FFF;}
</style>

<div class="main-section">
	<div class="registration-section">
	<form class="list">
		<h2>Administrators</h2>
		<ul>
			<c:forEach var="admin" items="${admins}">
				<li>		
					<p>${admin.name} ${admin.surname} (${admin.email})</p>
						<a href="deleteuser/${admin.id}">delete</a>
						-
						<a href="changerole/${admin.id}">change role</a>
						-
						<a href="changeenabled/${admin.id}">
							<c:choose>
							    <c:when test="${admin.enabled}">
							        disable
							    </c:when>    
							    <c:otherwise>
							        enable
							    </c:otherwise>
							</c:choose>
						</a>
				</li> 
			</c:forEach>
		</ul>
	</form>
	
	<br><br>
	
	<form>
		<h2>Users</h2>
		<ul>
			<c:forEach var="user" items="${users}">
				<li>		
					<p>${user.name} ${user.surname} (${user.email})</p>
						<a href="deleteuser/${user.id}">delete</a>
						-
						<a href="changerole/${user.id}">change role</a>
						-
						<a href="changeenabled/${user.id}">
							<c:choose>
							    <c:when test="${user.enabled}">
							        disable
							    </c:when>    
							    <c:otherwise>
							        enable
							    </c:otherwise>
							</c:choose>
						</a>
				</li> 
			</c:forEach>
		</ul>
	</form>
	</div>
</div>