<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Top 10</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Top 10</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Título</th>
					<th>Artista</th>
					<th>Rating</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${songs}" var="song">
					<tr>
						<td>${song.title}</td>
						<td>${song.artist}</td>
						<td>${song.rating}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/dashboard" class="btn btn-primary">Regresar a Dashboard</a>
	</div>
</body>
</html>