<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="../css/header.css">
  </head>
  <body>
	<c:import url="/jsp/fragment/header.jsp"></c:import>
    <h3>Welcome</h3>
    <hr/>
    ${user}, hello!
    <hr/>
    <c:import url="/jsp/fragment/footer.jsp"></c:import>
  </body>
</html>