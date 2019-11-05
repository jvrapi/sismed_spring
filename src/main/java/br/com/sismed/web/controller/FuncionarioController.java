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
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionario", service.buscarTodos());
		return "/funcionario/lista"; 
	}
	
	@GetMapping("/cadastrar") 
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		attr.addFlashAttribute("success","Funcionario(a) cadastrado(a) com sucesso");
		service.salvar(funcionario);
		return "redirect:/funcionario/listar";
	}
	
	@GetMapping("/editar/{id}") 
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario", service.buscarporId(id));
		return "/funcionario/editar";
	}
	
	@PostMapping("/editar")
	public String editar(Funcionario funcionario, RedirectAttributes attr) {
		attr.addFlashAttribute("success","Funcionario(a) alterado(a) com sucesso");
		service.editar(funcionario);
		return "redirect:/funcionario/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("success", "Funcionario(a) excluído(a) com sucesso");
		service.excluir(id);
		
		return listar(model);
	}
	
}
