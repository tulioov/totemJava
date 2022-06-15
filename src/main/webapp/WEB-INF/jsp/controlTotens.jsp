<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
	<title>Controle Totem</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>

	<input type="hidden" name="action" id="action">
	<input type="hidden" name="id" id="id">
	<input type="hidden" name="descricao" id="descricao">
	<input type="hidden" name="localizacao" id="localizacao">
	<div class="container">
	
		<div class="modal" id="myModal">
		    
		</div>
	
		<div class="row">
			<div class="col-md-3">
				<button onclick="ControlTotensController.abrirModal();" data-toggle="modal" data-target="#myModal" type="button" class="btn btn-primary" style="width: 18rem;">
		  			<div class="card-body">
		    		<h5 class="card-title">Embarcação A1</h5>
		    		<div class="card-text">
			    		<div>
							<div class="progress">
							  <div class="progress-bar bg-danger" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
							</div>
						</div>
					</div>
					</div>
				</button>
			</div>
			<div class="col-md-3">
				<button data-toggle="modal" data-target="#myModal" type="button" class="btn btn-primary" style="width: 18rem;">
		  			<div class="card-body">
		    		<h5 class="card-title">Embarcação A2</h5>
			    		<div class="card-text">
			    			<div>
								<div class="progress">
								  <div class="progress-bar bg-warning" role="progressbar" style="width: 60%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
								</div>
							</div>
						</div>
					</div>
				</button>
			</div>
			<div class="col-md-3">
				<button data-toggle="modal" data-target="#myModal" type="button" class="btn btn-primary" style="width: 18rem;">
		  			<div class="card-body">
		    		<h5 class="card-title">Embarcação A3</h5>
			    		<p class="card-text">
			    			<td>
								<div class="progress">
								  <div class="progress-bar bg-warning" role="progressbar" style="width: 60%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
								</div>
							</td>
						</p>
					</div>
				</button>
			</div>
			<div class="col-md-3">
				<button data-toggle="modal" data-target="#myModal" type="button" class="btn btn-primary" style="width: 18rem;">
		  			<div class="card-body">
		    		<h5 class="card-title">Embarcação A4</h5>
			    		<p class="card-text">
			    			<td>
								<div class="progress">
								  <div class="progress-bar bg-warning" role="progressbar" style="width: 60%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
								</div>
							</td>
						</p>
					</div>
				</button>
			</div>
		</div>
	</div>
	
	<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
	<script src="../js/controlTotens/controlTotensTemplate.js"></script>
	<script src="../js/controlTotens/controlTotensController.js"></script>
</body>
</html>
