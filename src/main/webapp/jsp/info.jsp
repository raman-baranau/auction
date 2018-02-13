<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html 
PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
       "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error info</title>
    <link rel="shortcut icon" href="../images/logo.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
  </head>
  <body>
    <c:import url="/jsp/fragment/header.jsp"></c:import>
    <div style="text-align: center; ">
      <h3><c:out value="${ msgSessionExpired }"/></h3>
      <h3><c:out value="${ msgInvalidParameter }"/></h3>
      <h3><c:out value="${ msgBidSuccess }"/></h3>
      <h3><c:out value="${ msgBidFail }"/></h3>
      <h3><c:out value="${ profileNotFound }"/></h3>
    </div>
    <c:import url="/jsp/fragment/footer.jsp"></c:import>
  </body>
</html>