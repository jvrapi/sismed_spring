package br.com.sismed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/laboratorio")
public class LaboratorioController {

	@GetMapping("/listar")
	public String listar() {
		return "/laboratorio/listar"; 
	}
	
	@GetMapping("/cadastrar") 
	public String cadastrar() {
		return "/laboratorio/cadastrar"; 
	}
}
