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
<div class="container">
	<form>
	  <div class="form-group">
	  	<center><label>Usuário</label></center>
	    <input type="email" class="form-control" style="width: 25%;" id="email" aria-describedby="emailHelp" placeholder="Email" name="login">
	  </div>
	  <div class="form-group">
	    <center><label>Senha</label></center>
	    <input type="password" style="width: 25%;" class="form-control" id="senha" placeholder="Senha" name="senha">
	  </div>
	  <button type="submit" class="btn btn-primary" value="Logar">Logar</button>
	</form>
</div>
</body>
</html>