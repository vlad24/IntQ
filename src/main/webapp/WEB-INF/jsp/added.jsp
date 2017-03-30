<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<p><spt:message code="added.success" /></p>
		<a href="main.html">Main</a> |
		<a href="add.html">Add another</a>
</body>
</html>

