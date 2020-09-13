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
		<form method=post action=editaCompromisso?cid=${compromissos.id}>
			<center>
				<h2>Edita Compromisso</h2>
			</center>
			<br>
			<div class="form-group">
				<center>
					<label>Local</label>
				</center>
				<input type="text" class="form-control" id="local"
					placeholder="Local" name="local" value=${compromissos.local
					} required>
			</div>
			<div class="form-group">
				<center>
					<label>Data</label>
				</center>
				<input type="date" class="form-control" id="local"
					placeholder="Data" name="data" value=${compromissos.data } required>
			</div>
			<div class="form-group">
				<center>
					<label>Contatos</label>
				</center>
				<select name=contatoid class="form-control">
					<c:forEach var="contatos" items="${contatos}">
						<option value="${contatos.id}" id="${contatos.id}">${contatos.name}</option>
					</c:forEach>
				</select>
				<script>
			document.getElementById("${compromissos.contato.getId()}").selected = true;
		</script>
			</div>
			<div class="form-group">
				<center>
					<label>Descrição</label>
				</center>
				<input type="text" class="form-control" id="descricao"
					placeholder="Descrição" name="descricao"
					value=${compromissos.descricao } required>
			</div>
			<center>
				<button type="submit" class="btn btn-outline-primary" value="Editar">Editar</button>
				<center>
		</form>
	</div>
<br>
</body>
</html>