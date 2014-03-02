<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formularz rejestracji</title>
</head>
<body>

<form method="POST" action="./Register">
Login&nbsp;<input type="text" name="login"/><br/>
Hasło&nbsp;<input type="password" name="password"/><br/>
Imię&nbsp;<input type="text" name="firstName"/><br/>
Nazwisko&nbsp;<input type="text" name="lastName"/><br/>
<input type="submit" value="Zarejestruj się"/>
</form>

</body>
</html>