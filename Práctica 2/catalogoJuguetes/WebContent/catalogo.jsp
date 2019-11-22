<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Catálogo de juguetes.</title>
</head>
<body style="margin:40px; font-family:cursive; background-image: url(./Imagenes/fondo.jpg)">
	<h1 style="font-size:40px">Catálogo de juguetes.</h1>
	<a style='font-size:20px;background-color: antiquewhite;color: black;padding: 14px 10px;
		text-align: center;text-decoration: none; float:right; margin-top:-80px' href='index.html'>Página principal</a>
	<p>${catalogo}</p>
	<p style='color:green'>${resultadoOperaciones}
</body>
</html>