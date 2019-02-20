
<%
  Boolean loggedIn = (Boolean) session.getAttribute("LOGGED-IN");
  if (loggedIn == null || !loggedIn.booleanValue()) {
    response.sendRedirect("profile/loginform.jsp");
    return;
  }
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ten widok jest chroniony</title>
</head>
<body>

	Jeżeli to oglądasz, to znaczy, że jesteś zalogowanym użytkownikiem o
	nazwie:<%=session.getAttribute("LOGIN")%>. Teraz możesz się <a href="profile/Logout">wylogować</a>.

</body>
</html>