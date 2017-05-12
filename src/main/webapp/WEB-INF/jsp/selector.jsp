<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="spt" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<script type="text/javascript">
    function rateAsync(indicator) {
    	delta = (indicator == "-1")? "down" : "up";
        $.ajax(
        	{
        	type: "POST",
            url : 'rate.html',
            data: {"id" : ${id}, "delta" : delta},
            success : function(data) {
                	$('#status').html("Thanks for rating! Current rating: " + data);
                	$(".rate_button").hide()
            		}
        	}
        		);
    }
</script>

<body>
	<spt:message code="main.lang" />
	<a href="?Language=en">En</a> |
	<a href="?Language=ru">Ru</a>
	<h1>IntQ</h1>
	<div class="workarea">
		<form:form method="POST" commandName="personForm">
			<table>
				<tr>
					<td>Language:</td>
					<td>
						<form:select path="languages" multiple="true">
							<form:options items="${languages}" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td>Category:</td>
					<td>
						<form:select path="categories" multiple="true">
							<form:options items="${categories}" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td>Difficulty:</td>
					<td>
						<form:select path="difficulties" multiple="true">
							<form:options items="${difficulties}" />
						</form:select>
					</td>
				</tr>
			</table>
			<tr>
				<td colspan="3"><input type="submit" value="Submit"></td>
			</tr>
		</form:form>
	</div>
</body>
</html>

