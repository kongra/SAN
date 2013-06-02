<%@page import="san.profile.Profile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	  if (session.getAttribute(Profile.UID) != null) {
	%>
	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce diam
	nibh, egestas sed viverra vel, tristique in augue. Praesent venenatis
	semper enim a tempus. Sed egestas volutpat turpis in lobortis. In hac
	habitasse platea dictumst. Nulla et nibh ut mi dictum varius. Phasellus
	purus ipsum, tempus sit amet egestas vitae, feugiat sit amet ipsum.
	Phasellus sit amet neque est. Proin nunc odio, aliquet id auctor sit
	amet, sollicitudin eu ipsum. Nullam in urna sed erat aliquam
	vestibulum.
	
	<p>
	<a href="profile/logout">Wyloguj się</a>
	
	<%
	  }
	  else {
	%>

	Próbujesz oglądać chronioną zawartość.
	<a href="loginform.jsp">Zaloguj się.</a>

	<%
	  }
	%>

</body>
</html>