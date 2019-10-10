package br.com.sismed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pacientes")
public class PacientesController {

	@GetMapping("")
	public String listar() {
		return "/pacientes/lista";
	}

	@GetMapping("/cadastrar")
	public String cadastrar() {
		return "/pacientes/cadastro";
	}
}
