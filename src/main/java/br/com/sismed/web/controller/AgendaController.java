package br.com.sismed.web.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.sismed.domain.Agenda;
import br.com.sismed.domain.Convenio;
import br.com.sismed.domain.Funcionario;
import br.com.sismed.domain.LabelValue;
import br.com.sismed.domain.Login;
import br.com.sismed.domain.Paciente;
import br.com.sismed.domain.Procedimento;
import br.com.sismed.domain.TConvenio;
import br.com.sismed.service.AgendaService;

import br.com.sismed.service.ConvenioService;
import br.com.sismed.service.FuncionarioService;
import br.com.sismed.service.LoginService;
import br.com.sismed.service.PacienteService;
import br.com.sismed.service.ProcedimentoService;
import br.com.sismed.service.TConvenioService;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Controller
@RequestMapping("/agenda")
public class AgendaController {

	@Autowired
	private AgendaService service;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private TConvenioService tconvenioService;
	
	@Autowired
	private ProcedimentoService procedimentoService;
	
	@Autowired
	private ConvenioService convenioService;
	
	@Autowired
	private FuncionarioService fservice;

	@Autowired
	private LoginService lservice;
	
	
	@GetMapping("/agendamentos")
	public List<Agenda> lista(ModelMap model, @AuthenticationPrincipal User user) {
		
		Login l = lservice.BuscarPorCPF(user.getUsername());
		Long perfil = l.getPerfis().getId();
		Long medico_id = l.getFuncionario_id().getId();
		System.out.println(perfil);
		//verifica se um medico esta logado e então exibe a agenda do medico 
		if( perfil != 2 ) {
			
			
			List<Agenda> Agendamentos = service.ListarAgendamentosMedico(medico_id);
			
			List<Agenda> lista = new ArrayList<Agenda>();
			
			//cria uma lista dos agendamentos do dia
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
				
				
				lista.add(a);
			}
			model.addAttribute("agendamentos", lista);
			model.addAttribute("usuario", perfil);
			model.addAttribute("funcionario", l.getFuncionario_id().getNome());
		
			return lista;
		}
		 
		//Se não for um medico, cria um lista com todos os agendamentos;
		List<Agenda> Agendamentos = service.ListarAgendamentos();
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
			List<Paciente> allPacientes = pacienteService.ListarPacNome(term);
			if(allPacientes.isEmpty()) {
				LabelValue lv = new LabelValue();
				lv.setLabel("Paciente não encontrado, Realizar Pre-Cadastro");
				lv.setValue2("0");
				suggeestions.add(lv);
			}
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue(paciente.getId());
				suggeestions.add(lv);
			}
		}
		else if(id == 2) {
			List<Paciente> allPacientes = pacienteService.ListarPacId(term);
			if(allPacientes.isEmpty()) {
				LabelValue lv = new LabelValue();
				lv.setLabel("Paciente não encontrado, Realizar Pre-Cadastro");
				lv.setValue2("0");
				suggeestions.add(lv);
			}
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue(paciente.getId());
				suggeestions.add(lv);
			}
		}
		else if(id == 3) {
			List<Paciente> allPacientes = pacienteService.PesquisarCPF(term);
			if(allPacientes.isEmpty()) {
				LabelValue lv = new LabelValue();
				lv.setLabel("Paciente não encontrado, Realizar Pre-Cadastro");
				lv.setValue2("0");
				suggeestions.add(lv);
			}
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue(paciente.getId());
				suggeestions.add(lv);
			}
		}
		else if(id == 4) {
			List<Paciente> allPacientes = pacienteService.PesquisarTelefone(term);
			if(allPacientes.isEmpty()) {
				LabelValue lv = new LabelValue();
				lv.setLabel("Paciente não encontrado, Realizar Pre-Cadastro");
				lv.setValue2("0");
				suggeestions.add(lv);
			}
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
		model.addAttribute("funcionario", fservice.ListarMedicos());
		
		return "/agenda/agendarPacienteCadastrado";
	}
	
	@PostMapping("/salvar1")
	public String salvar(Agenda agenda, RedirectAttributes attr) {
		agenda.setPrimeira_vez(0L);
		agenda.setPagou(1L);
		agenda.setCompareceu(1L);
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataAgendada = agenda.getData().format(formatador);
		service.salvar(agenda);
		attr.addFlashAttribute("success","Paciente Agendado para o dia " + dataAgendada + " As " + agenda.getHora());
		return "redirect:/agenda/agendamentos";
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
		
		return procedimentoService.ListarProcedimento(id) ;
	}
	
	
	@GetMapping("/valor/{id}")
	public @ResponseBody Procedimento Procedimento(@PathVariable("id") Long id, Agenda agenda) {
		
		return procedimentoService.BuscarPorId(id);
	}
	
	@GetMapping("funcionarioConvenio/{id}")
	public @ResponseBody List<Convenio> listarConvenios(@PathVariable("id") Long id, Agenda agenda){
		return convenioService.funcionarioConvenios(id);
	}
	
	@GetMapping("funcionario/{id}")
	public @ResponseBody Funcionario funcionarioInfo(@PathVariable("id") Long id, Agenda agenda){
		return fservice.buscarporId(id);
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		Agenda agendamento  = service.buscarPorId(id);
		Long funcionario_id = agendamento.getFuncionario().getId();
		Long convenio_id = agendamento.getTipo_convenio().getConvenio().getId();
		model.addAttribute("agendamento", agendamento);
		model.addAttribute("funcionario", fservice.ListarMedicos());
		model.addAttribute("convenio", convenioService.funcionarioConveniosEditar(funcionario_id, convenio_id));
		return "/agenda/editar";
	}
	
	@PostMapping("/editar")
	public String editar(Agenda agenda, RedirectAttributes attr) {
		service.salvar(agenda);
		attr.addFlashAttribute("success","Informações alteradas com sucesso!");
		return "redirect:/agenda/agendamentos";
	}
	
	@GetMapping("/preCadastro")
	public String preCadastro(Agenda agenda, Paciente paciente, ModelMap model) {
		Long prontuario = pacienteService.Ultimoid();
		
		model.addAttribute("funcionario", fservice.ListarMedicos());
		model.addAttribute("prontuario", prontuario);
		
		return "/agenda/preCadastro";
	}
	
	@PostMapping("/salvarPreCadastro")
	public String salvarPreCadastro(Agenda agenda, Paciente paciente, RedirectAttributes attr) {
		agenda.setPrimeira_vez(1L);
		agenda.setPagou(1L);
		agenda.setCompareceu(1L);
		agenda.setPaciente_id(paciente);
		paciente.setTipo_convenio(agenda.getTipo_convenio());
		paciente.setSituacao("A");
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataAgendada = agenda.getData().format(formatador);
		pacienteService.salvar(paciente);
		service.salvar(agenda);
		attr.addFlashAttribute("success","Paciente Agendado para o dia " + dataAgendada + " As " + agenda.getHora());
		return "redirect:/agenda/agendamentos";
	}
	
	@GetMapping("/finalizar")
	public String finalizarAtendimento(RedirectAttributes attr) {
		attr.addFlashAttribute("success","Atendimento finalizado com sucesso!");
		return "redirect:/agenda/agendamentos"; 
	}
	
}
