<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Załóż konto</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div>
		<form action="./Register" method="post">
			Login:&nbsp;<input type="text" value="" name="login"><br />
			Hasło:&nbsp;<input type="password" value="" name="password"><br />
			Imię:&nbsp;<input type="text" value="" name="firstName"><br />
			Nazwisko:&nbsp;<input type="text" value="" name="lastName"><br />
			<input type="submit" value="Załóż konto" />
		</form>
	</div>

</body>
</html>