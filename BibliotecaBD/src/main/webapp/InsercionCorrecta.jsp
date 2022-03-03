<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insercion correcta</title>
</head>
<body>
<%
String id = request.getParameter("id");
String titulo= request.getParameter("titulo");
String autor = request.getParameter("autor");
String prestado = request.getParameter("prestado");
%>
<p> Datos guardados: </p>
<p> Id: <%= id%> </p>
<p> Titulo: <%= titulo %> </p>
<p> Autor: <%= autor %> </p>
<p> Estado: <%= prestado %> </p>
</body>
</html>