package br.com.sismed.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sismed.domain.Exame;
import br.com.sismed.domain.Funcionario;
import br.com.sismed.domain.Paciente;
import br.com.sismed.domain.TConvenio;
import br.com.sismed.service.ExameService;
import br.com.sismed.service.FuncionarioService;
import br.com.sismed.service.PacienteService;
import br.com.sismed.service.TConvenioService;

@Controller
@RequestMapping("/exame")
public class ExameController {

	@Autowired
	private ExameService service;
		
	@Autowired
	public PacienteService pservice;
		
	@Autowired
	public TConvenioService tservice;
	
	@Autowired
	public FuncionarioService fservice;
	
	@GetMapping("/listar")
	
		public String listar(ModelMap model) {
		model.addAttribute("exame",service.buscarTodos());
		 return "/exame/lista"; 
	}
		
	@GetMapping("/cadastrar") 
	public String cadastrar (ModelMap model, Exame exame) {
		model.addAttribute("paciente",pservice.buscarTodos());
		model.addAttribute("funcionario",fservice.buscarTodos());
		return "/exame/cadastro"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(Exame exame, RedirectAttributes attr) {
	attr.addFlashAttribute("success","Exame cadastrado com sucesso");
	service.salvar(exame);
	return "redirect:/exame/listar";
	}
		
	@GetMapping("/editar/{id}") 
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
	model.addAttribute("exame", service.buscarporId(id));
	return "/exame/cadastro";
	}
		
	@PostMapping("/editar")
	public String editar(Exame exame, RedirectAttributes attr) {
	attr.addFlashAttribute("success","Exame alterado com sucesso");
	service.editar(exame);
	return "redirect:/exame/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
	model.addAttribute("success", "Exame excluído com sucesso");
	service.excluir(id);
		
	return listar(model);
	}
		
	@ModelAttribute("tipoconvenio")
	public List<TConvenio> getTConvenio(){
	return tservice.BuscarTodos();
	}
	
	@ModelAttribute("paciente")
	public List<Paciente> getPaciente(){
	return pservice.buscarTodos();
	}
		
	@ModelAttribute("funcionario")
	public List<Funcionario> getFuncionario(){
	return fservice.buscarTodos();
	}
	
	@GetMapping("/pacientes/{id}")
	public @ResponseBody Paciente paciente(@PathVariable("id") Long id) {
	return pservice.buscarporId(id);
	}
}

