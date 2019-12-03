package br.com.sismed.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.sismed.domain.Agenda;
import br.com.sismed.domain.Convenio;
import br.com.sismed.domain.LabelValue;
import br.com.sismed.domain.Paciente;
import br.com.sismed.domain.Procedimento;
import br.com.sismed.domain.TConvenio;
import br.com.sismed.service.AgendaService;

import br.com.sismed.service.ConvenioService;
import br.com.sismed.service.PacienteService;
import br.com.sismed.service.ProcedimentoService;
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
	
	@Autowired
	private ProcedimentoService procedimentoService;
	
	@Autowired
	private ConvenioService convenioService;
	
	
	/*
	  
	 @GetMapping("/agendamentos")
	public String agendamentos(ModelMap model) {
		model.addAttribute("agendamentos", serivce.ListarAgendamentos());
		return "/agenda/agendamentos";
		
	}
	
	*/
	
	
	@GetMapping("/agendamentos")
	public List<Agenda> lista(ModelMap model) {
		List<Agenda> Agendamentos = serivce.ListarAgendamentos();
		List<Agenda> lista = new ArrayList<Agenda>();
		for(Agenda agenda: Agendamentos) {
		
			Agenda a = new Agenda();
			
			a.setId(agenda.getId());
			a.setObservacao(agenda.getObservacao());
			a.setPrimeira_vez(agenda.getPrimeira_vez());
			a.setCompareceu(agenda.getCompareceu());
			a.setPagou(agenda.getPagou());
			a.setData(agenda.getData());
			a.setHora(agenda.getHora());
			a.setPaciente_id(agenda.getPaciente_id());
			a.setFuncionario(agenda.getFuncionario());
			a.setProcedimento(agenda.getProcedimento());
			a.setTipo_convenio(agenda.getTipo_convenio());
			//a.getPaciente_id().calcularIdade(agenda.getPaciente_id().getData_nascimento());
			
			lista.add(a);
		}
		model.addAttribute("agendamentos", lista);
		return lista;
		
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
		return "/agenda/agendar";
	}
	
	@ModelAttribute("convenio")
	public List<Convenio> listConvenio() {
		return convenioService.findAll();
	}
	
	@GetMapping("/convenio/{id}")
	public @ResponseBody List<TConvenio> listTipoConvenio(@PathVariable("id") Long id, Agenda agenda) {
		return tconvenioService.ListaComboBox(id);
	}
	
	@GetMapping("/procedimento/{id}")
	public @ResponseBody List<Procedimento> listProcedimentos(@PathVariable("id") Long id, Agenda agenda) {
		System.out.println(id);
		return procedimentoService.ListarProcedimento(id) ;
	}
	
	@GetMapping("/valor/{id}")
	public @ResponseBody Procedimento Procedimento(@PathVariable("id") Long id, Agenda agenda) {
		return procedimentoService.BuscarPorId(id);
	}
	
	
}
