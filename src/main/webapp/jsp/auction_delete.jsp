<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Create new lot</title>
    <link rel="shortcut icon" href="../images/logo.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/auction_delete.css">
  </head>
  <body>
    <c:import url="/jsp/fragment/header.jsp"></c:import>
    <div class=".success">
      ${ msgDeleteSuccess }
    </div>
    <div class=".fail">
      ${ msgDeleteFail }
    </div>
    <c:import url="/jsp/fragment/footer.jsp"></c:import>
  </body>
</html>