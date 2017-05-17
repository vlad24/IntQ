<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/user.css" />" />
<!-- 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="<c:url value="/resources/img/intq_logo.png" />" />
<title>int q; assert exists(u);</title>
</head>

<body>
	<nav class="navbar ">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li class="active"><a href="main.html">Home</a></li>
				<li><a href="start.html">Questions</a></li>
				<li><a href="add.html">Suggest</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<h1 class="header">>int q;</h1>
	<div id="error-container" class="container-fluid">
		<c:if test="${not empty error_msg}">
			<div class="alert alert-warning fade in">
				<strong>Oops!</strong> ${error_msg}
			</div>
		</c:if>

	</div>
	<div class="container-fluid">
		<form:form class="form-horizontal" id="loginForm" modelAttribute="userCreds" action="login.html" method="post">
			<div class="form-group">
				<label class="control-label col-sm-offset-2  col-sm-2" for="usr">Username:</label>
				<div class="col-sm-4">
					<form:input type="text" class="form-control" path="login" id="usr"></form:input>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-offset-2  col-sm-2" for="pass">Password:</label>
				<div class="col-sm-4">
					<form:input type="password" class="form-control" path="passHash" id="pass"></form:input>
				</div>
			</div>
		</form:form>
		<div class="col-sm-offset-6 col-sm-2">
			<button id="submit" class="btn btn-success btn-block" type="submit" form="loginForm">Log In</button>
		</div>
		<br> <br>
	</div>
</body>
</html>

