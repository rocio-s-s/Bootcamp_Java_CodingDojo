<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Asignar Hobby</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Asignar hobbies a:${usuario.nombre} ${usuario.apellido}</h1>
		<h2>Hobbies</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Actividad</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${hobbies}" var="hobby">
					<tr>
						<td>${hobby.actividad}</td>
						<td>
							<c:if test="${usuario.hobbies.contains(hobby)}">
								<a class="btn btn-danger" href="/quitarHobby/${usuario.id}/${hobby.id}">Quitar Hobby</a>
							</c:if>
							
							<c:if test="${not usuario.hobbies.contains(hobby)}">
								<!-- /asignarHobby/4/1 -->
								<a class="btn btn-warning" href="/asignarHobby/${usuario.id}/${hobby.id}" >Asignar Hobby</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>		
		</table>
	</div>
</body>
</html>