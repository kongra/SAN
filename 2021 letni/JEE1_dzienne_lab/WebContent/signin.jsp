<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign-In</title>
</head>
<body>

  <c:if test="${requestScope.localErrMessage != null}">
    <span style="color:red">Houston, we have a problem</span>
    <ul style="color:red">
      <li><c:out value="${requestScope.localErrMessage}"/>
    </ul>
  </c:if>

  <form action="/JEE1_dzienne_lab/profile/SignIn" method="POST">
    Email: <input type="text" name="email" /><br> Password: <input
      type="password" name="passwd"><br> <input
      type="submit" value="Sign-In">
  </form>

</body>
</html>