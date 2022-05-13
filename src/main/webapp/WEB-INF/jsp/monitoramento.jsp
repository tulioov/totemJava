<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="../css/monitorUser/steps.css">
	<link rel="stylesheet" type="text/css" href="../css/monitorUser/monitorUser.css">
	<title>Monitor user</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>

	<input type="hidden" name="action" id="action">
	
	<div class="container">
		<div class="row">
			<button type="button" onclick="MonitorUserController.abrirEscolhaBarco();" class="btn btn-primary">
				RFC
			</button>
			<hr/>
		</div>
		<div class="modal" id="myModal"></div>
		<div id="contentIdBarco">
		</div>
		<div id="contentId">
		</div>
	</div>
	
	<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
	<script src="../js/monitorUser/monitorUserTemplate.js"></script>
	<script src="../js/monitorUser/monitorUserController.js"></script>
</body>
</html>
