<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="utils.jsp" %>
<title>Login</title>
</head>
<body>
<%@ include file="loginNavbar.jsp" %>
<br>

<div class="container">
	<form method=post action=registraUsuario>
	  <center><h2>Registrar-se</h2></center>
	  <br>
	  <div class="form-group">
	  	<center><label>Nome</label></center>
	    <input type="text" class="form-control" id="nome" placeholder="Nome" name="nome" required>
	  </div>
	  <div class="form-group">
	  	<center><label>Email</label></center>
	    <input type="email" class="form-control" id="email" placeholder="Email" name="email" required>
	  </div>
	  <div class="form-group">
	    <center><label>Senha</label></center>
	    <input type="password" class="form-control" id="senha" placeholder="Senha" name="senha" required>
	  </div>
	  <center><small id="emailHelp" class="form-text text-muted"><a href="login">Logar</a></small></center>
	  <br>
	  <center><button type="submit" class="btn btn-outline-primary" value="Logar">Registrar-se</button></center>
	</form>
</div>
</body>
</html>