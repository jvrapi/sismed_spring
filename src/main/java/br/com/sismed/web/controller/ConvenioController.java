package br.com.sismed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sismed.domain.Convenio;
import br.com.sismed.service.ConvenioService;

@Controller
@RequestMapping("/convenios") // primeira parte do href 
public class ConvenioController {
	
	@Autowired	
	private ConvenioService service;
	
	
		@GetMapping("/listar") // segunda parte do href
		public String listar() {
			return "/convenio/lista"; // retorna o caminho do arquivo
		}
		
		@GetMapping("/cadastrar") // segunda parte do href
		public String cadastrar (Convenio convenio) {
			return "/convenio/cadastro"; // retorna o caminho do arquivo
		
		}
		
		
		@PostMapping("/salvar")
		public String salvar(Convenio convenio) {
			service.salvar(convenio);
			return  "redirect:/convenio/cadastro";
		}
}
