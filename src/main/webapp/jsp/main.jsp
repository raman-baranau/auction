<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Welcome</title>
    <link rel="shortcut icon" href="../images/logo.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/footer.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
  </head>
  <body>
	<c:import url="/jsp/fragment/header.jsp"></c:import>
    
    <div class="container">
	    <h3>Welcome</h3>
	    <hr/>
      Auction provides to users such possibilities as creating lots, 
      making bids, manipulating lots. There are 2 types of an auction
      supported: straight and reverse.
    </div>
    <c:import url="/jsp/fragment/footer.jsp"></c:import>
  </body>
</html>