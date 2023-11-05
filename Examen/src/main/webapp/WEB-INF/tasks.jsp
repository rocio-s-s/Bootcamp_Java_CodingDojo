<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administrador de Tareas</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<nav class="d-flex justify-content-between align-items-center">
			<h1>¡Bienvenid@! ${usuarioEnSesion.nombre}</h1>
			<a href="/logout" class="btn btn-danger">Cerrar Sesión</a>
		</nav>
		
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Tarea</th>
						<th>Creador</th>
						<th>Asignatario</th>
						<th>Prioridad</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tareas}" var="tarea">
						<tr>
							<td><a href="/show/${tarea.id}">${tarea.titulo}</a></td>
							<td>${tarea.creador.nombre} ${tarea.creador.apellido }</td>
							<td>${tarea.asignatario}</td>	<!-- Profe no pude hacer que se mostrara el nombre -->
							<td>${tarea.prioridad}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<a href="/new" class="btn btn-primary">Crear Tarea</a>	
	</div>
	
</body>
</html>