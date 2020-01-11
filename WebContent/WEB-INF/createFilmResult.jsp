<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add your own film</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty film}">
			<ul>
				<li>${film.title}</li>
				<li>${film.landId}</li>
				<li>${film.rentDur}</li>
				<li>${fiilm.rate}</li>
				<li>${film.repCost}</li>
			</ul>
		</c:when>
	</c:choose>
</body>
</html>