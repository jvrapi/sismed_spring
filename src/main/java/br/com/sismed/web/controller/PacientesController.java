package br.com.sismed.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sismed.domain.Convenio;
import br.com.sismed.domain.Paciente;
import br.com.sismed.domain.TipoConvenio;
import br.com.sismed.service.ConvenioService;
import br.com.sismed.service.PacienteService;
import br.com.sismed.service.TipoConvenioService;

@Controller
@RequestMapping("/pacientes")
public class PacientesController {
	
	@Autowired
	private PacienteService service;
	@Autowired
	private ConvenioService convenioService;
	@Autowired
	private TipoConvenioService tipoConvenioService;
	
	@GetMapping("/listar")
	public String listar() {
		return "/pacientes/lista";
	}

	@GetMapping("/cadastrar")
	public String cadastrar(Paciente paciente) {
		return "/pacientes/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(Paciente paciente) {
		service.salvar(paciente);
		return "redirect:/pacientes/cadastrar";
	}
	
	@ModelAttribute("convenio")
		public List<Convenio> listConvenio() {
			return convenioService.BuscarTodos();
	}
	
	@ModelAttribute("tipoconvenio")
	public List<TipoConvenio> listTipoConvenio() {
		return tipoConvenioService.findAll();
	}
}


