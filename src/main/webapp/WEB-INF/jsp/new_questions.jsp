<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/user.css" />" />
<link rel="icon" href="<c:url value="/resources/img/intq_logo.png" />" />

<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.js"></script>
<script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>


<meta name="viewport" content="width=device-width, initial-scale=1">
<title>int q; puts u;</title>
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
	<c:if test="${not empty questions}">
		<div class="container-fluid">
			<table class="table table-hover">
				<th>ID</th>
				<th>QUESTION</th>
				<th>ACTIONS</th>
				<c:forEach items="${questions}" var="q">
					<tr>
						<td>${q.id}</td>
						<td>${q.alias}</td>
						<td>
						<div class="btn-group">
								<button id="rate_up" class="rate_button btn btn-success">Accept</button>
								<button id="rate_up" class="rate_button btn btn-primary">Look</button>
								<button id="rate_up" class="rate_button btn btn-danger">Reject</button>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div id="error-container" class="container-fluid">
				<c:if test="${not empty error_msg}">
					<br>
					<div class="alert alert-warning fade in">
						<strong>Problem:</strong> ${error_msg}
					</div>
				</c:if>

			</div>
		</div>
	</c:if>
</body>

</html>

