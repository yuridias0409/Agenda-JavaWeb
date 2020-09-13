package org.springmvc.agenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmvc.agenda.DAO.ContatoRepository;
import org.springmvc.agenda.DAO.UsuarioRepository;
import org.springmvc.agenda.DAO.CompromissoRepository;
import org.springmvc.agenda.model.Compromissos;
import org.springmvc.agenda.model.Contatos;
import org.springmvc.agenda.model.Users;

@Controller
public class CompromissoController {
	
	private ContatoRepository contatodao;
	private CompromissoRepository compromissoDao;
	private UsuarioRepository userRep;
	
	@Autowired
	public CompromissoController(ContatoRepository contatodao, CompromissoRepository compromissoDao, UsuarioRepository userRep){
		this.compromissoDao = compromissoDao;
		this.contatodao = contatodao;
		this.userRep = userRep;
	}
	
	@Transactional
	@RequestMapping("novoCompromisso")
	public String formCompromisso(HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			model.addAttribute("contatos", contatodao.readAll((int) sessao.getAttribute("userid")));
			return "formCompromisso";
		}	else {
			return "redirect:login";
		}
	}
	
	@Transactional
	@RequestMapping("agendaCompromissos")
	public String agendaCompromissos(Compromissos compromisso, HttpSession sessao, Model model, HttpServletRequest request) {
		if(sessao.getAttribute("userid") != null) {
			int id = Integer.parseInt(request.getParameter("contatoid"));
			Contatos contato = contatodao.read(id);
			compromisso.setContato(contato);
			Users user = userRep.read((int) sessao.getAttribute("userid"));
			compromisso.setUser(user);
			compromissoDao.create(compromisso);
			return "redirect:listarCompromisso";
		}	else {
			return "redirect:login";
		}
	}
	
	@Transactional
	@RequestMapping("listarCompromisso")
	public String listarCompromissos(Compromissos compromisso, HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			List<Compromissos> compromissoList = new ArrayList<Compromissos>();
			List<String> contatosNomes = new ArrayList<String>();
			compromissoList.addAll(compromissoDao.readAll((int) sessao.getAttribute("userid")));
			for(Compromissos comp : compromissoList) {
				System.out.println(comp.getContato().toString());
				contatosNomes.add(comp.getContato().getName());
			}
			model.addAttribute("contatosNomes", contatosNomes);
			model.addAttribute("compromissos", compromissoList);
			return "compromissosList";
		}	else {
			return "redirect:login";
		}
	}
	
	@Transactional
	@RequestMapping("removerCompromissos")
	public String removerContatos(@RequestParam(value = "id", required = true) int id, HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			compromissoDao.delete(id);
			return "redirect:listarCompromisso";
		}	else {
			return "redirect:login";
		}
	}
	
	@Transactional
	@RequestMapping("attCompromissos")
	public String attContatos(@RequestParam(value = "id", required = true) int id, HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			model.addAttribute("contatos", contatodao.readAll((int) sessao.getAttribute("userid")));
			model.addAttribute("compromissos", compromissoDao.read(id));
			return "editaCompromisso";
		}	else {
			return "redirect:login";
		}
	}
	
	@Transactional
	@RequestMapping("editaCompromisso")
	public String editaCompro(@RequestParam(value = "cid", required = true) int id, HttpSession sessao, Compromissos compromisso, HttpServletRequest request) {
		if(sessao.getAttribute("userid") != null) {
			Contatos contato = contatodao.read(Integer.parseInt(request.getParameter("contatoid")));
			compromisso.setContato(contato);
			Users user = userRep.read((int) sessao.getAttribute("userid"));
			compromisso.setUser(user);
			compromissoDao.update(compromisso, id);
			return "redirect:listarCompromisso";
		}	else {
			return "redirect:login";
		}
		
	}
	
}
