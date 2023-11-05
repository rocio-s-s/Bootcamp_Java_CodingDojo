<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	
	<div class="container">
		<header class="d-flex justify-content-between align-items-center">
			<h1>¡Bienvenid@ a tu Dashboard!</h1>
			<a href="/nuevo" class="btn btn-success">Nuevo Usuario</a>
		</header>
		
		<form action="/busqueda" method="POST" class="row">
			<div class="col-10">
				<input type="text" name="palabra" class="form-control" placeholder="Ingresa el nombre a buscar">
			</div>
			<input type="submit" value="Buscar" class="btn btn-primary col-2">
		</form>
		
		<table class="table table-hover">
		<!--  -->
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>Email</th>
					<th>Dirección</th>
					<th>Salón</th>
					<th>Hobbies</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<!-- 
					usuarios = LIST {obj(elena), obj(juana), obj(pablo)}
					usuario = obj(elena) -> nombre = "Elena" apellido = "De Troya"
					usuario = obj(juana) 
				 -->
				<c:forEach items="${usuarios}" var="usuario">
					<tr><!-- table row -->
						<td>${usuario.nombre}</td>
						<td>${usuario.apellido}</td>
						<td>${usuario.email}</td>
						<td>${usuario.direccion.calle} ${usuario.direccion.numero}</td>
						<td>${usuario.salon.nombre}</td>
						<td>
							<ul>
								<c:forEach items="${usuario.hobbies}" var="hobby">
									<li>${hobby.actividad}</li>
								</c:forEach>
							</ul>
						</td>
						<td>
							<a href="/mostrar/${usuario.id}" class="btn btn-info">Mostrar</a>
							
							<form action="/borrar/${usuario.id}" method="post">
								<input type="hidden" name="_method" value="DELETE" >
								<input type="submit" value="Borrar" class="btn btn-danger">
							</form>
							
							<a href="/editar/${usuario.id}" class="btn btn-primary">Editar</a>
							<a href="/asignar/${usuario.id}" class="btn btn-warning">Asignar Hobbies</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</body>
</html>