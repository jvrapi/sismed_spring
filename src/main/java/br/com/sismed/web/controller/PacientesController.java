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

import br.com.sismed.domain.Convenio;
import br.com.sismed.domain.Paciente;
import br.com.sismed.domain.TConvenio;
import br.com.sismed.service.ConvenioService;
import br.com.sismed.service.PacienteService;
import br.com.sismed.service.TConvenioService;

@Controller
@RequestMapping("/pacientes")
public class PacientesController {
	
	@Autowired
	private ConvenioService convenioService;
	
	@Autowired
	private PacienteService service;
	
	@Autowired
	private TConvenioService tipoConvenioService;
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("paciente", service.buscarTodos());
		return "/pacientes/lista";
	}

	@GetMapping("/cadastrar")
	public String cadastrar(Paciente paciente) {
		return "/pacientes/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(Paciente paciente, RedirectAttributes attr) {
		service.salvar(paciente);
		attr.addFlashAttribute("success","Paciente cadastrado com sucesso");
		return "redirect:/pacientes/listar";
	}
	
	@GetMapping("/editar/{id}/{cid}")
	public String preEditar(@PathVariable("id") Long id, @PathVariable("cid") Long cid, ModelMap model) {
		model.addAttribute("paciente", service.buscarporId(id));
		model.addAttribute("tipoconvenio", tipoConvenioService.ListaComboBox(cid));
		return "pacientes/editar";
	}
	
	@PostMapping("/editar")
	public String editar(Paciente paciente) {
		service.editar(paciente);
		return "redirect:/pacientes/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.excluir(id);
		attr.addFlashAttribute("success","Paciente excluido com sucesso");
		return "redirect:/pacientes/listar";
	}
	
	@GetMapping("/convenio/{id}")
	public @ResponseBody List<TConvenio> listTipoConvenio(@PathVariable("id") Long id, Paciente paciente) {
		return tipoConvenioService.ListaComboBox(id);
	}
	
	@ModelAttribute("convenio")
	public List<Convenio> listConvenio() {
		return convenioService.BuscarTodos();
	}
}


