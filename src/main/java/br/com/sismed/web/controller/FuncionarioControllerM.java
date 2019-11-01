package br.com.sismed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sismed.domain.Funcionario;
import br.com.sismed.domain.Laboratorio;
import br.com.sismed.service.FuncionarioService;

@Controller
@RequestMapping("/medico")
public class FuncionarioControllerM {

	@Autowired
	private FuncionarioService service;
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("medico", service.buscarTodos());
		return "/medico/lista"; 
	}
	
	@GetMapping("/cadastrar") 
	public String cadastrar(Funcionario funcionario) {
		return "/medico/cadastro_med";
	}
	
	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		attr.addFlashAttribute("success","Médico(a) cadastrado(a) com sucesso");
		service.salvar(funcionario);
		return "redirect:/medico/listar";
	}
	
	@GetMapping("/editar/{id}") 
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("medico", service.buscarporId(id));
		return "/medico/cadastro_med";
	}
	
	@PostMapping("/editar")
	public String editar(Funcionario funcionario, RedirectAttributes attr) {
		attr.addFlashAttribute("success","Médico(a) alterado(a) com sucesso");
		service.editar(funcionario);
		return "redirect:/medico/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("success", "Médico(a) excluído(a) com sucesso");
		service.excluir(id);
		
		return listar(model);
	}
	
}
