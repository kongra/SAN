<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <p>
    To jest treść dostępna dla wszystkich.

    <c:choose>
      <c:when test="${sessionScope.email != null}">
        <p>
          To jest treść dostępna dla zalogowanych użytkowników.<br>
          <a href="profile/signout">Wyloguj się</a>.
      </c:when>
      <c:otherwise>
        <p>
          Możesz zalogować się <a href="profile/signin.jsp">tutaj</a>.
      </c:otherwise>
    </c:choose>
</body>
</html>