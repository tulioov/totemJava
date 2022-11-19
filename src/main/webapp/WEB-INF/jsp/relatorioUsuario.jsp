<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
	<title>Relatorio Usuario</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>

	<div class="container">
		<form id="formId">
		    <div id="alertMsgId" class="alert fade in oculta">
				<a onclick="$('#alertMsgId').addClass('oculta');$('#formId').find('input').removeClass('errorInput');" class="close">&times;</a>
				<div></div>
			</div>
			<input type="text" id="campoId" name="id" class="oculta">
			<div class="panel panel-default">
				<div class="panel-heading">Painel de pesquisa</div>
					<div class="panel-body">
					<div class="row">
						<div class="col-md-6">
							<div>Nome do Funcionario: </div>
							<select id="selectUsuarioId" class="selectpicker" data-live-search="true" multiple title="Escolha até mais de um">
							</select>
						</div>
					</div>
					<div class="row mt15">
						<div class="col-md-6">
							<div>Data inicio: </div>
							<input type="date" name="dtInicio" class="form-control" id="dtInicioId">
						</div>
						<div class="col-md-6">
							<div>Data fim: </div>
							<input type="date" name="dtFim" class="form-control" id="dtFimId">
						</div>
					</div>
				</div>
				<div class="row mt15">
					<div class="col-md-6">		
					    <button id="pesquiaseIdButton" type="button" class="btn btn-primary pull-right" onClick="RelatorioController.pesquisa()">
							Pesquisar
						</button>
					</div>
					<div class="col-md-6">		
					    <button id=donloadIdButton" type="button" class="btn btn-primary pull-right" onClick="RelatorioController.donwload()">
							Download
						</button>
					</div>
				</div>
			</div>
		</form>
		<div class="panel panel-default">
			<div class="panel-heading">Painel de pesquisa</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-12">
						<table id="tablePesquisa">
							<thead>
						    <tr>
						        <th>Matricula</th>
						        <th>Nome</th>
						        <th>Atividade</th>
						        <th>Tempo decorrido</th>
						        <th>Status</th>
						    </tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
			    </div>
		    </div>
	    </div>
    </div>
	<script src="../externo/serializejson/serializejson.js"></script>
	<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
	<script src="../js/util/selectUtil.js"></script>
	<script src="../js/relatorio/relatorioTemplate.js"></script>
	<script src="../js/relatorio/relatorioController.js"></script>
</body>
</html>
