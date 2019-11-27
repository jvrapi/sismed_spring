package br.com.sismed.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	private PacienteService service;
	
	@Autowired
	private ConvenioService convenioService;
	
	@Autowired
	private TConvenioService tipoConvenioService;

	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam(value = "page", required=false, defaultValue="1") int page) {
		PageRequest pagerequest = PageRequest.of(page-1, 13, Sort.by("nome").ascending());
		Page<Paciente> paciente = service.buscarTodos(pagerequest);
		model.addAttribute("paciente", paciente);
		int totalPages = paciente.getTotalPages();
		if (totalPages == 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		else if(totalPages == 2) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		else if (page == 2 && totalPages == 3) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, page + 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		else if(page == 1 || page == 2) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, page + 2).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		else if(page > 2 && page < totalPages - 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(page - 2, page + 2).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		else if(page == totalPages - 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(page-2, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		else if(page == totalPages) {
			List<Integer> pageNumbers = IntStream.rangeClosed(totalPages - 2, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "/pacientes/lista";
	}
	
	/*@GetMapping("/listar")
	public @ResponseBody Page<Paciente> listar(ModelMap model) {
		PageRequest pagerequest = PageRequest.of(0, 2);
		Page<Paciente> paciente = pRepository.findAll(pagerequest);
		return paciente;
	}*/

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
		service.salvar(paciente);
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


