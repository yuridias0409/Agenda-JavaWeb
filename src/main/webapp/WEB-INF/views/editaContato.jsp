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
<center><h3>O contato ${param.nome} foi editado com sucesso!</h3><center>
</c:if>
<br>
<div class="container">
	<form method=POST action=editaContatos?userid=${contato.id}>
	  <center><h2>Editar Contato</h2></center>
	  <div class="form-group">
	  	<center><label>Nome</label></center>
	    <input type="text" class="form-control" id="nome" aria-describedby="emailHelp" placeholder="Nome" name="nome" value=${contato.nome} required>
	  </div>
	  <div class="form-group">
	    <center><label>Email</label></center>
	    <input type="email" class="form-control" id="email" placeholder="Email" name="email" value=${contato.email} required>
	  </div>
	  <div class="form-group">
	    <center><label>Endereco</label></center>
	    <input type="text" class="form-control" id="endereco" placeholder="Endereco" name="endereco" value=${contato.endereco} required>
	  </div>
	  <div class="form-group">
	    <center><label>Telefone</label></center>
	    <input type="text" class="form-control" id="telefone" placeholder="Telefone" name="telefone" value=${contato.telefone} required>
	  </div>
	  <center><button type="submit" class="btn btn-outline-primary" value="Salvar">Salvar</button><center>
	</form>
</div>
<br>
</body>
</html>