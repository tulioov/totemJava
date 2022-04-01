<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:th="http://thymeleaf.org"
	  xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <title>Spring Security Example </title>
    </head>
    <body>
    	<jsp:include page="./header.jsp"></jsp:include>
        <form id="formId">
            <div><label> User Name : <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <div>
            	<button type="button" onclick="LoginController.login();" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
					Login
				</button>
            </div>
        </form>
    	<script src="../externo/serializejson/serializejson.js"></script>
    	<script src="../js/login/loginController.js"></script>
    </body>
</html>