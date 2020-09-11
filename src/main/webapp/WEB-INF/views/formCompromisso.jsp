<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="utils.jsp" %>
<title>Novo Compromisso</title>
</head>
<body>
<%@ include file="userNavBar.jsp" %>	
<br>
<div class="container">
	<form method=post action=agendaCompromisso>
	  <center><h2>Novo Compromisso</h2></center>
	  <br>
	  <div class="form-group">
	  	<center><label>Local</label></center>
	    <input type="text" class="form-control" id="local" placeholder="Local" name="local" required>
	  </div>
	  <div class="form-group">
	  	<center><label>Data</label></center>
	    <input type="date" class="form-control" id="local" placeholder="Data" name="data" required>
	  </div>
	  <div class="form-group">
		<center><label>Contato</label></center>
		<select name=contatoID class="form-control">
		  <c:forEach var="contatos" items="${contatos}">
			<option value="${contatos.id}">${contatos.nome}</option>
		  </c:forEach> 
		</select>
	  </div>
	  <div class="form-group">
	    <center><label>Descrição</label></center>
	    <input type="text" class="form-control" id="descricao" placeholder="Descrição" name="descricao" required>
	  </div>
	  <center><button type="submit" class="btn btn-outline-primary" value="Agendar">Agendar</button><center>
	</form>
</div>
<br>
</body>
</html>