<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>


<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="<c:url value="/resources/img/intq_logo.png" />" />
<title>Add question</title>
</head>

<body>
	<nav class="navbar ">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">intq</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li class="active"><a href="main.html">Home</a></li>
				<li><a href="start.html">Questions</a></li>
				<li><a href="add.html">Suggest</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<h1>int q;</h1>
	<div class="container-fluid">
		<form:form class="form-horizontal" id="selectorForm" modelAttribute="question" action="questionAdd.html" method="post">
			<div class="form-group required">
				<label class="control-label col-sm-2" for="cats">Question:</label>
				<div class="col-sm-10">
					<form:select class="form-control" path="category" id="cats">
						<form:options items="${categories}" />
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="cats">Categories:</label>
				<div class="col-sm-10">
					<form:select class="form-control" path="categories" id="cats">
						<form:options items="${categories}" />
					</form:select>
				</div>
			</div>
			<div class="form-group required">
				<label class="control-label col-sm-2" for="difs">Difficulty:</label>
				<div class="col-sm-10">
					<form:select class="form-control" path="difficulty" id="difs">
						<form:options items="${categories}" />
					</form:select>
				</div>
			</div>
			<div class="form-group required">
				<label class="control-label col-sm-2" for="langs">Language:</label>
				<div class="col-sm-10">
					<form:select class="form-control" path="language" id="langs">
						<form:options items="${categories}" />
					</form:select>
				</div>
			</div>
			<%
				ReCaptcha c = ReCaptchaFactory.newReCaptcha(
							"6LdKMBUUAAAAAAtlpDbAkTNyLv8wft0fFJ9HHe85",
							"6LdKMBUUAAAAAN7PS9DV--iIysnZcr7yrV9SkD8k", false);
					out.print(c.createRecaptchaHtml(null, null));
			%>
			<button class="btn btn-default" type="submit">Add</button>
		</form:form>
	</div>
</body>
</html>

