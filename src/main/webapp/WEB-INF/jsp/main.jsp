<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>


<%@taglib prefix="spt" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="spf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/intq.css" />" />
<link rel="icon" href="<c:url value="/resources/img/intq_logo.png" />" />
<meta name="robots" content="noindex,nofollow" />
<title><spt:message code="main.title" /></title>
</head>

<body>
	<spt:message code="main.lang" />
	<a href="?Language=en">En</a> |
	<a href="?Language=ru">Ru</a>
	<h1>IntQ</h1>
	<div class="main_form_area">
		<p>You need to choose the way:</p>
		<br>
		<form method="get" action="start.html">
			<button class="task_button" type="submit" value="Submit">Continue as guest </button>
		</form>
		<br> <br>
		<form method="get" action="login.html">
			<button class="task_button" type="submit" value="Submit">Log In</button>
		</form>
		<br>
		<form method="get" action="register.html">
			<button class="task_button" type="submit" value="Submit">Register</button>
		</form>
	</div>

</body>
</html>

