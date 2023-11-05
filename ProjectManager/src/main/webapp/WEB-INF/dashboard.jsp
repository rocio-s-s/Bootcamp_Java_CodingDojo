<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<nav class="d-flex justify-content-between align-items-center">
			<h1>¡Bienvenid@! ${usuarioEnSesion.nombre}</h1>
			
			<a href="/nuevo" class="btn btn-success">Crear Proyecto</a>
			
			<a href="/logout" class="btn btn-danger">Cerrar Sesión</a>
		</nav>
		
		<div class="row">
			<h2>Todos los Proyectos</h2>
			<table>
				<thead>
					<tr>
						<th>Título del Proyecto</th>
						<th>Líder del Proyecto</th>
						<th>Fecha de Entrega</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${otrosProyectos}" var="pr">
						<tr>
							<td>${pr.titulo}</td>
							<td>${pr.lider.nombre}</td>
							<td>${pr.fecha}</td>
							<td>
								<a href="/unir/${pr.id}" class="btn btn-primary">Unirme al Proyecto</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<h2>Mis Proyectos</h2>
			<table>
				<thead>
					<tr>
						<th>Título del Proyecto</th>
						<th>Líder del Proyecto</th>
						<th>Fecha de Entrega</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${misProyectos}" var="pr">
						<tr>
							<td>${pr.titulo}</td>
							<td>${pr.lider.nombre}</td>
							<td>${pr.fecha}</td>
							<td>
								<c:if test="${pr.lider.id == usuarioEnSesion.id}">
								<!-- SI ES LIDER -->
									<a href="/editar/${pr.id}" class="btn btn-warning">Editar</a>
								</c:if>
								<c:if test="${pr.lider.id != usuarioEnSesion.id}">
									<a href="/salir/${pr.id}" class="btn btn-danger">Salir del Proyecto</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	</div>
</body>
</html>