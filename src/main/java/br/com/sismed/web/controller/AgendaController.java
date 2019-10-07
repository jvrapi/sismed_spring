package br.com.sismed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agendamentos")
public class AgendaController {

	@GetMapping("/agendar")
	public String agendar() {
		return "/agenda/agendar";
	}
	
	@GetMapping("/agendamentos")
	public String agendamentos() {
		return "/agenda/agendamentos";
	}
}
