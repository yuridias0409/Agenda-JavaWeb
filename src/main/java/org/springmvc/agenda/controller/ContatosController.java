package org.springmvc.agenda.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springmvc.agenda.DAO.ContatoDAO;
import org.springmvc.agenda.model.Contato;

@Controller
public class ContatosController {
		
	@RequestMapping("novoContato")
	public String formContato(HttpSession sessao) {
		if(sessao.getAttribute("userid") != null) {
			return "gravaContato";
		}	else {
			return "redirect:login";
		}
	}
	
	@RequestMapping("salvarContato")
	public String salvar(Contato contato, HttpSession sessao) {
		if(sessao.getAttribute("userid") != null) {
			ContatoDAO contatoDAO = new ContatoDAO();	
			contatoDAO.create(contato, (int) sessao.getAttribute("userid"));
			return "redirect:listarContatos";
		}	else {
			return "redirect:login";
		}
	}
	
	@RequestMapping("listarContatos")
	public String lista(Model model, HttpSession sessao) {
		if(sessao.getAttribute("userid") != null) {
			ContatoDAO dao = new ContatoDAO();
			List<Contato> contatos = dao.readAll((int) sessao.getAttribute("userid"));
			model.addAttribute("contatos", contatos);
			return "contatosList";
		}	else {
			return "redirect:login";
		}
	}
	
	@RequestMapping("removerContato")
	public String removerContato(@RequestParam(value = "id", required = true) int id, HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			ContatoDAO dao = new ContatoDAO();
			dao.delete(id);
			return "redirect:listarContatos";
		}	else {
			return "redirect:login";
		}
	}
	
	
	@RequestMapping("attContato")
	public String attContato(@RequestParam(value = "id", required = true) int id, HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			ContatoDAO dao = new ContatoDAO();
			model.addAttribute("contato", dao.read(id));
			return "editaContato";
		}	else {
			return "redirect:login";
		}
	}
	
	@RequestMapping("editaContatos")
	public String editaContato(@RequestParam(value = "userid", required = true) int id, HttpSession sessao, Contato contato) {
		if(sessao.getAttribute("userid") != null) {
			ContatoDAO dao = new ContatoDAO();
			dao.update(contato, id);
			return "redirect:listarContatos";
		}	else {
			return "redirect:login";
		}
		
	}
	
	@RequestMapping("filtrarNome")
	public String filtrarNome(HttpServletRequest request, HttpServletResponse response, HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			String nome = request.getParameter("name");	
			ContatoDAO dao = new ContatoDAO();
			model.addAttribute("contatos", dao.readForName(nome, (int) sessao.getAttribute("userid")));
			return "contatosList";
		}	else {
			return "redirect:login";
		}
		
	}
}
