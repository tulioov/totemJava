<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>


</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>

	<input type="hidden" name="action" id="action">
	<input type="hidden" name="id" id="id">
	<input type="hidden" name="descricao" id="descricao">
	<input type="hidden" name="localizacao" id="localizacao">
	<div class="container">
		<h2>Bem vindo NOME, Estamos ta etapa 5</h2>
			<div class="col-12">
				<button type="button" class="btn btn-primary btn-lg btn-block">Fibra</button>
				<button type="button" class="btn btn-primary btn-lg btn-block">Eletrica</button>
				<button type="button" class="btn btn-primary btn-lg btn-block">Etapa0</button>
				<button type="button" class="btn btn-primary btn-lg btn-block">Etapa1</button>
				<button type="button" class="btn btn-primary btn-lg btn-block">Etapa2</button>
				<button type="button" class="btn btn-primary btn-lg btn-block">Etapa3</button>
				<button type="button" class="btn btn-primary btn-lg btn-block">Etapa4</button>
			</div>
	</div>
<!-- 	<script src="../js/cadastroUsuario/cadastroUsuarioController.js"></script> -->
</body>
</html>
