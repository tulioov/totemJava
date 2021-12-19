<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta>
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta http-equiv="Cache-control" content="no-cache">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="../css/home/homeStyle.css">
	<title>Welcome</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../js/home/homeController.js"></script>
	
	<div class="wrapper">
		<nav id="sidebar">
		    <div class="sidebar-header">
		        <h3>Menu</h3>
		    </div>
		
		    <ul class="list-unstyled components">
		    	<li>
		            <a href="#">Menu</a>
		        </li>
		        <li class="active">
		            <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false">Adm</a>
		            <ul class="collapse list-unstyled" id="homeSubmenu">
		                <li><a href="/cadastroUsuario" onclick="CadastroUsuarioController.listar();" >Cadastro Usuario</a></li>
		                <li><a href="" onclick="alert('teste')">Cadastro Atividade</a></li>
		                <li><a href="">Cadastro Etapa</a></li>
		                <li><a href="">Cadastro Grupo de Material</a></li>
		                <li><a href="">Cadastro Material</a></li>
		            </ul>
		        </li>
		    </ul>
		</nav>
		
		<div class="container">
		    <nav class="navbar navbar-default">
		        <div class="container-fluid">
		            <div class="row">
						<div class="text-center">
					  		<h1>${nome}</h1>
					  		<p>Gerenciamento de fabricação de Barcos</p>
						</div>
	            		<button type="button" id="sidebarCollapse" class="navbar-btn">
		                    <span></span>
		                    <span></span>
		                    <span></span>
		                </button>
		            </div>
		        </div>
		    </nav>
