<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html 
PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
       "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="../css/auction_info.css">
  </head>
  <body>
    <c:import url="/jsp/fragment/header.jsp"></c:import>
    <h3><c:out value="${ errorFindAuctionMessage }"/></h3>
    <c:if test="${not empty auction }">
      <div class="lot-info" id="info">
	      <ul>
	        <li><img src=""/></li>
	        <li><c:out value="${ auction.lotName}" /></li>
	        <li><c:out value="${ auction.lotDescription}" /></li>
	        <li><c:out value="${ auction.startDate}" /></li>
	        <li><c:out value="${ auction.endDate}" /></li>
	        <li><c:out value="${ auction.initialPrice}" /></li>
	        <li><c:out value="${ auction.sellingPrice}" /></li>
	        <c:if test="${not empty auction.client }">
	          <li><c:out value="${ auction.client.firstName}" /></li>
	        </c:if>
	        <li><c:out value="${ auction.owner.firstName}" /></li>
	        <li>
	          <form name="paramForm" action="controller" method="POST">
	            <input type="hidden" name="command" value="delete_auction" />
	            <input type="text" name="auctionId" value="${ auction.id }" />
	            <input type="submit" value="Delete"/>
	          </form>
          </li>
	      </ul>
	    </div>
    </c:if>
    <script type="text/javascript">
      <%@include file="../js/auction_info.js"%>
    </script>
  </body>
</html>