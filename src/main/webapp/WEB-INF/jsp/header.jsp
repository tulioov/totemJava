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
	<title>Okean</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../js/home/homeController.js"></script>
	
	<div class="wrapper">
		<nav id="sidebar" class="bg-azul texto" style="z-index: 9999;">
		    <div class="sidebar-heade bg-azul texto">
		        <h3>Menu</h3>
		    </div>
		
		    <ul class="list-unstyled components">
		    	<li>
		            <a href="#">Menu</a>
		        </li>
		        <li class="active">
		            <a href="#homeSubmenu" class="bg-azul texto" data-toggle="collapse" aria-expanded="false">Adm</a>
		            <ul class="collapse list-unstyled bg-azul texto" id="homeSubmenu">
		                <li><a class="bg-azul texto" href="/cadastroUsuario" onclick="CadastroUsuarioController.listar();" >Cadastro Usuario</a></li>
		                <li><a class="bg-azul texto" href="" onclick="alert('teste')">Cadastro Atividade</a></li>
		                <li><a class="bg-azul texto" href="">Cadastro Etapa</a></li>
		                <li><a class="bg-azul texto" href="">Cadastro Grupo de Material</a></li>
		                <li><a class="bg-azul texto" href="">Cadastro Material</a></li>
		            </ul>
		        </li>
		    </ul>
		</nav>
		
		<div class="container">
		    <nav class="row navbar navbar-default bg-azul">
		        <div class="container-fluid titulo" >
		            <div class="row">
		            	<div class="col-md-4">
		            		<a href="/monitoramento"><img src="../img/logonOkean.jpg" alt="logonOkean"></a>
		            	</div>
		            	<div class="col-md-4">
							<div class="text-center texto">
								<h1>${nome}</h1>
								<p class="textoGrifado">Gerenciamento de fabricação de Barcos</p>
							</div>
						</div>
						<div class="col-md-4">
						</div>
						<div class="col-md-12">
		            		<button type="button" id="sidebarCollapse" class="navbar-btn bg-azul">
			                    <span class="bg-laranja"></span>
			                    <span class="bg-laranja"></span>
			                    <span class="bg-laranja"></span>
			                </button>
		                </div>
		            </div>
		        </div>
		    </nav>
	    </div>
    </div>
