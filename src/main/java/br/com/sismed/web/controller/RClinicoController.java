package br.com.sismed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registroclinico")
public class RClinicoController {

	@GetMapping("/")
	public String RClinico() {
		return "/registro_clinico/RegistroClinico";
	}
}
