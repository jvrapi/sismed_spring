package br.com.sismed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exame")
public class ExameController {

	@GetMapping("/listar")
	public String listar() {
		return "/exame/lista"; 
	}
	
	@GetMapping("/cadastrar") 
	public String anexo () {
		return "/exame/cadastrar"; 
	}
	
	/*@GetMapping("/pesquisar") 
	public String pesquisar() {
		return "/exame/pesquisar"; 
	}*/
}

