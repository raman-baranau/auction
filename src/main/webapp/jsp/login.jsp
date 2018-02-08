<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Login</title>
	</head>
	<body>
	
		<form name="loginForm" method="POST" action="../controller">
			<input type="hidden" name="command" value="login" />
			Login:<br/>
			<input type="text" name="login" value=""/>
			<br/>Password:<br/>
			<input type="password" name="password" value=""/>
			<br/>
			<a href="jsp/register.jsp">Not registered yet? Register now!</a>
   			${errorLoginPassMessage}
			<br/>
			 ${wrongAction}
			<br/>
			 ${nullPage}
			<br/>
			<input type="submit" value="Log in"/>
		</form>
		<hr/>
	</body>
</html>