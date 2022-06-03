<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta http-equiv="cache-control" content="max-age=0" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="../css/monitorUser/steps.css">
	<link rel="stylesheet" type="text/css" href="../css/monitorUser/monitorUser.css">
	<title>Monitor user</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>

	<input type="hidden" name="action" id="action">
	
	<div class="container">
		<div class="modal" id="myModal"></div>
		<div id="contentIdBarco">
		</div>
		<div style="position: absolute; z-index: -1; margin-top: 50px;" >
			<form id="formNCId" onSubmit="MonitorUserController.abrirEscolhaBarco();" role="form"  class="login-box" onfocusout="document.getElementById('nfcId').focus();">
				<input type="text" id="nfcId" autofocus>
			</form>
		</div>
		<div id="contentId">
		</div>
		
	</div>
	
	<script src="../externo/serializejson/serializejson.js"></script>
	<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
	<script src="../js/monitorUser/monitorUserTemplate.js"></script>
	<script src="../js/monitorUser/monitorUserController.js"></script>
</body>
</html>
