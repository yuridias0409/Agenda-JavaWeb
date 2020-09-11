<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="utils.jsp" %>
<title>Lista de Compromissos</title>
</head>
<body>
<%@ include file="userNavBar.jsp" %>

<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Local</th>
      <th scope="col">Data</th>
      <th scope="col">Contato</th>
      <th scope="col">Descrição</th>
      <th scope="col">Ação</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="compromissos" items="${compromissos}">
    <tr>
      <td scope="row">${compromissos.id}</td>
      <td>${compromissos.local}</td>
	  <td>${compromissos.data}</td>
	  <td>${compromissos.contatoNome}</td>
	  <td>${compromissos.descricao}</td>
	  <td><a class="btn btn-outline-primary" href=attCompromisso?id=${compromissos.id}>Atualizar</a><a class="btn btn-outline-danger" href=removerCompromisso?id=${compromissos.id}>Remover</a></td>
    </tr>
  </c:forEach>  
  </tbody>
</table>
</body>
</html>