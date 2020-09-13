package org.springmvc.agenda.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springmvc.agenda.DAO.ContatoRepository;
import org.springmvc.agenda.DAO.UsuarioRepository;
import org.springmvc.agenda.model.Contatos;
import org.springmvc.agenda.model.Users;

@Controller
public class ContatosController {
	
	private ContatoRepository contatoDAO;
	private UsuarioRepository userRep;
	
	@Autowired
	public ContatosController(ContatoRepository contatoDAO, UsuarioRepository userRep){
		this.contatoDAO = contatoDAO;
		this.userRep = userRep;
	}
		
	@RequestMapping("novoContatos")
	public String formContatos(HttpSession sessao) {
		if(sessao.getAttribute("userid") != null) {
			return "gravaContato";
		}	else {
			return "redirect:login";
		}
	}
	
	@Transactional
	@RequestMapping("salvarContatos")
	public String salvar(Contatos contato, HttpSession sessao) {
		if(sessao.getAttribute("userid") != null) {
			Users user = userRep.read((int) sessao.getAttribute("userid"));
			contato.setUser(user);
			contatoDAO.create(contato);
			return "redirect:listarContatos";
		}	else {
			return "redirect:login";
		}
	}
	
	@Transactional
	@RequestMapping("listarContatos")
	public String lista(Model model, HttpSession sessao) {
		if(sessao.getAttribute("userid") != null) {
			List<Contatos> contatos = contatoDAO.readAll((int) sessao.getAttribute("userid"));
			model.addAttribute("contatos", contatos);
			return "contatosList";
		}	else {
			return "redirect:login";
		}
	}
	
	@Transactional
	@RequestMapping("removerContatos")
	public String removerContatos(@RequestParam(value = "id", required = true) int id, HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			contatoDAO.delete(id);
			return "redirect:listarContatos";
		}	else {
			return "redirect:login";
		}
	}
	
	@Transactional
	@RequestMapping("attContatos")
	public String attContatos(@RequestParam(value = "id", required = true) int id, HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			model.addAttribute("contato", contatoDAO.read(id));
			return "editaContato";
		}	else {
			return "redirect:login";
		}
	}
	
	@Transactional
	@RequestMapping("editaContato")
	public String editaContato(@RequestParam(value = "cid", required = true) int id, HttpSession sessao, Contatos contato) {
		if(sessao.getAttribute("userid") != null) {
			Users user = userRep.read(id);
			contato.setUser(user);
			contatoDAO.update(contato);
			return "redirect:listarContatos";
		}	else {
			return "redirect:login";
		}
		
	}
	
	@Transactional
	@RequestMapping("filtrarNome")
	public String filtrarName(HttpServletRequest request, HttpServletResponse response, HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			String nome = request.getParameter("name");	
			model.addAttribute("contatos", contatoDAO.readForName(nome, (int) sessao.getAttribute("userid")));
			return "contatosList";
		}	else {
			return "redirect:login";
		}
		
	}
}
