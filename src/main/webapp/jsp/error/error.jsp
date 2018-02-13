<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Error Page</title>
		<link rel="shortcut icon" href="../images/logo.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
	</head>
	<body>
	  <c:import url="/jsp/fragment/header.jsp"></c:import>
		Request from ${pageContext.errorData.requestURI} is failed
		<br/>
		Servlet name or type: ${pageContext.errorData.servletName}
		<br/>
		Status code: ${pageContext.errorData.statusCode}
		<br/>
		Exception: ${pageContext.errorData.throwable}
	</body>
</html>