package br.com.sismed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/relatorios")
public class CustosController {
	@Controller
	@RequestMapping("/relatorios")
	public class RelatorioController {

		@GetMapping("/listar")
		public String listar() {
			return "/relatorio/lista"; 
		}
		
		
}
}
