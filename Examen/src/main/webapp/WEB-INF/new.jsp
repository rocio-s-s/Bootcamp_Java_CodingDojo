<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nueva Tarea</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row">
			<h2>Crear Tarea</h2>
			<form:form action="/crear" method="post" modelAttribute="tarea">
				<div>
					<form:label path="titulo">Tarea:</form:label>
					<form:input path="titulo" class="form-control" />
					<form:errors path="titulo" class="text-danger" />
				</div>
				<div>
					<form:label path="asignatario">Asignatario</form:label>
					<form:select path="asignatario" class="form-select">
						<c:forEach items="${asignatarios}" var="asignatario">
							<form:option value="${asignatario}">${asignatario.nombre} ${asignatario.apellido}</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div>
					<form:label path="prioridad">Prioridad</form:label>
					<form:select path="prioridad" class="form-select">
						<c:forEach items="${prioridades}" var="prioridad">
							<form:option value="${prioridad}">${prioridad}</form:option>
						</c:forEach>
					</form:select>
				</div>
				<form:hidden value="${usuarioEnSesion.id}" path="creador" />
				<!--TODOS LOS ERRORES: <form:errors path="*" class="text-danger" />  -->
				<input type="submit" value="Crear Tarea" class="btn btn-success" />
			</form:form>
		</div>
	</div>
</body>
</html>