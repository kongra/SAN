<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Moja pierwsza strona</title>
</head>
<body>

<c:choose>
  <c:when test="${!empty sessionScope.login}">
    <p>Witamy  <c:out value="${sessionScope.login}"/> na naszej stronie<p>
  </c:when>
  <c:otherwise>
    <p>Proszę, zaloguj się <a href="login.jsp">tutaj.</a></p>
  </c:otherwise>
</c:choose>

</body>
</html>