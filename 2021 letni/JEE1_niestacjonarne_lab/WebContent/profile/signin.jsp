<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Zaloguj się</title>
</head>
<body>

<c:if test = "${requestScope.error != null}">
  <span style="color:red"><c:out value="${requestScope.error}"/></span>
</c:if>

<form method="post" action="signin">
  Email:   <input type="text"     name="email"  value=""/><br>
  Password <input type="password" name="passwd" value=""/><br>
  <input type="submit" value="Zaloguj się">
</form>

</body>
</html>