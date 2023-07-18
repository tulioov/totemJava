<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
	<title>Importar Atividade</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>

	<div class="container">
		<div class="row mt15">
			<button type="button" class="btn btn-primary" onClick="ImportAtividadeController.downloadTemplateExcel()">
				Download Template excel
			</button>
		</div>
		<div class="row mt15">
			<input class="btn btn-primary" id="fileupload" type=file name="files[]">
		</div>
		
		<div class="row mt15">
			<table id="tableAtividade">
				<thead>
			    <tr>
			    	<th>Codigo</th>
			        <th>Nome</th>
			        <th>Constante Campo</th>
			        <th>Descri&ccedil;&atilde;o</th>
			        <th>Tempo Estimado (min)</th>
			        <th class="text-center">Status</th>
			    </tr>
				</thead>
				<tbody></tbody>
			</table>
	    </div>
	    <div class="row mt15">
		    <button id="salvarIdButton" type="button" class="btn btn-primary pull-right" onClick="ImportAtividadeController.salvar()">
				Salvar
			</button>
		</div>
	</div>
	<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/2.0.0/FileSaver.min.js"></script>
	<script src="https://cdn.sheetjs.com/xlsx-latest/package/dist/xlsx.full.min.js"></script>
	<script src="../js/importarAtividade/importarAtividadeTemplate.js"></script>
	<script src="../js/importarAtividade/importarAtividadeController.js"></script>
</body>
</html>
