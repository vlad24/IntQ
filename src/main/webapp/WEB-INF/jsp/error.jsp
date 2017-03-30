<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title><spring:message code="error.title" /></title>
</head>
<body>
	<a href="?Language=en">En</a> |
	<a href="?Language=ru">Ru</a>
	<div class="header">
		<h1>IntQ</h1>
	</div>
	<div class="errorarea">
		<label><spring:message code="error.text"></spring:message></label>
	</div>
</body>
</html>

