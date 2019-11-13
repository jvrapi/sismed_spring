package br.com.sismed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sismed.domain.RegistroClinico;

@Controller
@RequestMapping("/RegistroClinico")
public class RClinicoController {

	@GetMapping("/listar")
	public String RClinico() {
		return "/registro_clinico/lista";
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(RegistroClinico registroclinico) {
		return "/registro_clinico/cadastro";
	}
}
