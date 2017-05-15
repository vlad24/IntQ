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
<title>int q; setFilter()</title>
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
			<ul class="nav navbar-nav navbar-right">
				<li><a href="register.htm;"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
				<li><a href="login.html"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<h1 class="header">int q;</h1>
	<div class="container-fluid">
		<form:form class="form-horizontal" id="selectorForm" modelAttribute="questionSelector" action="q.html" method="get">
			<div class="form-group">
				<label class="control-label col-sm-2" for="cats">Categories:</label>
				<div class="col-sm-10">
					<form:select class="form-control" path="category" id="cats">
						<form:options items="${categories}" />
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="difs">Difficulty:</label>
				<div class="col-sm-10">
					<form:select class="form-control" path="difficulty" id="difs">
						<form:options items="${categories}" />
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="langs">Language:</label>
				<div class="col-sm-10">
					<form:select class="form-control" path="language" id="langs">
						<form:options items="${categories}" />
					</form:select>
				</div>
			</div>
		</form:form>
		 <div class="col-sm-offset-8 col-sm-4">
			<button id="submit" class="btn btn-success btn-block" type="submit" form="selectorForm">Run</button>
		</div>
	</div>
</body>
</html>

