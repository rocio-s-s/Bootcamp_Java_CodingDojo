<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalles</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>${song.title} ${usuario.apellido}</h1>
		<h3>${song.artist}</h3>
		<h3>${song.rating}</h3>
		<a href="/dashboard" class="btn btn-info">Regresar a Dashboard</a>
		<a href="/delete/${song.id}" class="btn btn-danger">Quitar</a>
	</div>
</body>
</html>