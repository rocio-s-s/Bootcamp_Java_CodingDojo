<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pregunta</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<a href="/preguntas" class="btn btn-success">Volver al inicio</a>
		<h1>${pregunta.contenido}</h1>
		<div>
			<h4>Etiquetas</h4>
			<c:forEach items="${pregunta.etiquetas}" var="etiqueta">
				<span class="badge bg-secondary">${etiqueta.tema}</span>
			</c:forEach>
		</div>
		<div class="row">
			<div class="col-6">
				<!-- TABLA DE RESPUESTAS -->
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Respuestas</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pregunta.respuestas}" var="respuesta">
							<tr>
								<td>${respuesta.texto}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-6">
				<!-- FORMULARIO -->
				<form:form method="post" action="/respuesta" modelAttribute="respuesta">
					<form:label path="texto">Ingresa tu respuesta</form:label>
					<form:textarea path="texto" class="form-control"/>
					<form:errors class="text-danger" path="texto"/>
					<form:hidden path="pregunta" value="${pregunta.id}"/>
					<input type="submit" class="btn btn-primary" value="Guardar Respuesta">
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>