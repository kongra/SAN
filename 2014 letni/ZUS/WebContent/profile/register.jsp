<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Załóż nowe konto</title>
</head>
<body>

<form method="POST" action = "./Register">
Nazwa:&nbsp;<input name="login" type="text" value=""/><br/>
Hasło:&nbsp;<input name="password" type="password" value=""/><br/>
Imię:&nbsp;<input name="firstName" type="text" value=""/><br/>
Nazwisko:&nbsp;<input name="lastName" type="text" value=""/><br/>
<input type="submit" value="Załóż konto"/>
</form>

</body>
</html>