package br.com.sismed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/procedimentos")
public class ProcedimentosController {
	@GetMapping("/cadastrar")
	public String Cadastrar() {
		return "/procedimentos/cadastro";
	}
	
	@GetMapping("/listar")
	public String agendamentos() {
		return "/procedimentos/lista";
	}
}
