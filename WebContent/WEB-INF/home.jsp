<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="home.css" type="text/css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Films</title>
</head>
<body>
<section class="findFilm">
<h3>Find a Film by its ID</h3>
<a href="FindFilmById.html">Find Film By Id</a><br>
</section>
<section class="createFilm">
<h3>Create your own film</h3>
<a href="createFilm.html">Add your own film</a><br>
</section>
<section class="deleteFilm">
<h3>Delete a film</h3>
<a href="deleteFilm.html">Delete your films</a>
</section>
<section class="updateFilm">
<h3>Update a film</h3>
<a href="updateFilm.html">Update your films</a>
</section>

</body>
<style>
body {
	margin: 0;
}

.findFilm {
	height: 100vh;
  background-color: green;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding:0 20px;
  color: white;
  
}
.createFilm {
  height: 100vh;
  background-color: turquoise;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding:0 20px;
  color: white;
  }
.deleteFilm {
 height: 100vh;
  background-color: skyblue;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding:0 20px;
  color: white;
 }
.updateFilm {
 height: 100vh;
  background-color: blue;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding:0 20px;
  color: white;
 }
</style>
</html>