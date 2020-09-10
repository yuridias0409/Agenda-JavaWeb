package org.springmvc.agenda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springmvc.agenda.DAO.UsuarioDAO;
import org.springmvc.agenda.model.Usuario;

@Controller
public class AutenticadorController {
	
	@RequestMapping("login")
	public String loginForm() {
		return "login";
	}

}
