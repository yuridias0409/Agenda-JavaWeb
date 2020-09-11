package org.springmvc.agenda.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springmvc.agenda.DAO.UsuarioDAO;
import org.springmvc.agenda.model.Usuario;

@Controller
public class usuarioController {		
	@RequestMapping("registraUsuario")
	public String registraUsuario(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		if(!dao.verificaCadastrado(usuario.getEmail())) {
			dao.create(usuario);
			return "login";
		}	else {
			return "cadastro";
		}
	}
	
	@RequestMapping("logaUsuario")
	public String logaUsuario(HttpServletRequest request, HttpServletResponse response, HttpSession sessao) {
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");	
		UsuarioDAO dao = new UsuarioDAO();
		if(dao.verificaLogin(email, senha)) {
			sessao.setAttribute("userid", dao.retornaID(email));
			return "gravaContato";
		}	else {
			return "login";
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession sessao) {
		sessao.removeAttribute("userid");
		sessao.invalidate();
		return "login";
	}
	
	@RequestMapping("login")
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping("cadastro")
	public String cadastroForm() {
		return "cadastro";
	}

}
