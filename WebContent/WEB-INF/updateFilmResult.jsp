<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update your own film</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty film}">
			<ul>
				<li><strong>Title: </strong>${film.title}</li>
				<li><strong>Description: </strong>${film.desc}</li>
				<li><strong>Release Year: </strong>${film.realeaseYear}</li>
				<li><strong>Rental Duration:</strong>${film.rentDur}
				<li><strong>Rental Rate: </strong>${film.rate}</li>
			</ul>
		</c:when>
	</c:choose>
</body>
</html>