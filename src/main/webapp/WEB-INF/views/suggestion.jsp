<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<link rel="icon" href="<c:url value="/resources/img/intq_logo.png" />" />
<link rel="stylesheet" href="<c:url value="/resources/css/user.css" />" />
<meta username="viewport" content="width=device-width, initial-scale=1">
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>
<script type="text/javascript">
var RecaptchaOptions = {
	    theme : 'clean'
	 };
</script>
<title>int q; insert into Qs values (...);</title>
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
			</ul>
			<c:if test="${empty username}">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="register.html"><span class="glyphicon glyphicon-user"></span> Register</a></li>
					<li><a href="login.html"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</ul>
			</c:if>
			<c:if test="${not empty username}">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="profile.html"><span class="glyphicon glyphicon-user"></span> ${username} </a></li>
					<li><a href="logout.html"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
				</ul>
			</c:if>
		</div>
	</div>
	</nav>
	<h1 class="header">> int q;</h1>
	<div id="error-container" class="container-fluid">
		<c:if test="${not empty error_msg}">
			<div class="alert alert-warning fade in">
				<strong>Oops!</strong> ${error_msg}
			</div>
		</c:if>
		<c:if test="${not empty success_msg}">
			<div class="alert alert-success fade in">
				<strong>Done!</strong> ${success_msg}
			</div>
		</c:if>
	</div>
	<div class="container-fluid">
		<form:form id="suggestForm" role="form" data-toggle="validator" class="form-horizontal" modelAttribute="questionSuggestion"
			action="suggest"
			enctype="multipart/form-data" 
			method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="qst">Question:</label>
				<div class="col-sm-10">
					<form:textarea path="question" id="qst" class="form-control" rows="4" placeholder="At least 8 characters." data-minlength="8"
						required="true"></form:textarea>
				</div>
				<div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="asw">Answer:</label>
				<div class="col-sm-10">
					<form:textarea path="answer" id="asw" class="form-control" rows="4" placeholder="(Optional). Please, be constructive."></form:textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="cats">Categories:</label>
				<div class="col-sm-10">
					<form:select class="form-control" path="categories" id="cats" required="true">
						<form:options items="${categories}" itemValue="id" itemLabel="alias" />
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="difs">Difficulty:</label>
				<div class="col-sm-10">
					<form:select class="form-control" path="difficulty" id="difs" required="true">
						<form:options items="${difficulties}" itemValue="id" itemLabel="alias" />
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="langs">Language:</label>
				<div class="col-sm-10">
					<form:select class="form-control" path="language" id="langs" require="true">
						<form:options items="${languages}" itemValue="id" itemLabel="alias" />
					</form:select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="attachment">Attachment:</label>
				<div class="col-sm-10">
				 	<form:input path="fileData" type="file"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Captcha:</label>
				<div class="col-sm-offset-2 col-sm-6">
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
					<button class="btn btn-success btn-block" type="submit">Add</button>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>

