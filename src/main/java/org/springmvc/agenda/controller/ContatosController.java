package org.springmvc.agenda.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springmvc.agenda.DAO.ContatoDAO;
import org.springmvc.agenda.model.Contato;

@Controller
public class ContatosController {
	
	@RequestMapping("novoContato")
	public String formContato() {
		return "gravaContato";
	}
	
	@RequestMapping("salvarContato")
	public String salvar(Contato contato) {
		ContatoDAO contatoDAO = new ContatoDAO();	
		contatoDAO.create(contato);
		return "gravaContato";
	}
	
	@RequestMapping("listarContatos")
	public ModelAndView lista() {
		ContatoDAO dao = new ContatoDAO();
		List<Contato> contatos = dao.readAll();
	
		ModelAndView mv = new ModelAndView("contatosList");
		mv.addObject("contatos", contatos);
		return mv;
	}
	
	@RequestMapping("removerContato")
	public ModelAndView removerContato(@RequestParam(value = "id", required = true) int id) {
		ContatoDAO dao = new ContatoDAO();
		dao.delete(id);
		
		List<Contato> contatos = dao.readAll();
	
		ModelAndView mv = new ModelAndView("contatosList");
		mv.addObject("contatos", contatos);
		return mv;
	}
}
