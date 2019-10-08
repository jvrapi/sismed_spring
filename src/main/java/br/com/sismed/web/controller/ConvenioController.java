package br.com.sismed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/convenios") // primeira parte do href 
public class ConvenioController {
	
		@GetMapping("/listar") // segunda parte do href
		public String listar() {
			return "/convenio/lista"; // retorna o caminho do arquivo
		}
		
		@GetMapping("/cadastros") // segunda parte do href
		public String cadastrar () {
			return "/convenio/cadastro"; // retorna o caminho do arquivo
		
		}
		
}
