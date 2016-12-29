<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Shop</title>
    
		<!-- Google Fonts -->
		<link href="<c:url value="http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600"/>" rel="stylesheet">
		<link href="<c:url value="http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300"/>" rel="stylesheet">
		<link href="<c:url value="http://fonts.googleapis.com/css?family=Raleway:400,100"/>" rel="stylesheet">
    
		<!-- Bootstrap -->
		<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    
		<!-- Font Awesome -->
		<link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet">
    
		<!-- Custom CSS -->
		<link href="<c:url value="/css/style.css"/>" rel="stylesheet">
		<link href="<c:url value="/css/responsive.css"/>" rel="stylesheet">
		
		<!-- jQuery -->
		<script src="<c:url value="/js/jquery-3.1.1.js"/>"></script>

	</head>
	
	<body>
	
		<div>
			<tiles:insertAttribute name="header" />
		</div>
		
		<div>
			<tiles:insertAttribute name="body" />
		</div>
        
		<div>
			<tiles:insertAttribute name="footer" />
		</div>
		
	</body>
</html>