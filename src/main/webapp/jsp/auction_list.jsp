<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<table>
    		<c:forEach var="auction" items="${auctions}" varStatus="status">
	 			<tr>
				  	<td><c:out value="${ auction.lotName }" /></td>
					<td><c:out value="${ auction.lotDescription }" /></td>
					<td><c:out value="${ auction.startDate }" /></td>
					<td><c:out value="${ auction.endDate }" /></td>
					<td><c:out value="${ auction.initialPrice }" /></td>
					<td><c:out value="${ auction.lastPrice }" /></td>
					<td><c:out value="${ auction.lotName }" /></td>
					<td><c:out value="${ auction.lotName }" /></td>
				</tr>
	    	</c:forEach>
		</table>
	</body>
</html>