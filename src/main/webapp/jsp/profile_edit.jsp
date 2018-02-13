<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><c:out value="${ user.firstName }"/> <c:out value="${ user.lastName }"/></title>
    <link rel="shortcut icon" href="../images/logo.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/footer.css">
    <link rel="stylesheet" type="text/css" href="../css/profile_edit.css">
  </head>
  <body>
    <c:import url="/jsp/fragment/header.jsp"></c:import>
    
    <c:choose>
	    <c:when test="${ role != 'GUEST' }">
		    <div class="container"> 
		      <h1>Edit profile info page</h1>
		      <hr class="line">
		      <div class="success"><c:out value="${ editUserSuccess }"/></div>
          <div class="error-msg"><c:out value="${ editUserFail }"/></div>
		      <form id="editPasswordForm" method="POST" action="controller" onsubmit="return (validate1());">
		        <input type="hidden" name="command" value="edit_user" />
		        
		        <label><b>Old password</b></label>
		        <input type="password" name="oldPassword" value="" placeholder="Enter old password"/>
		        <p class="error" id="oldPasswordError">
		          Enter password
		        </p><br/>
		        
		        <label><b>New password</b></label>
		        <input type="password" name="newPassword" value="" placeholder="Enter new password"/>
		        <p class="error" id="newPasswordError">
		        Password should be at least 8 characters long, containing at least 1 lower case letter,
		        1 upper case letter, 1 digit and 1 special character (e.g. !@#$%^&*-)
		        </p><br/>
		        
		        <label><b>Repeat new password</b></label>
		        <input type="password" name="confirmedNewPassword" value="" placeholder="Repeat new password"/>
		        <p class="error" id="confirmedNewPasswordError">Passwords aren't equal</p><br/>
		        
		        <input type="submit" class="editbtn" value="Update password"/>
		      </form>
		      <form id="editInfoForm" method="POST" action="controller" onsubmit="return (validate2());">
		        <input type="hidden" name="command" value="edit_user" />
		        <hr class="line">
		      
		        <label><b>Email</b></label>
		        <input type="text" name="clientEmail" value="${ user.email }" placeholder=""/>
		        <p class="error" id="clientEmailError">Invalid email pattern</p><br/>
		        
		        <label><b>Phone number</b></label>
		        <input type="text" 
		            name="phoneNumber" 
		            maxlength="9"
                onkeypress='return isDigit();'
		            value="${ user.phoneNumber }" 
		            placeholder="Enter phone number"/>
		        <p class="error" id="phoneNumberError">It should consist of 9 digits</p><br/>
		        
		        <input type="submit" class="editbtn" value="Update contact data"/>
		      </form>
		    </div>
		    
		    <script type="text/javascript">
		      <%@include file="../js/profile_edit.js"%>
		    </script>
	    </c:when>
	    <c:otherwise>
	      <div class="msg">
	        <c:out value="You should sign in first to edit your profile"/>
	      </div>
	    </c:otherwise>
    </c:choose>
    <c:import url="/jsp/fragment/footer.jsp"></c:import>
  </body>
</html>