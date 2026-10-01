<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.ruben.bluewave.model.Cliente"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Bluewave</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bluewave.css">

</head>

<body>

	<header class="header">

		<div class="header-content">

			<div class="logo">
				<a href="<%=request.getContextPath()%>/"> BLUEWAVE </a>
			</div>

			<nav>

				<a href="<%=request.getContextPath()%>/incidencia?action=search">
					Incidencias </a>

				<%
				Cliente usuario = (Cliente) request.getSession().getAttribute("usuario");

				if (usuario != null) {
				%>

				<span class="usuario"> Hola, <%=usuario.getNombre()%>
				</span> <a href="<%=request.getContextPath()%>/Cliente?action=logout">
					Cerrar sesión </a>

				<%
				} else {
				%>

				<a href="<%=request.getContextPath()%>/html/cliente/login.html">
					Iniciar sesión </a>

				<%
				}
				%>

			</nav>

		</div>

	</header>

	<main class="container">