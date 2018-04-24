<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<spring:url value="/resources/bootstrap/css/bootstrap-registration_user.css" var="RegCss"/>
	<link href="${RegCss}" rel="stylesheet" />
</head>
<body>
	<div id="menu">
		<form action="registration_login" method="post" enctype="multipart/form-data">
			Nick:<br><input type="text" id="nick" name="nick"/><br>
			Name:<br><input type="text" id="user_name" name="user_name"/><br>
			Password:<br><input type="text" id="password" name="password"/><br>
			Photo:<br><input type="file" id="photo" name="photo" accept="image/*,image/jpeg"><br>
			Telephone:<br><input type="text" id="telephone" name="telephone"/><br><br>
			<input type="submit" value="Login"/>
		</form> 
     </div>	
</body>
</html>