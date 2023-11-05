<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Registra tu usuario</h1>
		<form action="/registrarme" method="POST">
			<div>
				<label for="nombreId">Nombre</label>
				<input id="nombreId" type="text" name="nombre" class="form-control" placeholder="Escribe tu nombre">
				<label for="emailId">Email</label>
				<input id="emailId" type="email" name="email" class="form-control" placeholder="Escribe tu email">
			</div>
			<input type="submit" value="Enviar" class="btn-success">
		</form>
		<p class="text-danger">${errorNombre}</p>
	</div>
</body>
</html>