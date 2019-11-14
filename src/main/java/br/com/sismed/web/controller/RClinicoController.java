package br.com.sismed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sismed.domain.RegistroClinico;
import br.com.sismed.service.AgendaService;
import br.com.sismed.service.RegistroClinicoService;

@Controller
@RequestMapping("/RegistroClinico")
public class RClinicoController {
	
	@Autowired
	private RegistroClinicoService service;
	
	@Autowired
	private AgendaService agendaService;

	@GetMapping("/listar")
	public String RClinico() {
		return "/registro_clinico/lista";
	}
	
	@GetMapping("/cadastrar/{id}")
	public String cadastrar(@PathVariable("id") Long id, ModelMap model, @ModelAttribute("registroclinico") RegistroClinico registroclinico) {
		model.addAttribute("agenda", agendaService.buscarPorId(id));
		return "/registro_clinico/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(RegistroClinico registroclinico) {
		service.salvar(registroclinico);
		return "redirect:/pacientes/listar";
	}
}

