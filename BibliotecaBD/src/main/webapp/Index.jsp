<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Biblioteca</title>
	</head>
	
	<body>
		<h1>Biblioteca</h1>
		<h2>Seleccione lo que desea hacer a continuación:</h2>
		
		<form method="post" action="insertaLibros">
				<button type="submit">Insertar libro</button>
			</form>
			<form method="post" action="consultarLibros">
				<button type="submit">Consultar libros</button>
			</form>
		
	</body>
</html>