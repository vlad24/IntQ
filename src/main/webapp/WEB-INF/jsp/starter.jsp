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

<script src="http://code.jquery.com/jquery-1.10.1.min.js"
	type="text/javascript"></script>

<body>
	<spt:message code="main.lang" />
	<a href="?Language=en">En</a> |
	<a href="?Language=ru">Ru</a>
	<h1>IntQ</h1>
	<div class="workarea">
		<table>
			<tr>
				<td><spf:form id="selectorForm" modelAttribute="questionSelector" action="questionGet.html" method="get">
						<spf:select path="category">
							<spf:options items="${categories}" />
						</spf:select>
						<spf:select path="difficulty">
							<spf:option value="any">
								<spt:message code="choice.any"></spt:message>
							</spf:option>
							<spf:option value="easy">
								<spt:message code="dif.easy"></spt:message>
							</spf:option>
							<spf:option value="middle">
								<spt:message code="dif.middle"></spt:message>
							</spf:option>
							<spf:option value="hard">
								<spt:message code="dif.hard"></spt:message>
							</spf:option>
						</spf:select>
						<spf:select path="language">
							<spf:option value="en">
								<spt:message code="lang.en"></spt:message>
							</spf:option>
							<spf:option value="ru">
								<spt:message code="lang.ru"></spt:message>
							</spf:option>
						</spf:select>
					</spf:form></td>
			</tr>
		</table>
	</div>
</body>
</html>

