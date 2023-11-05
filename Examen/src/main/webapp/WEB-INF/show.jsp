<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tarea</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Tarea: ${tarea.titulo}</h1>
		<h3>Creador: ${tarea.creador.nombre} ${tarea.creador.apellido}</h3>
		<h3>Asignatario: ${tarea.asignatario}</h3>
		<h3>Prioridad: ${tarea.prioridad}</h3>	
		<a href="/tasks" class="btn btn-info">Regresar a Dashboard</a>
		<c:if test="${tarea.creador.id == usuarioEnSesion.id}">
			<!-- SI ES Creador -->
			<a href="/edit/${tarea.id}" class="btn btn-warning">Editar</a>
		</c:if>
		<c:if test="${tarea.creador.id == usuarioEnSesion.id}">
			<a href="/delete/${tarea.id}" class="btn btn-danger">Eliminar</a>
		</c:if>
		<c:if test="${tarea.asignatario == usuarioEnSesion}"	>
			<a href="/complete/${tarea.id}" class="btn btn-success">Completar</a>
		</c:if>
	</div>
</body>
</html>