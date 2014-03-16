<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Witamy w sklepie internetowym Eshop!!!</h1>

	<c:choose>
		<c:when test="${!empty sessionScope.profile}">
			<a href="./profile/Logout">Wyloguj się</a>
		</c:when>
		<c:otherwise>
			<a href="./profile/login.jsp">Loguj się</a>
			<br />
			<a href="./profile/register.jsp">Załóż nowe konto</a>
		</c:otherwise>
	</c:choose>


</body>
</html>