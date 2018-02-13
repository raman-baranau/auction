<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Login</title>
    <link rel="shortcut icon" href="../images/logo.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/footer.css">
    <link rel="stylesheet" type="text/css" href="../css/sign_in_form.css">
  </head>
  <body>
    <c:import url="/jsp/fragment/header.jsp"></c:import>
    <form name="loginForm" method="POST" action="controller">
      <div class="container">
        <input type="hidden" name="command" value="login" />
        <h1>Sign In</h1>
        <p>Please fill in this form to sign in your account.</p>
        <hr class="line">
        
        <h5 class="error">${errorLoginPassMessage}</h5>
		    <label><b>Login</b></label>
	      <input type="text" name="login" value="" placeholder="Enter your login here"/>
	
	      <label><b>Password</b></label>
	      <input type="password" name="password" value="" placeholder="Enter password"/>
	    
		    <a href="register.jsp">Not registered yet? Register now!</a>
  			
		
		    <input type="submit" class="signinbtn" value="Sign in"/>
      </div>
    </form>
    <c:import url="/jsp/fragment/footer.jsp"></c:import>
  </body>
</html>