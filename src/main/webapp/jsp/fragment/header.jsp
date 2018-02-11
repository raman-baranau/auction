<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Header title</title>
    <link rel="stylesheet" type="text/css" href="../css/header.css">
  </head>
  <body>
    <ul class="nav-bar">
      <li><a href="main.jsp" class="logo" ><img src="../images/auction.png"/></a></li>
      <li><a href="main.jsp">Home</a></li>
      <li><a href="controller?command=show_auctions">Lots</a></li><li>
      
      <c:choose>
        <c:when test="${role eq 'GUEST'}">
          <li style="float:right"><a href="register.jsp">Sign up</a></li>
          <li style="float:right"><a href="login.jsp">Sign in</a></li>
        </c:when>
        <c:otherwise>
         <li class="signout">
              <form name="signOutForm" method="POST" action="controller">
                <input type="hidden" name="command" value="logout" />
                <input type="submit" class="signoutbtn" value="Sign out"/>
              </form>
          </li>
          <li class="dropdown">
            <a href="#" class="dropbtn">Hello, ${ user.firstName }</a>
            <div class="dropdown-content">
              <a href="#">My profile</a>
              <c:choose>
                <c:when test="${ role eq 'CLIENT' }">
                  <a href="#">My lots</a>
                  <a href="auction_create.jsp">Create lot</a>
                </c:when>
              </c:choose>
            </div>
          </li>
         
        </c:otherwise>
      </c:choose>
    </ul>
  </body>
</html>