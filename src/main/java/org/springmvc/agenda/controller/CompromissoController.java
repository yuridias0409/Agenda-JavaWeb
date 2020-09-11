package org.springmvc.agenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmvc.agenda.DAO.CompromissoDAO;
import org.springmvc.agenda.DAO.ContatoDAO;
import org.springmvc.agenda.model.Compromisso;

@Controller
public class CompromissoController {
	
	@RequestMapping("novoCompromisso")
	public String formCompromisso(HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			ContatoDAO contatodao = new ContatoDAO();
			model.addAttribute("contatos", contatodao.readAll((int) sessao.getAttribute("userid")));
			return "formCompromisso";
		}	else {
			return "redirect:login";
		}
	}
	
	@RequestMapping("agendaCompromisso")
	public String agendaCompromisso(Compromisso compromisso, HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			CompromissoDAO cdao = new CompromissoDAO();
			cdao.create(compromisso, (int) sessao.getAttribute("userid"));
			return "redirect:listarCompromisso";
		}	else {
			return "redirect:login";
		}
	}
	
	@RequestMapping("listarCompromisso")
	public String listarCompromisso(Compromisso compromisso, HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			CompromissoDAO cdao = new CompromissoDAO();
			List<Compromisso> compromissoList = new ArrayList<Compromisso>();
			compromissoList.addAll(cdao.readAll((int) sessao.getAttribute("userid")));
			model.addAttribute("compromissos", compromissoList);
			return "compromissosList";
		}	else {
			return "redirect:login";
		}
	}
	
	@RequestMapping("removerCompromisso")
	public String removerContato(@RequestParam(value = "id", required = true) int id, HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			CompromissoDAO cdao = new CompromissoDAO();
			cdao.delete(id);
			return "redirect:listarCompromisso";
		}	else {
			return "redirect:login";
		}
	}
	
	@RequestMapping("attCompromisso")
	public String attContato(@RequestParam(value = "id", required = true) int id, HttpSession sessao, Model model) {
		if(sessao.getAttribute("userid") != null) {
			CompromissoDAO compromissoDAO = new CompromissoDAO();
			ContatoDAO contatodao = new ContatoDAO();
			model.addAttribute("contatos", contatodao.readAll((int) sessao.getAttribute("userid")));
			model.addAttribute("compromissos", compromissoDAO.read(id));
			return "editaCompromisso";
		}	else {
			return "redirect:login";
		}
	}
	
	@RequestMapping("editaCompro")
	public String editaCompro(@RequestParam(value = "cid", required = true) int id, HttpSession sessao, Compromisso compromisso) {
		if(sessao.getAttribute("userid") != null) {
			CompromissoDAO compromissoDAO = new CompromissoDAO();
			compromissoDAO.update(compromisso, id);
			return "redirect:listarCompromisso";
		}	else {
			return "redirect:login";
		}
		
	}
	
}
