<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="spt" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<!-- 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 -->
<link rel="stylesheet" href="<c:url value="/resources/css/user.css" />" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta username="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="<c:url value="/resources/img/intq_logo.png" />" />
<title>int q; puts q</title>
</head>

<script src="http://code.jquery.com/jquery-1.10.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$( document ).ready(function() {
	getNextAsync();
});


    function rateAsync(indicator) {
    	delta = (indicator == "-1")? "DOWN" : "UP";
        $.ajax(
        	{
        		type: "POST",
            	url : 'rate.html',
            	data: {"id" : ${id}, "delta" : delta},
            	success: function(data) 
            			 {
                			$('#status').html("Rating: " + data + " %");
                			$(".rate_button").hide();
            			 }
        	}
       	);
    }
    
    function getNextAsync() {
        $.ajax(
         	   {
                 type: "GET",
                 url: "nextQ",
                 data: $("#selectorForm").serialize(),
                 contentType: "application/json",
                 success: function(data)
                 {
                	 if (data){
	             	   $("#qstid").html(data.id);
	             	   $("#qst").html(data.question);
	             	   $("#ans").html(data.answer);
	             	   $("#shft").val(data.selector.shift);
                	 }else{
                		 $("#err-box").html("You have reached the end of your question sequence. You are ready! \<a href=\"start.html\" class=\"alert-link\">Start a new tour!</a>."	)
                	 }
                 }
                }
         	);
    }
</script>

<body>
	<nav class="navbar">
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
				<c:if test="${not empty username}">
					<li><a href="suggestion.html">Suggest</a></li>
				</c:if>
			</ul>
			<c:if test="${empty username}">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="register.html"><span class="glyphicon glyphicon-user"></span> Register</a></li>
					<li><a href="login.html"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</ul>
			</c:if>
			<c:if test="${not empty username}">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="profile.html"><span class="glyphicon glyphicon-user"></span> ${username} </a></li>
					<li><a href="logout.html"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
				</ul>
			</c:if>
		</div>
	</div>
	</nav>

	<h1 class="header">> int q;</h1>

		<div class="container-fluid">
			<div class="panel panel-default">
				<div class="panel-body">
					<form:label id="qst" class="question" path="question">
					<!-- ${question} -->
					</form:label>
				</div>
			</div>
			<input id="qstid" type="hidden">
			<div class="container">
				<div class="row">
					<div class="col-sm-2">
						<button id="rate_down" class="rate_button btn btn-warn" onclick="rateAsync(-1)">- -</button>
					</div>
					<div class="col-sm-8">
						<button class="btn btn-default btn-block" onclick="getNextAsync()">Next</button>
					</div>
					<div class="col-sm-2">
						<button id="rate_up" class="rate_button btn btn-success" onclick="rateAsync(1)">++</button>
					</div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-sm-2">
						<button class="btn btn-link" data-toggle="collapse" data-target="#ans">Show answer</button>
					</div>
					<div class="col-sm-offset-8 col-sm-2">
						<p id="status">Rating: ${rating} %</p>
					</div>
				</div>
			</div>
			<form:label id="ans" class="collapse" path="answer">
			<!-- {answer} -->$
			</form:label>
		</div>

		<form:form id="selectorForm" modelAttribute="questionSelector" action="q.html" method="get" hidden="true">
			<form:hidden path="ids" multiple="true" />
			<form:hidden path="difficulty" />
			<form:hidden path="language" />
			<form:hidden path="catsUnioned" />
			<form:hidden path="shift" id="shft"/>
		</form:form>
		<div id="err-box" class="alert alert-success"></div>

</body>
</html>

