<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="utils.jsp" %>
<title>Edita Compromisso</title>
</head>
<body>
<%@ include file="userNavBar.jsp" %>	
<br>
<div class="container">
	<form method=post action=editaCompro?cid=${compromissos.id}>
	  <center><h2>Edita Compromisso</h2></center>
	  <br>
	  <div class="form-group">
	  	<center><label>Local</label></center>
	    <input type="text" class="form-control" id="local" placeholder="Local" name="local" value=${compromissos.local} required>
	  </div>
	  <div class="form-group">
	  	<center><label>Data</label></center>
	    <input type="date" class="form-control" id="local" placeholder="Data" name="data" value=${compromissos.data} required>
	  </div>
	  <div class="form-group">
		<center><label>Contato</label></center>
		<select name=contatoID class="form-control">
		  <c:forEach var="contatos" items="${contatos}">
			<option value="${contatos.id}" id="${contatos.id}">${contatos.nome}</option>
		  </c:forEach> 
		</select>
		<script>
			document.getElementById("${compromissos.contatoID}").selected = true;
		</script>
	  </div>
	  <div class="form-group">
	    <center><label>Descri��o</label></center>
	    <input type="text" class="form-control" id="descricao" placeholder="Descri��o" name="descricao" value=${compromissos.descricao} required>
	  </div>
	  <center><button type="submit" class="btn btn-outline-primary" value="Editar">Editar</button><center>
	</form>
</div>
<br>
</body>
</html>