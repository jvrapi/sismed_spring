package br.com.sismed.web.controller;

import java.util.ArrayList;
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

import br.com.sismed.domain.LabelValue;
import br.com.sismed.domain.Paciente;
import br.com.sismed.domain.RegistroClinico;
import br.com.sismed.service.AgendaService;
import br.com.sismed.service.PacienteService;
import br.com.sismed.service.RegistroClinicoService;

@Controller
@RequestMapping("/RegistroClinico")
public class RClinicoController {
	
	@Autowired
	private RegistroClinicoService service;
	
	@Autowired
	private AgendaService agendaService;
	
	@Autowired
	private PacienteService pacienteSercice;
	
	@GetMapping("/buscar")
	public String buscar() {
		return "/registro_clinico/busca";
	}

	@GetMapping("/listar/{id}")
	@ResponseBody
	public List<LabelValue> listar(@PathVariable("id") Integer id, @RequestParam (value="term", required=false, defaultValue="") String term) {
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();
		if(id == 1) {
			List<Paciente> allPacientes = pacienteSercice.ListarPacId(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue(paciente.getId());
				suggeestions.add(lv);
			}
		}
		else if(id == 2) {
			List<Paciente> allPacientes = pacienteSercice.ListarPacNome(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue(paciente.getId());
				suggeestions.add(lv);
			}
		}
		return suggeestions;
	}
	
	/*@GetMapping("/listar/{id}")
	@ResponseBody
	public List<String> listar(@PathVariable("id") Integer id, @RequestParam (value="term", required=false, defaultValue="") String term) {
		List<String> suggeestions = new ArrayList<String>();
		if(id == 1) {
			List<RegistroClinico> allRegistroClinico = service.ListarRegPacienteAgen(term);
			for (RegistroClinico registroclinico : allRegistroClinico) {
				suggeestions.add(registroclinico.toString());
			}
		}
		else {
			List<RegistroClinico> allRegistroClinico = service.ListarRegPacienteAgen(term);
			for (RegistroClinico registroclinico : allRegistroClinico) {
				suggeestions.add(registroclinico.toString());
			}
		}
		return suggeestions;
	}*/
	
	@GetMapping("/cadastrar/{id}")
	public String cadastrar(ModelMap model, @ModelAttribute("registroclinico") RegistroClinico registroclinico, @RequestParam(value = "page", required=false, defaultValue="1") int page, @PathVariable("id") Long id) {
		model.addAttribute("agenda", agendaService.buscarPorId(id));
		PageRequest pagerequest = PageRequest.of(page-1, 5, Sort.by("id").descending());
		Page<RegistroClinico> rclinico = service.ListarRegAgenda(id, pagerequest);
		model.addAttribute("registro", rclinico);
		int totalPages = rclinico.getTotalPages();
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
		return "/registro_clinico/cadastro";
	}
	
	@GetMapping("/cadastrarpac/{id}")
	public String cadastrarPac(@PathVariable("id") Long id, ModelMap model, @ModelAttribute("registroclinico") RegistroClinico registroclinico, @RequestParam(value = "page", required=false, defaultValue="1") int page) {
		model.addAttribute("paciente", pacienteSercice.buscarporId(id));
		PageRequest pagerequest = PageRequest.of(page-1, 5, Sort.by("id").descending());
		Page<RegistroClinico> rclinico = service.ListarRegPac(id, pagerequest);
		model.addAttribute("registro", rclinico);
		int totalPages = rclinico.getTotalPages();
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
		return "/registro_clinico/cadastropac";
	}
	
	@PostMapping("/salvar")
	public String salvar(RegistroClinico registroclinico) {
		Long id = registroclinico.getPaciente_id().getId();
		service.salvar(registroclinico);
		return "redirect:/RegistroClinico/cadastrarpac/" + id;
	}
	
	@GetMapping("buscarregistros/{id}")
	public @ResponseBody Page<RegistroClinico> listRegistros(@RequestParam(value = "page", required=false, defaultValue="1") int page, @PathVariable("id") Long id) {
		PageRequest pagerequest = PageRequest.of(page-1, 3);
		return service.ListarRegAgenda(id, pagerequest);
	}
}

