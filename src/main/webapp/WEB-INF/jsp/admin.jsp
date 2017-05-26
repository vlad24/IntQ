<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>

<link rel="stylesheet" href="<c:url value="/resources/css/user.css" />" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="<c:url value="/resources/img/intq_logo.png" />" />
<title>int q; sudo su</title>
</head>

<body>
	<div class="jumbotron">
		<h1>> int q;</h1>
		<p>Review your skills...</p>
	</div>
	<c:if test="${not empty error_msg}">
		<div id="error-container" class="container-fluid">
			<div class="alert alert-danger fade in">
				<strong>Oops!</strong> ${error_msg}
			</div>
		</div>
	</c:if>
	<c:if test="${not empty success_msg}">
		<div id="success-container" class="container-fluid">
			<div class="alert alert-success fade in">
				<strong>Done!</strong> ${success_msg}
			</div>
		</div>
	</c:if>
	<div class="container">
		<div class="col-sm-offset-4 col-sm-4 ">
			<h3>What's next, sir?</h3>
			<div class="well">
				<br>
				<form method="get" action="admin_moderate.html">
					<button class="btn btn-danger btn-block" type="submit" value="Submit">Moderate new questions</button>
				</form>
				<br> <br>
				<form method="get" action="admin_init.html">
					<button class="btn btn-primary btn-block" type="submit" value="Submit">Fill initial data</button>
				</form>
				<br> <br>
				<form method="get" action="main.html">
					<button class="btn btn-default btn-block" type="submit" value="Submit">Back to main</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>

