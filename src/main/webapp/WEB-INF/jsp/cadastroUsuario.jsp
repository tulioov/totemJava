<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:th="http://thymeleaf.org"
	  xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<link href="../externo/dual-list/bootstrap-duallistbox.css" rel="stylesheet" />
	
	
	<title>Cadastro de Usuario</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>

	<input type="hidden" name="action" id="action">
	<input type="hidden" name="id" id="id">
	<input type="hidden" name="descricao" id="descricao">
	<input type="hidden" name="localizacao" id="localizacao">
	<div class="container">
		<div class="row">
			<button type="submit" onclick="CadastroUsuarioController.addUser();" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
				Add Usuario
			</button>
			<hr/>
		</div>
		
		<div class="modal" id="myModal">
		</div>
		
		
		<div class="row">
			<table id="tableUsuario">
				<thead>
			    <tr>
			        <th>ID</th>
			        <th>Nome</th>
			        <th>Especialidade</th>
			    </tr>
				</thead>
				<tbody></tbody>
			</table>
	    </div>
	</div>
	
	<script src="../externo/serializejson/serializejson.js"></script>
	<script src="../externo/dual-list/jquery.bootstrap-duallistbox.js"></script>
	<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
	<script src="../js/cadastroUsuario/cadastroUsuarioTemplate.js"></script>
	<script src="../js/cadastroUsuario/cadastroUsuarioController.js"></script>
</body>
</html>
