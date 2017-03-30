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

<script src="http://code.jquery.com/jquery-1.10.1.min.js" type="text/javascript"></script>
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
		<table>
			<tr>
				<td><spf:form id="selectorForm" modelAttribute="questionSelector" action="main.html" method="get">
						<spf:select path="category">
							<spf:option value="any">
								<spt:message code="choice.any"></spt:message>
							</spf:option>
							<spf:option value="java">
								<spt:message code="cat.java"></spt:message>
							</spf:option>
							<spf:option value="python">
								<spt:message code="cat.python"></spt:message>
							</spf:option>
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
			<tr>
				<td><spf:label class="question" path="question">${question}</spf:label></td>
			</tr>
			<tr>
				<td><spf:label class="answer" path="answer">${answer}</spf:label></td>
			</tr>
			<tr>
				<td>
					<button id="rate_up" class="rate_button" onclick="rateAsync(1)">
						<spt:message code="rate.up" />
					</button>
				</td>
				<td>
					<button class="main_button" form="selectorForm" type="submit">
						<spt:message code="main.next" />
					</button>
				</td>
				<td>
						<button id="rate_down" class="rate_button" onclick="rateAsync(-1)">
							<spt:message code="rate.down" />
						</button>
				</td>
			</tr>
		</table>
		<label id="status">Rating: ${rating} / 100%</label>
	</div>
</body>
</html>

