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
	
		<div id="painelId" class="row">
		</div>
	</div>
	
	<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
	<script src="../js/controlTotens/controlTotensTemplate.js"></script>
	<script src="../js/controlTotens/controlTotensController.js"></script>
</body>
</html>
