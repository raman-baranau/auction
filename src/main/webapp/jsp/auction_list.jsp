<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html 
PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
       "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of auctions</title>
    <link rel="shortcut icon" href="../images/logo.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/footer.css">
    <link rel="stylesheet" type="text/css" href="../css/auction_list.css">
  </head>
  <body>
    <c:import url="/jsp/fragment/header.jsp"></c:import>
		<h3><c:out value="${ emptyAuctionList }"/></h3>
		
		<div class="container">
			<h2>Auction list</h2>
	    <hr>
			<c:forEach var="auction" items="${auctions}" varStatus="status">
				<div class="lot-info">
				  <ul>
				    <li><img src=""/></li>
				    <li class="lot-name"><c:out value="${ auction.lotName }" /></li>
				    <li><c:out value="${ auction.endDate }" /></li>
				    <li><c:out value="${ auction.sellingPrice }" /></li>
				    <li><c:out value="${ auction.owner.firstName }" /></li>
						<li>
						  <a href="controller?command=show_auction&auctionId=${ auction.id }">
				         More info
				      </a>
					  </li>
				  </ul>
				</div>
			</c:forEach>
    </div>
    <c:import url="/jsp/fragment/footer.jsp"></c:import>
  </body>
</html>