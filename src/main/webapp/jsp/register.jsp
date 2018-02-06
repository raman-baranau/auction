<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Register</title>
	</head>
	<body>
		<form name="loginForm" method="POST" action="controller">
			<input type="hidden" name="command" value="register" />
			Login:<br/>
			<input type="text" name="login" value=""/> <br/>
			Password:<br/>
			<input type="password" name="password" value=""/> <br/>
			Repeat password:<br/>
			<input type="password" name="repeat_password" value=""/> <br/>
			First name:<br/>
			<input type="text" name="first_name" value=""/> <br/>
			Last name:<br/>
			<input type="text" name="last_name" value=""/> <br/>
			Email: <br/>
			<input type="email" name="client_email" value=""/> <br/>
			Phone number: <br/>
			<input type="text" name="phone_number" value=""/> <br/>
   			${errorLoginPassMessage}
			<br/>
			 ${wrongAction}
			<br/>
			 ${nullPage}
			<br/>
			<input type="submit" value="Register"/>
		</form>
		<hr/>
	</body>
</html>