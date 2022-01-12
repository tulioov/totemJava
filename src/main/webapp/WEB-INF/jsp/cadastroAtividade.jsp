<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
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
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
				Add Atividade
			</button>
			<hr/>
		</div>
		
		<div class="modal" id="myModal">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h4 class="modal-title">Modal Heading</h4>
		                <button type="button" class="close" data-dismiss="modal">&times;</button>
		            </div>
		            <div class="modal-body">
		                Modal body..
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		            </div>
		        </div>
		    </div>
		</div>
		
		
		<div class="row">
			<table id="tableUsuario">
				<thead>
			    <tr>
			        <th>ID</th>
			        <th>Descrição</th>
			        <th>Contante Campo</th>
			        <th>Tempo Estimado</th>
			    </tr>
				</thead>
				<tbody></tbody>
			</table>
	    </div>
	</div>
	
	<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
<!-- 	<script src="../js/cadastroUsuario/cadastroUsuarioController.js"></script> -->
</body>
</html>
