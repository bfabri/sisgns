<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Home Page</title>
	</head>
	<body>
		<h3>Login</h3>
		<c:forEach var="error" items="${errors}">
    		${error.category} - ${error.message}<br />
		</c:forEach>
		<form action="${linkTo[HomeController].login}" method="post">
			<label for="email"><fmt:message key="user.email"/></label>
			<input type="text" id="email" name="user.email" value="${user.email}" placeholder="<fmt:message key="user.email"/>"/>
				
			<label for="password"><fmt:message key="user.password"/></label>
			<input type="password" name="user.password" value="${user.password}" placeholder="<fmt:message key="user.password"/>" />
					
			<button type="submit"><fmt:message key="send"/></button>
		</form>
	</body>
</html>