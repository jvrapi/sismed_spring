package br.com.sismed.web.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
	public String lista(ModelMap model, @AuthenticationPrincipal User user) {

		
		
		Login l = lservice.BuscarPorCPF(user.getUsername());
		Long perfil = l.getPerfis().getId();
		Long medico_id = l.getFuncionario_id().getId();
		
		List<Funcionario> medicos = fservice.ListarMedicos();
		
		model.addAttribute("usuario", perfil);
		model.addAttribute("funcionario", l.getFuncionario_id().getNome());
		model.addAttribute("medicos", medicos);
		model.addAttribute("medico_id", medico_id);
		return "/agenda/agendamentos";
	}

	@GetMapping("/listar/{id}")
	@ResponseBody
	public List<LabelValue> listar(@PathVariable("id") Integer id,
			@RequestParam(value = "term", required = false, defaultValue = "") String term) {
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();
		if (id == 1) {
			List<Paciente> allPacientes = pacienteService.ListarPacNome(term);
			if (allPacientes.isEmpty()) {
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
		} else if (id == 2) {
			List<Paciente> allPacientes = pacienteService.ListarPacId(term);
			if (allPacientes.isEmpty()) {
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
		} else if (id == 3) {
			List<Paciente> allPacientes = pacienteService.PesquisarCPF(term);
			if (allPacientes.isEmpty()) {
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
		} else if (id == 4) {
			List<Paciente> allPacientes = pacienteService.PesquisarTelefone(term);
			if (allPacientes.isEmpty()) {
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
		attr.addFlashAttribute("success", "Paciente Agendado para o dia " + dataAgendada + " As " + agenda.getHora());
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

		return procedimentoService.ListarProcedimento(id);
	}

	@GetMapping("/valor/{id}")
	public @ResponseBody Procedimento Procedimento(@PathVariable("id") Long id, Agenda agenda) {

		return procedimentoService.BuscarPorId(id);
	}

	@GetMapping("funcionarioConvenio/{id}")
	public @ResponseBody List<Convenio> listarConvenios(@PathVariable("id") Long id, Agenda agenda) {
		return convenioService.funcionarioConvenios(id);
	}

	@GetMapping("funcionario/{id}")
	public @ResponseBody Funcionario funcionarioInfo(@PathVariable("id") Long id, Agenda agenda) {
		return fservice.buscarporId(id);
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		Agenda agendamento = service.buscarPorId(id);
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
		attr.addFlashAttribute("success", "Informações alteradas com sucesso!");
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
		attr.addFlashAttribute("success", "Paciente Agendado para o dia " + dataAgendada + " As " + agenda.getHora());
		return "redirect:/agenda/agendamentos";
	}

	@GetMapping("/finalizar")
	public String finalizarAtendimento(RedirectAttributes attr) {
		List<Agenda> encerrar = service.encerrarAtendimento();
		for (Agenda a : encerrar) {
			if (a.getPrimeira_vez() == 1 && a.getCompareceu() == 0) {
				// paciente pre cadastrado, porem não compareceu;
				Paciente p = new Paciente();
				p.setId(a.getPaciente_id().getId());
				p.setNome(a.getPaciente_id().getNome());
				p.setSituacao("NC");
				p.setCelular(a.getPaciente_id().getCelular());
				p.setTipo_convenio(a.getPaciente_id().getTipo_convenio());
				pacienteService.salvar(p);
			}
		}
		attr.addFlashAttribute("success", "Atendimento finalizado com sucesso!");
		return "redirect:/agenda/agendamentos";
	}

	@GetMapping("/agendamentosAnteriores/{id}")
	public String find(ModelMap model, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@PathVariable("id") Long id) {
		
		PageRequest pagerequest = PageRequest.of(page - 1, 3);
		Page<Agenda> agendamentos = service.agendamentosAnteriores(id, pagerequest);
		model.addAttribute("agenda", agendamentos);
		int totalPages = agendamentos.getTotalPages();
		if (totalPages == 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (totalPages == 2) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page == 2 && totalPages == 3) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, page + 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page == 1 || page == 2) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, page + 2).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page > 2 && page < totalPages - 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(page - 2, page + 2).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page == totalPages - 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(page - 2, totalPages).boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page == totalPages) {
			List<Integer> pageNumbers = IntStream.rangeClosed(totalPages - 2, totalPages).boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "fragmentos/agendamentosAnteriores :: resultsList";
	}
	
	@GetMapping("/agendaFuncionario/{id}")
	public String agendaFuncionario(@PathVariable("id") Long id, Agenda agenda, ModelMap model) {
		
		List<Agenda> agendamentos = service.ListarAgendamentosMedico(id);
		List<Funcionario> medicos = fservice.ListarMedicos();
		model.addAttribute("agendamentos", agendamentos);
		for (Agenda a : agendamentos) {
			model.addAttribute("data", a.getData());	
		}
		return "fragmentos/agendaFuncionario :: resultsList";
	}
	
	@GetMapping("/agendaMedico")
	public String agendaMedico(Agenda agenda, ModelMap model, @AuthenticationPrincipal User user) {
		
		Login l = lservice.BuscarPorCPF(user.getUsername());
		Long perfil = l.getPerfis().getId();
		Long medico_id = l.getFuncionario_id().getId();
		List<Agenda> agendamentos = service.ListarAgendamentosMedico(medico_id);
		
		for (Agenda a : agendamentos) {
			model.addAttribute("data", a.getData());	
		}
		model.addAttribute("usuario", perfil);
		model.addAttribute("funcionario", l.getFuncionario_id().getNome());
		model.addAttribute("agendamentos", agendamentos);
		
		
		return "fragmentos/agendaFuncionario :: resultsList";
	}
	
	@GetMapping("buscarAgendamento/{data}/{medico}")
	public String buscarAgendamento(@PathVariable("data") String data, @PathVariable("medico") Long medico, ModelMap model, Agenda agenda) {
		agenda.compararDatas(data);
		List<Agenda> agendamentos = service.buscarAgendamentos(data, medico);
		model.addAttribute("agendamentos", agendamentos);
		return "fragmentos/agendaFuncionario :: resultsList";
	}

}
