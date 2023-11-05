<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulario</title>
</head>
<body>
	<div class="container">
		<h1>Prueba tu suerte</h1>
		<form action="/damemisuerte" method="POST">
			<div>
				<label for="numeroId">Elige un número del 1 - 25:</label>
				<input id="numeroId" type="number" name="numero" class="form-control">
			</div>
			<div>
				<label for="ciudadId">Ingresa una ciudad</label>
				<input id="ciudadId" type="text" name="ciudad" class="form-control">
			</div>
			<div>
				<label for="cualquierNombreId">Ingresa un nombre de cualquier persona real</label>
				<input id="cualquierNombreId" type="text" name="cualquierNombre" class="form-control">
			</div>
			<div>
				<label for="hobbyId">Ingresa un hobby</label>
				<input id="hobbyId" type="text" name="hobby" class="form-control">
			</div>
			<div>
				<label for="animalId">Ingresa un animal</label>
				<input id="animalId" type="text" name="animal" class="form-control">
			</div>
			<input type="submit" value="Enviar" class="btn-success">
		</form>
	</div>
</body>
</html>