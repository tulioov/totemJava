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
	
	
	<title>Cadastro de Local</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>

	<input type="hidden" name="action" id="action">
	<input type="hidden" name="id" id="id">
	<input type="hidden" name="descricao" id="descricao">
	<input type="hidden" name="localizacao" id="localizacao">
	<div class="container">
		<div class="row">
			<button type="submit" onclick="CadastroLocalController.addUser();" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
				Add Local
			</button>
			<div id="alertMsgIdTable" class=" mt15 alert fade in oculta ">
              	<a onclick="$('#alertMsgIdTable').addClass('oculta');" class="close">&times;</a>
              	<div></div>
			</div>
			<hr/>
		</div>
		<div class="modal scroll" id="myModal">
		</div>
		<div class="row">
			<table id="tableLocal" class="display">
				<thead>
			    <tr>
			        <th>ID</th>
			        <th>Nome</th>
			        <th>Constante Campo</th>
			        <th>Status</th>
			        <th>A&ccedil;&atilde;o</th>
			    </tr>
				</thead>
				<tbody></tbody>
			</table>
	    </div>
	</div>
	<script src="../externo/serializejson/serializejson.js"></script>
	<script src="../externo/dual-list/jquery.bootstrap-duallistbox.js"></script>
	<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
	<script src="../js/cadastroLocal/cadastroLocalTemplate.js"></script>
	<script src="../js/cadastroLocal/cadastroLocalController.js"></script>
</body>
</html>
