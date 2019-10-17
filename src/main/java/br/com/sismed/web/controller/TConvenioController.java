package br.com.sismed.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sismed.domain.Convenio;
import br.com.sismed.domain.TConvenio;
import br.com.sismed.service.ConvenioService;
import br.com.sismed.service.TConvenioService;

@Controller
@RequestMapping("/tconvenios")
public class TConvenioController {

	@Autowired	
	private ConvenioService service;
	
	@Autowired	
	private TConvenioService tservice;
	
	
	@GetMapping("/listar") // segunda parte do href
	public String listar(ModelMap model) {
		model.addAttribute("tconvenio", tservice.BuscarTodos());
		return "/tconvenio/lista"; // retorna o caminho do arquivo
	}
	
	@GetMapping("/cadastrar") // segunda parte do href
	public String cadastrar (TConvenio tconvenio) {
		return "/tconvenio/cadastro"; // retorna o caminho do arquivo
	
	}
	
	@PostMapping("/salvar")
	public String salvar(TConvenio tconvenio, RedirectAttributes attr) {
		tservice.salvar(tconvenio);
		attr.addFlashAttribute("success","Convenio cadastrado com sucesso");
		return  "redirect:/tconvenios/cadastrar";
	}


@ModelAttribute("convenios")
public List<Convenio> listConvenio() {
	return service.BuscarTodos();
}
}