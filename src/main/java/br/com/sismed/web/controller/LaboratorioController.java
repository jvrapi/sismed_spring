	package br.com.sismed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sismed.domain.Laboratorio;
import br.com.sismed.service.LaboratorioService;
import groovyjarjarpicocli.CommandLine.Model;

@Controller
@RequestMapping("/laboratorio")
public class LaboratorioController {
	
	@Autowired
	private LaboratorioService service;
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("laboratorio", service.buscarTodos());
		return "/laboratorio/lista"; 
	}
	
	@GetMapping("/cadastrar") 
	public String cadastrar(Laboratorio laboratorio) {
		return "/laboratorio/cadastro"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(Laboratorio laboratorio) {
		service.salvar(laboratorio);
		return "redirect:/laboratorio/cadastro";
	}
	@PostMapping("/editar")
	public String editar(Laboratorio laboratorio) {
		service.editar(laboratorio);
		return "redirect:/laboratorio/cadastrar";
	}
}
