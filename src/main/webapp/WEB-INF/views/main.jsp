<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<%@taglib prefix="spt" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="spf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>

<link rel="stylesheet" href="<c:url value="/resources/css/user.css" />" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="javascript"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="javascript"></script>
<meta username="viewport" content="width=device-width, initial-scale=1">
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
			<sec:authorize access="not isAuthenticated()">
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
			</sec:authorize>
		</div>
		<sec:authorize access="isAuthenticated()">
			<div class="col-sm-offset-2 col-sm-8 ">
				<div class="well">
					<br>
					<form method="get" action="start.html">
						<button class="btn btn-success btn-block" type="submit" value="Submit">Start reviewing as <sec:authentication property="principal.username"/> </button>
					</form>
					<br> <br>
					<form method="get" action="logout.html">
						<button class="btn btn-default btn-block" type="submit" value="Submit">Switch user</button>
					</form>
					<br> <br>
				</div>
			</div>
		</sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <form method="get" action="admin.html">
                <button class="btn btn-link btn-block" type="submit" value="Submit">Administration</button>
            </form>
        </sec:authorize>
	</div>

</body>
</html>

