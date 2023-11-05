<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nueva Pregunta</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Nueva Pregunta</h1>
		<form:form method="post" action="/crear" modelAttribute="pregunta">
			<div>
				<form:label path="contenido">Pregunta</form:label>
				<form:input path="contenido" class="form-control"/>
				<form:errors path="contenido" class="text-danger"/>
			</div>
			<div>
				<label>Etiquetas</label>
				<input type="text" name="textoEtiquetas" class="form-control">
			</div>
			<input type="submit" class="btn btn-success mt-3" value="Guardar">
		</form:form>
	</div>
</body>
</html>