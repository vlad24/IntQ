<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/user.css" />" />
<!-- 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta username="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="<c:url value="/resources/img/intq_logo.png" />" />
<title>int q; raise Exception();</title>
</head>
<body>
	<div class="header">
		<h1>> int q;</h1>
	</div>
	<c:if test="${not empty error_msg}">
			<div id="error-container" class="container-fluid">
				<div class="alert alert-danger fade in">
					<strong>Oops!</strong> ${error_msg}
				</div>
			</div>
		</c:if>
</body>
</html>

