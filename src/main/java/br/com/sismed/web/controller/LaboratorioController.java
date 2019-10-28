package br.com.sismed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sismed.domain.Laboratorio;
import br.com.sismed.service.LaboratorioService;

@Controller
@RequestMapping("/laboratorio")
public class LaboratorioController {
	
	@Autowired
	private LaboratorioService service;
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("laboratorio", service.buscarTodos());
		return "/laboratorio/lista"; 
	}
	
	@GetMapping("/cadastrar") 
	public String cadastrar(Laboratorio laboratorio) {
		return "/laboratorio/cadastro"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(Laboratorio laboratorio, RedirectAttributes attr) {
		attr.addFlashAttribute("success","Laboratório cadastrado com sucesso");
		service.salvar(laboratorio);
		return "redirect:/laboratorio/listar";
	}
	
	@GetMapping("/editar/{id}") 
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("laboratorio", service.buscarporId(id));
		return "/laboratorio/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Laboratorio laboratorio, RedirectAttributes attr) {
		attr.addFlashAttribute("success","Laboratório alterado com sucesso");
		service.editar(laboratorio);
		return "redirect:/laboratorio/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("success", "Laboratorio excluido com sucesso");
		service.excluir(id);
		
		return listar(model);
	}
}
