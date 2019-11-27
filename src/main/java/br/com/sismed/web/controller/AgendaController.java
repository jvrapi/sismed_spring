package br.com.sismed.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.sismed.domain.Agenda;
import br.com.sismed.domain.LabelValue;
import br.com.sismed.domain.Paciente;
import br.com.sismed.service.AgendaService;
import br.com.sismed.service.PacienteService;
import br.com.sismed.service.TConvenioService;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

	@Autowired
	private AgendaService serivce;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private TConvenioService tconvenioService;
	
	
	@GetMapping("/agendamentos")
	public String agendamentos(ModelMap model) {
		model.addAttribute("agendamentos", serivce.ListarAgendamentos());
		return "/agenda/agendamentos";
		
	}
	@GetMapping("/listar/{id}")
	@ResponseBody
	public List<LabelValue> listar(@PathVariable("id") Integer id, @RequestParam (value="term", required=false, defaultValue="") String term) {
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();
		if(id == 1) {
			List<Paciente> allPacientes = pacienteService.ListarPacId(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue(paciente.getId());
				suggeestions.add(lv);
			}
		}
		else if(id == 2) {
			List<Paciente> allPacientes = pacienteService.ListarPacNome(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue(paciente.getId());
				suggeestions.add(lv);
			}
		}
		else if(id == 3) {
			List<Paciente> allPacientes = pacienteService.PesquisarCPF(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue(paciente.getId());
				suggeestions.add(lv);
			}
		}
		else if(id == 4) {
			List<Paciente> allPacientes = pacienteService.PesquisarTelefone(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue(paciente.getId());
				suggeestions.add(lv);
			}
		}
		return suggeestions;	
	}
	
	@GetMapping("/agendar/{id}")
	public String agendar(@PathVariable("id") Long id, ModelMap model, Agenda agendar) {
		model.addAttribute("paciente", pacienteService.buscarporId(id));
		Paciente paciente = pacienteService.buscarporId(id) ;
		Long tconvenio = paciente.getTipo_convenio().getId();
		model.addAttribute("tconvenio", tconvenioService.buscarPorId(tconvenio));
		return "/agenda/agendar";
	}
	
}
