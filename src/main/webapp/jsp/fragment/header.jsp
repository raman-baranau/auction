<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Header title</title>
    <link rel="stylesheet" type="text/css" href="../css/header.css">
  </head>
  <body>
    <ul>
      <li><a class="logo" href=""><img src="../images/auction.png"/></a></li>
      <li><a class="active" href="main.jsp">Home</a></li>
      <li><a href="">Lots</a></li><li>
      
      <c:choose>
        <c:when test="${role eq 'GUEST'}">
          <li style="float:right"><a href="register.jsp">Sign up</a></li>
          <li style="float:right"><a href="login.jsp">Sign in</a></li>
        </c:when>
        <c:otherwise>
          <li class="dropdown">
            <a href="#" class="dropbtn">Hello, ${ user }</a>
            <div class="dropdown-content">
              <a>item</a>
              <a>item</a>
              <a>item</a>
            </div>
          </li>
        </c:otherwise>
      </c:choose>
    </ul>
  </body>
</html>