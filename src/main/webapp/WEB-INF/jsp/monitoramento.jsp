<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="../css/monitorUser/monitorUser.css">
	<title>Monitor user</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>

	<input type="hidden" name="action" id="action">
	<input type="hidden" name="id" id="id">
	<input type="hidden" name="descricao" id="descricao">
	<input type="hidden" name="localizacao" id="localizacao">
	<div class="container">
	
		<div class="row">
			<button type="button" onclick="MonitorUserController.abrirModal();" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
				RFC
			</button>
			
			<button type="button" onclick="MonitorUserController.abrirModalContinuidade();" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
				RFC Continuidade
			</button>
			
			<button type="button" onclick="MonitorUserController.abrirModalSaida();" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
				RFC Saída
			</button>
			<hr/>
		</div>
		
		<div class="modal" id="myModal">
		    
		</div>
		<h3>Processo de fabricação do barco</h3>
		<div class="progress">
		  	<div class="progress-bar bg-success" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">25%</div>
		</div>
		<hr/>
		<div class="row">
			<table id="tableMonitorUser">
				<thead>
			    <tr>
			        <th>ID</th>
			        <th>Nome</th>
			        <th>Trabalhando em</th>
			        <th>Hr. Entrada</th>
			        <th>Tempo</th>
			    </tr>
				</thead>
				<tbody>
					<tr>
						<td>01</td>
						<td>Fernand</td>
						<td>Fibra</td>
						<td>08:00</td>
						<td>
							<div class="progress">
							  <div class="progress-bar bg-success" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">03:00</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>01</td>
						<td>maria</td>
						<td>Eletrica</td>
						<td>08:00</td>
						<td>
							<div class="progress">
							  <div class="progress-bar bg-warning" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">5:00</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>01</td>
						<td>Geraldo</td>
						<td>Motor</td>
						<td>08:00</td>
						<td>
							<div class="progress">
							  <div class="progress-bar bg-danger" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100">13:00</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>01</td>
						<td>Jose</td>
						<td>Fibra</td>
						<td>08:00</td>
						<td>
							<div class="progress">
							  <div class="progress-bar bg-warning" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">5:00</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>01</td>
						<td>Fulano</td>
						<td>Fibra</td>
						<td>08:00</td>
						<td>
							<div class="progress">
							  <div class="progress-bar bg-success" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">03:00</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
	<script src="../js/monitorUser/monitorUserTemplate.js"></script>
	<script src="../js/monitorUser/monitorUserController.js"></script>
</body>
</html>
