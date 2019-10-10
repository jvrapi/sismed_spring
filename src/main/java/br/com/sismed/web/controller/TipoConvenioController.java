package br.com.sismed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tipo_convenios")
@Controller
public class TipoConvenioController {

	@GetMapping("/listar") // segunda parte do href
	public String listar() {
		return "/tipo_convenio/lista"; // retorna o caminho do arquivo
	}
	
	@GetMapping("/cadastros") // segunda parte do href
	public String cadastrar () {
		return "/tipo_convenio/cadastro"; // retorna o caminho do arquivo
	
	}
}
