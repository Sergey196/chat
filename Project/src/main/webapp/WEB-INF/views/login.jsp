<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>22</title>
	<spring:url value="/resources/bootstrap/css/bootstrap-login.css" var="LoginCss"/>
	<spring:url value="/resources/bootstrap/js/login.js" var="LoginJs" />
	<link href="${LoginCss}" rel="stylesheet" />
	<script src="${LoginJs}"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body>

<div id="registration">
	<form action="reg" method="post">
    	<h1>Login form</h1>
    	<fieldset id="inputs">
        	<input id="nick" type="text" placeholder="LOGIN" name="nick" autofocus required>   
        	<input id="password" type="password" placeholder="Password" name="password" required>
    	</fieldset>
    	<fieldset id="actions">
        	<a href="">Forgot your password?</a><a href="registration_user">Registration</a>
        	<button onclick="submit();" id="submit">SIGN</button>
    	</fieldset>
	</form>    	
</div>
</body>
</html>
