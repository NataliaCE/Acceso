<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insertar libros</title>
</head>
<body>
	<h1>Insertar Libros</h1>
	<table>
		<tr>
			<th>ID</th>
			<th>Título</th>
			<th>Autor</th>
			<th>Prestado</th>
		</tr>
		<form><tr>
			<td><input type="text" name="id" size=4></td>
			<td><input type="text" name="titulo" size=50></td>
			<td><input type="text" name="autor" size=30></td>
			<td><select name="prestado">
				<option value="true">Prestado</option>
				<option value="false">Sin prestar</option>	
			</select></td>
			<td><button type="submit">Añadir</button></td>
		</tr></form>
		
	</table>
</body>
</html>