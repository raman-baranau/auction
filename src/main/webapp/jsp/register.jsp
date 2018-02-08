<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Sign up</title>
    <link rel="stylesheet" type="text/css" href="css/sign_up_form.css">
  </head>
  <body>
    <form name="signUpForm" method="POST" action="controller" onsubmit="return(validate());">
      <div class="container">	
        <input type="hidden" name="command" value="register" />
        <h1>Sign Up</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>
	    
	    <label><b>Login<span class="required">*</span></b></label>
	    <input type="text" name="login" value="" placeholder="Type unique user name"/>
	
	    <label><b>Password<span class="required">*</span></b></label>
	    <input type="password" name="password" value="" placeholder="Enter password"/>
	    
	    <label><b>Repeat password<span class="required">*</span></b></label>
	    <input type="password" name="confirmedPassword" value="" placeholder="Repeat password"/>
	    
	    <label><b>First name<span class="required">*</span></b></label>
	    <input type="text" name="firstName" value=""/> <br/>
	    
	    <label><b>Last name<span class="required">*</span></b></label>
	    <input type="text" name="lastName" value=""/> <br/>
	    
	    <label><b>Email<span class="required">*</span></b></label>
	    <input type="text" name="clientEmail" value=""/> <br/>
	    
	    <label><b>Phone number</b></label>
	    <input type="text" name="phoneNumber" value=""/> <br/>
        
	    <input type="submit" class="signupbtn" value="Sign up"/>
      </div>
    </form>
    <hr/>
    <script type="text/javascript">
      <%@include file="../js/sign_up_form_validation.js"%>
    </script>
  </body>
</html>