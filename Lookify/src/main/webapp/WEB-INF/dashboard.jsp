<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<header class="d-flex justify-content-between align-items-center">
		<h1>¡Bienvenid@!</h1>
		<div class="mt-3 d-flex justify-content-end d-flex gap-2"> 
			<a href="/new" class="btn btn-success">Añadir Canción</a>
			<a href="/top" class="btn btn-success">Top 10</a>
		</div>
		</header>
		
		<form action="/busqueda" method="post" class="row">
			<div class="col-7">
				<input type="text" name="palabra" class="form-control" placeholder="busca por nombre del artista">
				</div>	
				<input type="submit" value="Buscar" class="btn btn-primary col-1">
		</form>
		
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Título</th>
					<th>Rating</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${songs}" var="song">
					<tr>
						<td><a href="/show/${song.id}">${song.title}</a></td>
						<td>${song.rating}</td>
						<td>
							<a href="/delete/${song.id}" class="btn btn-danger">Quitar</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>