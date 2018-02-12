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
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/auction_info.css">
  </head>
  <body>
    <c:import url="/jsp/fragment/header.jsp"></c:import>
    <h3><c:out value="${ errorFindAuctionMessage }"/></h3>
    <c:if test="${not empty auction }">
      <div class="lot-info" id="info">
	      <ul>
	        <li><img src=""/></li>
	        <li>
            <div class="info-block">
              <div class="info-name">Lot name</div>
              <div class="info-value"><c:out value="${ auction.lotName}" /></div>
            </div>
	        </li>
	        <li>
	          <div class="info-block">
              <div class="info-name">Lot description</div>
              <div class="info-value"><c:out value="${ auction.lotDescription}" /></div>
            </div>
	        </li>
	        <li>
            <div class="info-block">
                <div class="info-name">Lot owner</div>
                <div class="info-value"><c:out value="${ auction.owner.firstName}" /></div>
            </div>
          </li>
	        <li>
	          <div class="info-block">
              <div class="info-name">Start date</div>
              <div class="info-value"><c:out value="${ auction.startDate}" /></div>
            </div>
	        </li>
	        <li>
	          <div class="info-block">
              <div class="info-name">End date</div>
              <div class="info-value"><c:out value="${ auction.endDate}" /></div>
            </div>
	        </li>
	        <li>
	          <div class="info-block">
              <div class="info-name">Initial price</div>
              <div class="info-value"><c:out value="${ auction.initialPrice}" /></div>
            </div>
	        </li>
	        <li>
	          <div class="info-block">
              <div class="info-name">Last bid</div>
              <div class="info-value"><c:out value="${ auction.sellingPrice}" /></div>
            </div>
	        </li>
	        <c:if test="${not empty auction.client }">
	          <li>
	            <div class="info-block">
                <div class="info-name">Last bid owner</div>
                <div class="info-value"><c:out value="${ auction.client.firstName}" /></div>
              </div>
	          </li>
	        </c:if>
	        <li>
	          <c:if test="${ auction.owner.id != user.id }">
	            <div class="info-block">
                <form name="paramForm" action="controller" method="POST">
	                <input type="hidden" name="command" value="place_bid" />
	                <input type="hidden" name="auctionId" value="${ auction.id }" />
	                <div class="info-name">
                    <input type="text" name="bid" value="${ auction.sellingPrice * 1.01 }"/>
                  </div>
	                <div class="info-value">
	                  <input type="submit" class="placebidbtn" value="Place bid"/>
	                </div>
                </form>
              </div>
	          </c:if>
	        </li>
	        <li>
	          <c:if test="${ role != 'GUEST' }">
		          <form name="paramForm" action="controller" method="POST">
		            <input type="hidden" name="command" value="delete_auction" />
		            <input type="hidden" name="auctionId" value="${ auction.id }" />
		            <input type="submit" class="deletebtn" value="Delete"/>
		          </form>
	          </c:if>
          </li>
	      </ul>
	    </div>
    </c:if>
    <script type="text/javascript">
      <%@include file="../js/auction_info.js"%>
    </script>
  </body>
</html>