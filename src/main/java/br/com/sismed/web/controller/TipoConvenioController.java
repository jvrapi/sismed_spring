package br.com.sismed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sismed.service.TipoConvenioService;

@RequestMapping("/tipo_convenios")
@Controller
public class TipoConvenioController {
	
	@Autowired
	private TipoConvenioService tservice;

	@GetMapping("/listar") // segunda parte do href
	public String listar(ModelMap model) {
		model.addAttribute("tipoconvenio", tservice.BuscarTodos());
		return "/tipo_convenio/lista"; // retorna o caminho do arquivo
	}
	
	@GetMapping("/cadastrar") // segunda parte do href
	public String cadastrar () {
		return "/tipo_convenio/cadastro"; // retorna o caminho do arquivo
	
	}
}
