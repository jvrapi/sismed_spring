package br.com.sismed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/convenios")
public class ConvenioController {
	
		@GetMapping("/lista")
		public String listar() {
			return "/convenio/lista";
		}
		
		@GetMapping("/cadastro")
		public String cadastrar () {
			return "/convenio/cadastro";
		}
		
}
