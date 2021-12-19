<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>


</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>

	<input type="hidden" name="action" id="action">
	<input type="hidden" name="id" id="id">
	<input type="hidden" name="descricao" id="descricao">
	<input type="hidden" name="localizacao" id="localizacao">
	<div class="container">
		<div class="row col-md-6 col-md-offset-2 custyle">
			<table class="table table-striped custab">
				<thead>
			    <tr>
			        <th>ID</th>
			        <th>Descrição</th>
			        <th>Localização</th>
			        <th class="text-center">Action</th>
			    </tr>
				</thead>
		  </table>
	  </div>
	</div>
	<script src="../js/cadastroUsuario/cadastroUsuarioController.js"></script>
</body>
</html>
