package br.com.sismed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sismed.service.AgendaService;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

	@Autowired
	private AgendaService serivce;
	
	@GetMapping("/agendar")
	public String agendar() {
		
		return "/agenda/agendar";
	}
	
	@GetMapping("/agendamentos")
	public String agendamentos(ModelMap model) {
		model.addAttribute("agendamentos", serivce.ListarAgendamentos());
		return "/agenda/agendamentos";
	}
}
