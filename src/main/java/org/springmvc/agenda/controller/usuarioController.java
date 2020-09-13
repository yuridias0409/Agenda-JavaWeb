package org.springmvc.agenda.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springmvc.agenda.DAO.UsuarioRepository;
import org.springmvc.agenda.model.Users;

@Controller
public class usuarioController {		
	
	private UsuarioRepository dao;
	
	@Autowired
	public usuarioController(UsuarioRepository dao) {
		this.dao = dao;
	}
	
	@Transactional
	@RequestMapping("registraUsers")
	public String registraUsers(Users usuario) {
		if(!dao.verificaCadastrado(usuario.getEmail())) {
			dao.create(usuario);
			return "login";
		}	else {
			return "cadastro";
		}
	}
	
	@Transactional
	@RequestMapping("logaUsers")
	public String logaUsers(HttpServletRequest request, HttpServletResponse response, HttpSession sessao) {
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		if(dao.verificaLogin(email, senha)) {
			sessao.setAttribute("userid", dao.retornaID(email));
			return "redirect:listarCompromisso";
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
