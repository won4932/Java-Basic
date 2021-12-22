<%@ page import="com.db.Vision_Write" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

// 안드로이에서 보내준 파라미터 받는다
String returns = "";
String type = request.getParameter("type");
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
%>
<%
	if(type == null) {
		return;
	}else if(type.equalsIgnoreCase("type_regi")) {
		Vision_Write vision_board = Vision_Write.getWrite();
		returns = vision_board.write(id, pwd);
		out.println(returns);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>