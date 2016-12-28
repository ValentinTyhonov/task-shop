<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="<c:url value="/css/adminpage.css"/>" rel="stylesheet">

<style>
	.mainmenu-area ul.navbar-nav li:hover a, .mainmenu-area ul.navbar-nav li.admin-active a {background: #5a88ca; color:#FFF;}
</style>

<nav>
  <ul>
    <li><a href="newproduct">NEW PRODUCT</a></li>
    <li><a href="userslist">USERS</a></li>
    <li><a href="category">CATEGORIES</a></li>
  </ul>
</nav>