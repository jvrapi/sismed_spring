package br.com.sismed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sismed.service.ProcedimentoService;

@Controller
@RequestMapping("/procedimentos")
public class ProcedimentosController {
	
	@Autowired
	private ProcedimentoService service;
	
	@GetMapping("/cadastrar")
	public String Cadastrar() {
		
		return "/procedimentos/cadastro";
	}
	
	@GetMapping("/listar")
	public String agendamentos(ModelMap model) {
		model.addAttribute("procedimentos", service.findAll());
		return "/procedimentos/lista";
	}
}
