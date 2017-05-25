<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>

<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/user.css" />" />
<link rel="icon" href="<c:url value="/resources/img/intq_logo.png" />" />


<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.js"></script>
<script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<script>
$('#registerForm').validator();
	$('#registerForm').validator().on('submit', function (e) {
	console.log("here");
		console.log(e);
		  if (e.isDefaultPrevented()) {
		  } else {
		  }
		})
</script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<title>int q; push user;</title>
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
	<div class="container-fluid">
		<form:form id="registerForm" role="form" data-toggle="validator" class="form-horizontal" modelAttribute="profile" action="register.html"
			method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="inputLogin">Login:</label>
				<div class="col-sm-5">
					<form:input id="inputLogin" type="text" class="form-control" path="login"
						placeholder="From 4 up to 16 of alph. characters, numbers and '_'." data-minlength="4" pattern="^[_A-z0-9]{4,16}$" required="true"></form:input>
				</div>
				<div class="help-block with-errors"></div>
			</div>
			<div class="form-group required">
				<label class="control-label col-sm-2" for="inputPass">Password:</label>
				<div class="col-sm-5">
					<form:input id="inputPass" path="pass" type="password" class="form-control" placeholder="From 4 up to 64 characters" data-minlength="4"
						pattern="^[_A-z0-9]{4,64}$" required="true"></form:input>
				</div>
				<div class="help-block with-errors"></div>
			</div>
			<div class="form-group required">
				<label class="control-label col-sm-2" for="inputPassConfirm">Repeat Password:</label>
				<div class="col-sm-5">
					<input id="inputPassConfirm" type="password" class="form-control" placeholder="Repeat your password" required="true"
						data-match="#inputPass" data-match-error="Passwords don't match">
					<div class="help-block with-errors"></div>
				</div>
			</div>
			<div class="form-group required">
				<label class="control-label col-sm-2" for="inputEmail">Email:</label>
				<div class="col-sm-5">
					<form:input id="inputEmail" path="email" type="email" class="form-control" placeholder="email@domain.com" required="true"
						data-error="Invalid email"></form:input>
				</div>
				<div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="inputName">First Name:</label>
				<div class="col-sm-5">
					<form:input id="inputName" path="name" type="text" class="form-control" placeholder="(Optional) First name" required="true"></form:input>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="inputLastName">Last Name:</label>
				<div class="col-sm-5">
					<form:input id="inputLastName" path="lastName" type="text" class="form-control" placeholder="(Optional) Last name"></form:input>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="inputAge">Age:</label>
				<div class="col-sm-5">
					<form:input id="inputAge" type="number" class="form-control" placeholder="(Optional) age" path="age"></form:input>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Captcha:</label>
				<div class="col-sm-6">
					<%
						ReCaptcha c = ReCaptchaFactory.newReCaptcha(
									"6LdKMBUUAAAAAAtlpDbAkTNyLv8wft0fFJ9HHe85",
									"6LdKMBUUAAAAAN7PS9DV--iIysnZcr7yrV9SkD8k", false);
							out.print(c.createRecaptchaHtml(null, null));
					%>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-8 col-sm-4">
					<button id="signUpBtn" class="btn btn-success btn-block" type="submit">Sign Up</button>
				</div>
			</div>
		</form:form>
	</div>
	<div id="error-container" class="container-fluid">
		<c:if test="${not empty error_msg}">
			<br>
			<div class="alert alert-warning fade in">
				<strong>Problem:</strong> ${error_msg}
			</div>
		</c:if>

	</div>

</body>

</html>

