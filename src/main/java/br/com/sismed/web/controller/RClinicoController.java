package br.com.sismed.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
			List<Paciente> allPacientes = pacienteSercice.ListarRegPacienteAgen(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue(paciente.getId());
				suggeestions.add(lv);
			}
		}
		else if(id == 2) {
			List<Paciente> allPacientes = pacienteSercice.ListarRegPaciente(term);
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
	public String cadastrar(@PathVariable("id") Long id, ModelMap model, @ModelAttribute("registroclinico") RegistroClinico registroclinico) {
		model.addAttribute("agenda", agendaService.buscarPorId(id));
		model.addAttribute("registro", service.ListarRegAgenda(id));
		return "/registro_clinico/cadastro";
	}
	
	@GetMapping("/cadastrarpac/{id}")
	public String cadastrarPac(@PathVariable("id") Long id, ModelMap model, @ModelAttribute("registroclinico") RegistroClinico registroclinico) {
		model.addAttribute("paciente", pacienteSercice.buscarporId(id));
		model.addAttribute("registro", service.ListarRegPaciente(id));
		return "/registro_clinico/cadastropac";
	}
	
	@PostMapping("/salvar")
	public String salvar(RegistroClinico registroclinico) {
		Long id = registroclinico.getPaciente_id().getId();
		service.salvar(registroclinico);
		return "redirect:/RegistroClinico/cadastrarpac/" + id;
	}
}

