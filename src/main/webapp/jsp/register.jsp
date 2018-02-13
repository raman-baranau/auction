<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Sign up</title>
    <link rel="shortcut icon" href="../images/logo.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/footer.css">
    <link rel="stylesheet" type="text/css" href="../css/sign_up_form.css">
  </head>
  <body>
    <c:import url="/jsp/fragment/header.jsp"></c:import>
    <form id="signUpForm" method="POST" action="controller" onsubmit="return validate();">
      <div class="container">	
        <input type="hidden" name="command" value="register" />
        <h1>Sign Up</h1>
        <p>Please fill in this form to create an account.</p>
        <hr class="line">
	    
		    <label><b>Login<span class="required">*</span></b></label>
		    <input type="text" name="login" value="" placeholder="Type unique user name"/>
			  <p class="error" id="loginError"></p><br/>
			
		    <label><b>Password<span class="required">*</span></b></label>
		    <input type="password" name="password" value="" placeholder="Enter password"/>
		    <p class="error" id="passwordError"></p><br/>
		    
		    <label><b>Repeat password<span class="required">*</span></b></label>
		    <input type="password" name="confirmedPassword" value="" placeholder="Repeat password"/>
		    <p class="error" id="confirmedPasswordError"></p><br/>
		    
		    <label><b>First name<span class="required">*</span></b></label>
		    <input type="text" name="firstName" value="" placeholder="Enter your first name"/>
		    <p class="error" id="firstNameError"></p><br/>
		    
		    <label><b>Last name<span class="required">*</span></b></label>
		    <input type="text" name="lastName" value="" placeholder="Enter your last name"/>
		    <p class="error" id="lastNameError"></p><br/>
		    
		    <label><b>Email<span class="required">*</span></b></label>
		    <input type="text" name="clientEmail" value="" placeholder="Enter your email"/>
		    <p class="error" id="clientEmailError"></p><br/>
		    
		    <label><b>Phone number</b></label>
		    <input type="text" 
		      name="phoneNumber" 
		      value="" 
		      maxlength="9"
		      onkeypress='return isDigit();'
		      placeholder="Enter your phone number in format xxyyyyyyy, where xx is mobile operator code"/>
		    <p class="error" id="phoneNumberError"></p><br/>
	        
	      <p>Fields marked as <span class="required">*</span> are required to fill in.</p>
	      <p>By creating an account you agree to our <a href="#">Terms &#38; Privacy.</a></p>
	      <input type="button" class="cancelbtn" 
	          onclick="window.location.href='main.jsp';" value="Cancel"/>
		    <input type="submit" class="signupbtn" value="Submit"/>
      </div>
    </form>
    <hr/>
    <c:import url="/jsp/fragment/footer.jsp"></c:import>
    <script type="text/javascript">
      <%@include file="../js/sign_up_form_validation.js"%>
    </script>
  </body>
</html>