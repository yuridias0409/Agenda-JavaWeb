<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="utils.jsp" %>
<title>Gravar Contato</title>
</head>
<body>
<%@ include file="userNavBar.jsp" %>
<c:if test="${not empty param.nome}">
<center><h3>O contato ${param.nome} foi adicionado com sucesso!</h3><center>
</c:if>
<div class="container">
	<form method=post action=salvarContato>
	  <div class="form-group">
	  	<center><label>Nome</label></center>
	    <input type="text" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Nome" name="nome">
	  </div>
	  <div class="form-group">
	    <center><label>Email</label></center>
	    <input type="email" class="form-control" id="senha" placeholder="Email" name="email">
	  </div>
	  <center><button type="submit" class="btn btn-primary" value="Adicionar">Salvar</button><center>
	</form>
</div>
<br>
</body>
</html>