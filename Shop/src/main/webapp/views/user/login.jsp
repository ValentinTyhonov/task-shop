<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<link href="<c:url value="/css/form.css"/>" rel="stylesheet">

<style>
	.mainmenu-area ul.navbar-nav li:hover a, .mainmenu-area ul.navbar-nav li.login-active a {background: #5a88ca; color:#FFF;}
</style>

<div class="main-section">
	<div class="registration-section">
		<h2>register or sign in</h2>
		<h5>${exception}</h5>
		<div class="register-form">
		<div class="login-form">
		<form:form class="form" action="loginprocesing" method="post">
			<input name="username" type="email" placeholder="Email">
			<input name="password" type="password" placeholder="Password">
			<input type="submit" value="login">
		</form:form>
		</div>
		<div class="registration-form">
		<form:form class="form" modelAttribute="user" action="saveuser" method="post">
			<form:input path="name" type="text" placeholder="Name"/>
			<form:input path="surname" type="text" placeholder="Surname"/>
			<form:input path="email" type="email" placeholder="Email"/>
			<form:input path="password" type="password" placeholder="Password"/>
			<input name="confirmPass" type="password" placeholder="Confirm password">
			<input type="submit" value="submit">
		</form:form>
		</div>
		<div class="clear"></div>
		</div>
		<p>forgot your password? click<a href="#"> here </a></p>
	</div>
</div>