<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:choose>
    <c:when test="${!empty sessionScope.profile}">
      <form action="./ChangePass" method="POST">
        Stare hasło:&nbsp;<input name="oldPassword" type="password"/><br/>
        Nowe hasło: &nbsp;<input name="newPassword" type="password"/><br/>
        <input type="submit" value="Zmień hasło" />
      </form>
    </c:when>
    <c:otherwise>
      <h1>Aby zmienić hasło, musisz się najpierw zalogować</h1>
    </c:otherwise>
  </c:choose>

</body>
</html>