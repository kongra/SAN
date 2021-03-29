<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
</head>
<body>
	
	<c:choose>
	  <c:when test = "${sessionScope.email != null}">
        <h2>This is a protected content.</h2>
      </c:when>
      <c:otherwise>
      	<p>You can always <a href="/JEE1_dzienne_lab/signin.jsp">Sign-In</a></p>
      </c:otherwise>
    </c:choose>
         					
	<h3>And this one is for all.</h3>
	
</body>
</html>
