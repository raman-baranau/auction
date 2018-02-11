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
    <link rel="stylesheet" type="text/css" href="../css/auction_list.css">
  </head>
  <body>
    <c:import url="/jsp/fragment/header.jsp"></c:import>
      <h3><c:out value="${ emptyAuctionList }"/></h3>
	    <c:forEach var="auction" items="${auctions}" varStatus="status">
	     <div class="lot-info">
	        <ul>
	          <li><img src=""/></li>
	          <li><c:out value="${ auction.lotName          }" /></li>
						<li><c:out value="${ auction.endDate          }" /></li>
						<li><c:out value="${ auction.sellingPrice     }" /></li>
						<li><c:out value="${ auction.owner.firstName  }" /></li>
						<li>
						  <form name="paramForm" action="controller" method="GET">
						    <input type="hidden" name="command" value="show_auction" />
						    <input type="hidden" name="auctionId" value="${ auction.id }" />
						    <input type="submit" value="Info"/>
						  </form>
						</li>
	        </ul>
	     </div>
	    </c:forEach>
  </body>
</html>