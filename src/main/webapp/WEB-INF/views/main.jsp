<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<%@taglib prefix="spt" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="spf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>

<link rel="stylesheet" href="<c:url value="/resources/css/user.css" />" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="javascript"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="javascript"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="<c:url value="/resources/img/intq_logo.png" />" />
<title>int q;</title>
</head>

<body>
	<div class="jumbotron">
		<h1>> int q;</h1>
		<p>Review your skills...</p>
	</div>
	<div class="container">
		<div class="col-sm-offset-4 col-sm-4 ">
			<c:if test="${empty username}">
				<h3>You need to choose the way:</h3>
				<div class="well">
					<br>
					<form method="get" action="login.html">
						<button class="btn btn-default btn-block" type="submit" value="Submit">Log In</button>
					</form>
					<br>
					<form method="get" action="register.html">
						<button class="btn btn-default btn-block" type="submit" value="Submit">Sign Up</button>
					</form>
					<br> <br>
					<form method="get" action="start.html">
						<button class="btn btn-success btn-block" type="submit" value="Submit">Continue as guest</button>
					</form>
				</div>
			</c:if>
		</div>
		<c:if test="${not empty username}">
			<div class="col-sm-offset-2 col-sm-8 ">
				<div class="well">
					<br>
					<form method="get" action="start.html">
						<button class="btn btn-success btn-block" type="submit" value="Submit">Start reviewing as ${username}</button>
					</form>
					<br> <br>
					<form method="get" action="logout.html">
						<button class="btn btn-default btn-block" type="submit" value="Submit">Switch user</button>
					</form>
					<br> <br>
				</div>
			</div>
		</c:if>
		<form method="get" action="admin.html">
			<button class="btn btn-link btn-block" type="submit" value="Submit">Debug</button>
		</form>
	</div>

</body>
</html>

