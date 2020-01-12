<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FilmByIdResult</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty film}">
			<ul>
				<li><strong>Title:</strong> ${film.title}</li>
				<li><strong>Description:</strong> ${film.desc}</li>
				<li><strong>Actors:</strong> ${film.actors}</li>
				<li><strong>Language:</strong> ${film.langId}</li>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No film found</p>
		</c:otherwise>
	</c:choose>
</body>
</html>
