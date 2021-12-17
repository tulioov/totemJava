<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		                <li><a href="#">Cadastro Usuario</a></li>
		                <li><a href="#">Cadastro Atividade</a></li>
		                <li><a href="#">Cadastro Etapa</a></li>
		                <li><a href="#">Cadastro Grupo de Material</a></li>
		                <li><a href="#">Cadastro Material</a></li>
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
			<h2>Bem vindo NOME, Estamos ta etapa 5</h2>
			<div class="col-12">
				<button type="button" class="btn btn-primary btn-lg btn-block">Fibra</button>
				<button type="button" class="btn btn-primary btn-lg btn-block">Eletrica</button>
				<button type="button" class="btn btn-primary btn-lg btn-block">Etapa0</button>
				<button type="button" class="btn btn-primary btn-lg btn-block">Etapa1</button>
				<button type="button" class="btn btn-primary btn-lg btn-block">Etapa2</button>
				<button type="button" class="btn btn-primary btn-lg btn-block">Etapa3</button>
				<button type="button" class="btn btn-primary btn-lg btn-block">Etapa4</button>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="../js/home/homeController.js"></script>
</body>
</html>