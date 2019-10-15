package br.com.sismed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sismed.service.TipoConvenioService;

@Controller
@RequestMapping("/tiposconvenio")
public class TipoConvenioController {

	@Autowired
	private TipoConvenioService service;
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("tiposconvenio", service.findAll());
		return "/tipo_convenio/lista";
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar() {
		return "/tipo_convenio/cadastro";
	}
}
