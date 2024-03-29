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
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">		
		<link rel="stylesheet" href="../css/menu/menu.css">
		<link rel="stylesheet" href="../css/home/homeStyle.css">
	<title>Okean</title>
</head>
<body>
<script type="text/javascript">
	var nome = '${nome}';
	var email = '${email}';
	var isAdm = ${isAdm};
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../js/menu/menu.js"></script>

<div class='dashboard'>
    <div class="dashboard-nav bg-azul">
        <header>
        	<a href="#!" id="menuId" class="menu-toggle">
        		<i class="fas fa-bars"></i>
        	</a>
        	<a href="#" class="brand-logo">
        		<i class="fas fa-anchor"></i> 
        		<span>OKEAN</span>
        	</a>
		</header>
        <nav class="dashboard-nav-list">
        	<a href="/" class="dashboard-nav-item active">
        		<i class="fas fa-home"></i>
				Home 
			</a>
            <div class='dashboard-nav-dropdown'>
            	<a href="#!" class="dashboard-nav-item dashboard-nav-dropdown-toggle">
	            	<i class="fas fa-tachometer-alt"></i> 
					Monitoramento
				</a>
                <div class='dashboard-nav-dropdown-menu'>
	                <a href="/monitoramento" class="dashboard-nav-dropdown-item">Barco</a>
					<a href="controlTotens" class="dashboard-nav-dropdown-item">Toten</a>
				</div>
            </div>
            <div class='dashboard-nav-dropdown'>
            	<a href="#!" class="dashboard-nav-item dashboard-nav-dropdown-toggle">
	            	<i class="fas fa-file-upload"></i> 
					Importar
				</a>
                <div class='dashboard-nav-dropdown-menu'>
	                <a href="/importarUsuario" class="dashboard-nav-dropdown-item">Importar Usuarios</a>
	                <a href="/importarLocais" class="dashboard-nav-dropdown-item">Importar Locais</a>
	                <a href="/importarAtividade" class="dashboard-nav-dropdown-item">Importar Atividade</a>
				</div>
            </div>
            <div class='dashboard-nav-dropdown'>
            	<a href="#!" class="dashboard-nav-item dashboard-nav-dropdown-toggle">
	            	<i class="fas fa-tachometer-alt"></i> 
					Relat&oacute;rio 
				</a>
                <div class='dashboard-nav-dropdown-menu'>
	                <a href="/relatorioUsuario" class="dashboard-nav-dropdown-item">Relatorio Usuarios</a>
				</div>
            </div>
            <div class='dashboard-nav-dropdown'>
            	<a href="#!" class="dashboard-nav-item dashboard-nav-dropdown-toggle">
	            	<i class="fas fa-cogs"></i> 
					Cadastro
				</a>
                <div class='dashboard-nav-dropdown-menu'>
       				<a href="/cadastroStatusMonitoracao" class="dashboard-nav-dropdown-item">Menu Monitoracao</a>
					<a href="/cadastroFeriado" class="dashboard-nav-dropdown-item">Feriado</a>
					<a href="/cadastroBarco" class="dashboard-nav-dropdown-item">Barco</a>
					<a href="/cadastroUsuario" onclick="CadastroUsuarioController.listar();" class="dashboard-nav-dropdown-item">Usuario</a>
					<a href="/cadasdroFases" class="dashboard-nav-dropdown-item">Fase</a>
					<div class='dashboard-nav-dropdown'>
		            	<a href="#!" class="dashboard-nav-item dashboard-nav-dropdown-toggle">
			            	<i class="fas fa-coins"></i> 
							Grupo Local/Atividade
						</a>
		                <div class='dashboard-nav-dropdown-menu'>
							<a href="/cadastroAtividade" class="dashboard-nav-dropdown-item">Atividade</a>
							<a href="/cadastroLocal" class="dashboard-nav-dropdown-item">Local</a>
							<a href="/cadastroBarcoTemplate" class="dashboard-nav-dropdown-item">Barco Template</a>
							<a href="/cadastroEspecialidade" class="dashboard-nav-dropdown-item">Especialidade</a>
						</div>
		            </div>
		            
		            <div class='dashboard-nav-dropdown'>
		            	<a href="#!" class="dashboard-nav-item dashboard-nav-dropdown-toggle">
			            	<i class="fas fa-coins"></i> 
							Grupo Material
						</a>
		                <div class='dashboard-nav-dropdown-menu'>
							<a href="/cadastroMaterial" class="dashboard-nav-dropdown-item">Material</a>
							<a href="/cadastroItens" class="dashboard-nav-dropdown-item">Itens</a>
						</div>
		            </div>
				</div>
            </div>
            <div class="nav-item-divider"></div>
            <a href="/logout" class="dashboard-nav-item">
            	<i class="fas fa-sign-out-alt"></i> 
            	Sair 
			</a>
        </nav>
    </div>
    <div class='dashboard-app'>
        <header class='dashboard-toolbar bg-azul'>
        	<a id="headMenuId" href="#!" class="menu-toggle">
       			<i class="fas fa-bars laranja"></i>	
			</a>
			<div class="col-md-8">
          		<a href="/"><img src="../img/logonOkean.png" style="width: 167px; height: 44px;"
				alt="logonOkean"></a>
			</div>
		</header>
        <div class='dashboard-content' style="margin-top: 30px;">
