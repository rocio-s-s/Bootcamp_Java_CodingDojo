<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dojo Overflow</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Dashboard de Preguntas</h1>
		<a class="btn btn-success" href="/nueva">Nueva Pregunta</a>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Pregunta</th>
					<th>Etiquetas</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${preguntas}" var="pregunta">
					<tr>
						<td><a href="/preguntas/${pregunta.id}">${pregunta.contenido}</a></td>
						<td>
							<c:forEach items="${pregunta.etiquetas}" var="etiqueta">
								<span class="badge bg-secondary">${etiqueta.tema}</span>
							</c:forEach>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>