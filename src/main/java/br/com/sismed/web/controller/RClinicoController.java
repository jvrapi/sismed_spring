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
import br.com.sismed.service.PacienteService;
import br.com.sismed.service.RegistroClinicoService;

@Controller
@RequestMapping("/RegistroClinico")
public class RClinicoController {
	
	@Autowired
	private RegistroClinicoService service;
	
	@Autowired
	private AgendaService agendaService;
	
	@Autowired
	private PacienteService pacienteSercice;
	
	@GetMapping("/buscar")
	public String buscar() {
		return "/registro_clinico/busca";
	}

	@GetMapping("/listar/{id}/{dado}")
	public String listar(@PathVariable("id") Integer id, @PathVariable("dado") String dado, ModelMap model) {
		if(id == 1) {
			model.addAttribute("registro", service.ListarRegPacienteAgen(dado));
			return "/registro_clinico/lista";
		}
		else {
			model.addAttribute("registro", service.ListarRegPaciente(dado));
			return "/registro_clinico/lista";
		}
	}
	
	@GetMapping("/cadastrar/{id}")
	public String cadastrar(@PathVariable("id") Long id, ModelMap model, @ModelAttribute("registroclinico") RegistroClinico registroclinico) {
		model.addAttribute("agenda", agendaService.buscarPorId(id));
		return "/registro_clinico/cadastro";
	}
	
	@GetMapping("/cadastrarpac/{id}")
	public String cadastrarPac(@PathVariable("id") Long id, ModelMap model, @ModelAttribute("registroclinico") RegistroClinico registroclinico) {
		model.addAttribute("paciente", pacienteSercice.buscarporId(id));
		return "/registro_clinico/cadastropac";
	}
	
	@PostMapping("/salvar")
	public String salvar(RegistroClinico registroclinico) {
		service.salvar(registroclinico);
		return "redirect:/pacientes/listar";
	}
}

