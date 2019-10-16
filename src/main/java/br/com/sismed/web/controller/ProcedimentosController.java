package br.com.sismed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sismed.domain.Convenio;
import br.com.sismed.domain.Procedimento;
import br.com.sismed.service.ProcedimentoService;

@Controller
@RequestMapping("/procedimentos")
public class ProcedimentosController {
	
	@Autowired
	private ProcedimentoService service;
	
	@GetMapping("/cadastrar")
	public String Cadastrar(Procedimento procedimentos) {
		
		return "/procedimentos/cadastro";
	}
	
	@GetMapping("/listar")
	public String agendamentos(ModelMap model) {
		model.addAttribute("procedimentos", service.BuscarTodos());
		return "/procedimentos/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Procedimento procedimento, RedirectAttributes attr) {
		service.salvar(procedimento);
		attr.addFlashAttribute("success","Convenio cadastrado com sucesso");
		return  "redirect:/convenios/cadastrar";
	}
}
