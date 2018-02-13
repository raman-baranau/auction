<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html 
PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
       "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><c:out value="${ client.firstName }"/> <c:out value="${ client.lastName }"/></title>
    <link rel="shortcut icon" href="../images/logo.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/profile.css">
  </head>
  <body>
    <c:import url="/jsp/fragment/header.jsp"></c:import>
    <div class="container">
	    <h3>
	      <c:out value="${ client.firstName }"/>
	      <c:out value="${ client.lastName }"/>
	    </h3>
	    <hr class="line">
	    
	    <div class="profile">
	      <div class="info-block">
          <div class="info-name">Pseudonym</div>
          <div class="info-value"><c:out value="${ client.login }"/></div>
        </div>
	      <div class="info-block">
	        <div class="info-name">Email</div>
	        <div class="info-value"><c:out value="${ client.email }"/></div>
	      </div>
	      <br>
	      <div class="info-block">
	        <div class="info-name">Phone number</div>
	        <div class="info-value">
		        <c:choose>
		          <c:when test="${ not empty client.phoneNumber }">
		            <c:out value="${ client.phoneNumber }"/>
		          </c:when>
		          <c:otherwise>
                Not specified
              </c:otherwise>
		        </c:choose>
	        </div>
	      </div>
	      <br>
	      <div class="info-block">
	        <div class="info-name">Lots </div>
	        <div class="info-value">
	        <c:choose>
		        <c:when test="${ lot_count != 0 }">
		          <a href="controller?command=show_auctions&ownerId=${ client.id }">
		            <c:out value="${ lot_count }" />
		          </a>
		        </c:when>
		        <c:otherwise>
		          0
		        </c:otherwise>
	        </c:choose>
	        </div>
	      </div>
	      <br>
	      <form action="">
	        
	      </form>
	    </div>
	    <hr class="line">
    </div>
    
    <c:import url="/jsp/fragment/footer.jsp"></c:import>
  </body>
</html>