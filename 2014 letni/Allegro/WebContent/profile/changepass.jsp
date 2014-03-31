<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zmień hasło</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div>
		<c:choose>
			<c:when test="${!empty sessionScope.profile}">
				<form action="./Changepass" method="post">
					Stare hasło:&nbsp;<input type="password" value="" name="oldPassword"><br />
					Nowe hasło:&nbsp;<input type="password" value="" name="newPassword"><br />
					<input type="submit" value="Zmień hasło" />
				</form>
			</c:when>
			<c:otherwise>
				<h1>
					Aby zmienić hasło musisz się <a href="./login.jsp">zalogować</a>
				</h1>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>