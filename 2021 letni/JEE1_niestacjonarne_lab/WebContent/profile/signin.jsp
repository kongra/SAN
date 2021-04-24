<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Zaloguj się</title>
</head>
<body>

<%
var hello = "Hello World!";
%>
<h1><%= hello %></h1>

<form method="post" action="signin">
  Email:   <input type="text" name="email" value=""/><br>
  Password <input type="password" name="passwd" value=""/><br>
  <input type="submit" value="Zaloguj się">
</form>

</body>
</html>