<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<spring:url value="/resources/bootstrap/css/bootstrap-chat.css" var="ChatCss"/>
	<spring:url value="/resources/bootstrap/js/chat.js" var="ChatJs" />
	<spring:url value="/resources/bootstrap/js/loud_chat.js" var="LoudChatJs" />
	<link href="${ChatCss}" rel="stylesheet" />
	<script src="${ChatJs}" charset="UTF-8"></script>
	<script src="${LoudChatJs}" charset="UTF-8"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
</head>
  <body onload="load();">
     <div id="header">
     	<div id="login">
     		<label id="nick">Hi, ${user.getNick()}</label>
     	</div>
     	<div id="loginButton">
     		<form action="logout" method="POST">
				<input type="hidden" name="command" value="logout" />
				<input type="submit" value="logout"/>
			</form>
     	</div>
     </div>
     <div id="menu">
     	<textarea id="all_messages" name="all_messages" rows="20" cols="65" readonly>
			
     	</textarea>
     	<div id="login_users">
			<select onchange="chang_user();" size="10" id = "login_users_list">
    			<option disabled>Users</option>
				<c:forEach var="item" items="${dao.getUserDao().findAllLogged()}">
					<option value= "${item.getNick()}" >${item.getNick()}</option>
				</c:forEach>
   			</select>
   			<div id="user_panel">
   				<textarea id="user_info" rows="3" cols="27" name="user_info" readonly></textarea>
   				<img id="user_img" src="<c:url value="resources/bootstrap/images/fon.png" />" width="80" height="80">
   			</div>
     	</div>
     </div>
     <div id="footer">
     	<textarea id="message" rows="2" cols="65" name="message"></textarea>	
     	<div id="messegeButton">
			<button onclick="message();">Send</button>
		</div>

	</div>
	</body>
</html>