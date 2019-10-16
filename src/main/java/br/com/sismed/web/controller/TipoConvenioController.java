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
import br.com.sismed.domain.TipoConvenio;
import br.com.sismed.service.ConvenioService;
import br.com.sismed.service.TipoConvenioService;

@Controller
@RequestMapping("/tipo_convenio")
public class TipoConvenioController {

	@Autowired
	private TipoConvenioService service;
	
	@Autowired
	private ConvenioService convenioService;
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("tipo_convenio", service.BuscarTodos());
		return "/tipo_convenio/lista";
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(TipoConvenio tipo_convenio) {
		
		return "/tipo_convenio/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(TipoConvenio tipo_convenio, RedirectAttributes attr) {
		service.salvar(tipo_convenio);
		attr.addFlashAttribute("success","Convenio cadastrado com sucesso");
		return  "redirect:/tipo_convenio/cadastrar";
	}
	
	
	
	@ModelAttribute("convenios")
	public List<Convenio> listaConvenios(){
		return convenioService.BuscarTodos();
	}
}
