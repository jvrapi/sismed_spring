package br.com.sismed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

	@GetMapping("/listar")
	public String listar() {
		return "/funcionario/listar"; 
	}
	
	@GetMapping("/cadastrar") 
	public String cadastrar() {
		return "/funcionario/cadastrar"; 
	
	}
}
