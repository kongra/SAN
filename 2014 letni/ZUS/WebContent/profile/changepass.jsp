<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zmień hasło</title>
</head>
<body>

	<c:choose>
		<c:when test="${!empty sessionScope.PROFILE}">
			<form method="post" action="./ChangePassword">
				Stare hasło:<input type="password" name="oldPassword"><br />
				Nowe hasło: <input type="password" name="newPassword"><br />
				<input type="submit" value="Zmień hasło" />
			</form>
		</c:when>
		<c:otherwise>
			<h1>Aby zmienić hasło musisz się zalogować.</h1>
		</c:otherwise>
	</c:choose>

</body>
</html>