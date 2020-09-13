<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="utils.jsp" %>
<title>Lista de Contatos</title>
</head>
<body>
<%@ include file="userNavBar.jsp" %>

<form method=POST action=filtrarNome>
	<div class="col-lg-6">
		<div class="row">
			<input class="form-control" style="width: 25%" id="filtroNome" placeholder="Filtrar Nome" name="name">
			<button type="submit" class="btn btn-outline-primary" value="Adicionar">Filtrar</button>
		</div>
	</div>
</form>

<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Nome</th>
      <th scope="col">Email</th>
      <th scope="col">Endereco</th>
      <th scope="col">Telefone</th>
      <th scope="col">Ação</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="contatos" items="${contatos}">
    <tr>
      <td scope="row">${contatos.id}</td>
      <td>${contatos.name}</td>
	  <td>${contatos.email}</td>
	  <td>${contatos.endereco}</td>
	  <td>${contatos.telefone}</td>
	  <td><a class="btn btn-outline-primary" href=attContatos?id=${contatos.id}>Atualizar</a><a class="btn btn-outline-danger" href=removerContatos?id=${contatos.id}>Remover</a></td>
    </tr>
  </c:forEach>  
  </tbody>
</table>
</body>
</html>