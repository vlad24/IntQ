<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
	

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
	<div class="inputarea">
		<table>
			<tr>
				<td>
					<form id="questionForm" action="add.html" method="post">
						<label>
							<spt:message code="qform.question"></spt:message>
						</label>
						<textarea name="question"></textarea>
						<br/>
						<label>
							<spt:message code="qform.answer"></spt:message>
						</label>
						<textarea name="answer"> </textarea>
						<br/>
						<label>
							<spt:message code="qform.dif"></spt:message>
						</label>
						<select name="difficulty">
							<option value="easy">
								<spt:message code="dif.easy"></spt:message>
							</option>
							<option value="middle">
								<spt:message code="dif.middle"></spt:message>
							<option>
							<option value="hard">
								<spt:message code="dif.hard"></spt:message>
							<option>
						</select>
						<br/>
						<select name="category">
							<option value="java">
								<spt:message code="cat.java"></spt:message>
							</option>
							<option value="python">
								<spt:message code="cat.python"></spt:message>
							</option>
						</select>
						<%
					        ReCaptcha c = ReCaptchaFactory.newReCaptcha(
					        		"6LdKMBUUAAAAAAtlpDbAkTNyLv8wft0fFJ9HHe85",
					        		"6LdKMBUUAAAAAN7PS9DV--iIysnZcr7yrV9SkD8k",
					        		false
					        		);
					        out.print(c.createRecaptchaHtml(null, null));
				 		%>
						<button class="add_button" type="submit">
							<spt:message code="qform.add" />
						</button>
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>

