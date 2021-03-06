package br.com.sismed.web.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import org.springframework.security.core.userdetails.User;
import br.com.sismed.domain.Exame;
import br.com.sismed.domain.Funcionario;
import br.com.sismed.domain.LabFlag;
import br.com.sismed.domain.LabelValue;
import br.com.sismed.domain.Log;
import br.com.sismed.domain.Login;
import br.com.sismed.domain.Paciente;
import br.com.sismed.service.ExameService;
import br.com.sismed.service.FuncionarioService;
import br.com.sismed.service.LaboratorioService;
import br.com.sismed.service.LogService;
import br.com.sismed.service.LoginService;
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

	@Autowired
	public LaboratorioService lservice;

	@Autowired
	public LoginService loginservice;

	@Autowired
	public LogService logservice;

	@GetMapping("/listar")

	public String listar(ModelMap model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		PageRequest pagerequest = PageRequest.of(page - 1, 5, Sort.by("id").descending());
		Page<Exame> exame = service.buscarTodos(pagerequest);
		model.addAttribute("exame", exame);
		int lastPage = exame.getTotalPages();

		if (lastPage == 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (lastPage == 2) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, lastPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page == 2 && lastPage == 3) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, page + 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page == 1 || page == 2) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, page + 2).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page > 2 && page < lastPage - 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(page - 2, page + 2).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page == lastPage - 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(page - 2, lastPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page == lastPage) {
			List<Integer> pageNumbers = IntStream.rangeClosed(lastPage - 2, lastPage).boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "exame/lista";
	}

	@GetMapping("/cadastrar")
	public String cadastrar(ModelMap model, Exame exame) {
		model.addAttribute("paciente", pservice.findAll());
		model.addAttribute("funcionario", fservice.findAll());
		return "exame/cadastro";
	}

	@PostMapping("/salvar")
	public String salvar(Exame exame, RedirectAttributes attr) {
		attr.addFlashAttribute("success", "Exame cadastrado com sucesso");
		service.salvar(exame);
		return "redirect:/exame/listar";
	}

	@GetMapping("/editar/{id}/{tcid}")
	public String preEditar(@PathVariable("id") Long id, @PathVariable("tcid") Long tcid, ModelMap model) {
		model.addAttribute("exame", service.buscarporId(id));
		model.addAttribute("laboratorio", lservice.ListarLabTConv(tcid));
		return "exame/editar";
	}

	@GetMapping("/buscar/{id}")
	@ResponseBody
	public List<LabelValue> buscar(@PathVariable("id") Integer id,
			@RequestParam(value = "term", required = false, defaultValue = "") String term) {
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();

		if (id == 2) {
			List<Paciente> allPacientes = pservice.ListarPacNome(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue(paciente.getId());
				suggeestions.add(lv);
			}
		}

		else if (id == 3) {
			List<Exame> allExame = service.ListarExameNome(term);
			for (Exame exame : allExame) {
				LabelValue lv = new LabelValue();
				lv.setLabel(exame.getNome());
				suggeestions.add(lv);
			}
		}

		else if (id == 4) {
			List<Exame> allExame = service.ListarExameDataColeta(term);
			for (Exame exame : allExame) {
				LabelValue lv = new LabelValue();
				lv.setLabel(exame.getNome());
				lv.setValue(exame.getId());
				suggeestions.add(lv);
			}
		}

		return suggeestions;
	}

	@PostMapping("/editar")
	public String editar(Exame exame, RedirectAttributes attr, @AuthenticationPrincipal User user ) {
		attr.addFlashAttribute("success","Exame alterado com sucesso");
		Exame e = service.buscarporId(exame.getId());
		Login login = loginservice.BuscarPorCPF(user.getUsername());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if(!exame.getData_coleta().isEqual(e.getData_coleta())) {
			Log l = new Log();
			l.setData(LocalDate.now());
			l.setFuncionario_id(login.getFuncionario_id());
			l.setHora(LocalTime.now());
			l.setDescricao("ALTER????O NA DATA DE COLETA: NOME DO EXAME " + e.getNome() + ". NOME DO PACIENTE: " + e.getPaciente_id().getNome() + ". DA DATA " 
					+ e.getData_coleta().format(formatter) + " PARA A DATA " + exame.getData_coleta().format(formatter));
			logservice.salvar(l);
		}
		
		if(!exame.getData_envio().isEqual(e.getData_envio())) {
			Log l = new Log();
			l.setData(LocalDate.now());
			l.setFuncionario_id(login.getFuncionario_id());
			l.setHora(LocalTime.now());
			l.setDescricao("ALTER????O NA DATA DE ENVIO: NOME DO EXAME " + e.getNome() + ". NOME DO PACIENTE " + e.getPaciente_id().getNome() + ". DA DATA " 
					+ e.getData_envio().format(formatter) + " PARA A DATA " + exame.getData_envio().format(formatter));
			logservice.salvar(l);
		}
		if(exame.getData_retorno() != null) {
			System.out.println("prmeiro if");
			if(e.getData_retorno() != null && !exame.getData_retorno().equals(e.getData_retorno())) {
				System.out.println("segundo if");
				Log l = new Log();
				l.setData(LocalDate.now());
				l.setFuncionario_id(login.getFuncionario_id());
				l.setHora(LocalTime.now());
				l.setDescricao("ALTER????O DE DATA DE RETORNO: NOME DO EXAME: " + e.getNome() + ". NOME DO PACIENTE: " + e.getPaciente_id().getNome() + ". DA DATA " 
						+ e.getData_retorno().format(formatter) + " PARA A DATA " + exame.getData_retorno().format(formatter));
				logservice.salvar(l);
			} else if(e.getData_retorno() == null) {
				System.out.println("segundo if");
				Log l = new Log();
				l.setData(LocalDate.now());
				l.setFuncionario_id(login.getFuncionario_id());
				l.setHora(LocalTime.now());
				l.setDescricao("ALTER????O DE DATA DE RETORNO: NOME DO EXAME: " + e.getNome() + ". NOME DO PACIENTE: " + e.getPaciente_id().getNome() + ". DE DATA DE RETORNO PENDENTE PARA A DATA " + exame.getData_retorno().format(formatter));
				logservice.salvar(l);
			}
		} else if(e.getData_retorno() != null){
			Log l = new Log();
			l.setData(LocalDate.now());
			l.setFuncionario_id(login.getFuncionario_id());
			l.setHora(LocalTime.now());
			l.setDescricao("ALTER????O DE DATA DE RETORNO: NOME DO EXAME: " + e.getNome() + ". NOME DO PACIENTE: " + e.getPaciente_id().getNome() + ". DA DATA " 
					+ e.getData_retorno().format(formatter) + " PARA DATA DE RETORNO PENDENTE" );
			logservice.salvar(l);
		}
		service.salvar(exame);
		return "redirect:/exame/editar/" + exame.getId() + "/" + exame.getPaciente_id().getTipo_convenio().getId();
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attr, @AuthenticationPrincipal User user) {

		String retorno = "";
		Exame e = service.buscarporId(id);
		try {
			Login login = loginservice.BuscarPorCPF(user.getUsername());
			Log l = new Log();
			l.setData(LocalDate.now());
			l.setFuncionario_id(login.getFuncionario_id());
			l.setHora(LocalTime.now());
			l.setDescricao("EXCLUS??O DE EXAME: NOME DO EXAME: " + e.getNome() + ". NOME DO PACIENTE: " + e.getPaciente_id().getNome());
			logservice.salvar(l);
			model.addAttribute("success", "Exame exclu??do com sucesso");
			service.excluir(id);
			attr.addFlashAttribute("success","Exame excluido com sucesso");
			retorno = "redirect:/exame/listar";
		}
		catch (DataIntegrityViolationException error) {
			attr.addFlashAttribute("fail","N??o foi poss??vel excluir");
			retorno = "redirect:/exame/listar";
		}
		return retorno;
	}

	/*
	 * @ModelAttribute("tipoconvenio") public List<TConvenio> getTConvenio(){ return
	 * tservice.BuscarTodos(); }
	 */

	@ModelAttribute("paciente")
	public List<Paciente> getPaciente() {
		return pservice.findAll();
	}

	@ModelAttribute("funcionario")
	public List<Funcionario> getFuncionario() {
		return fservice.findAll();
	}

	/*
	 * @GetMapping("/pacientes/{id}") public @ResponseBody Paciente
	 * paciente(@PathVariable("id") Long id) { return pservice.buscarporId(id); }
	 */

	@GetMapping("/buscarpaciente")
	@ResponseBody
	public List<LabelValue> listarpaciente(
			@RequestParam(value = "term", required = false, defaultValue = "") String term) {
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();

		List<Paciente> allPacientes = pservice.ListarPacNome(term);
		for (Paciente paciente : allPacientes) {
			LabelValue lv = new LabelValue();
			lv.setLabel(paciente.getNome());
			lv.setValue(paciente.getId());
			lv.setConvenio(paciente.getTipo_convenio().getConvenio().getId());
			lv.setNome_convenio(paciente.getTipo_convenio().getConvenio().getNome());
			lv.setTipo(paciente.getTipo_convenio().getId());
			lv.setNome_tipo(paciente.getTipo_convenio().getNome());
			suggeestions.add(lv);
		}

		return suggeestions;
	}

	@GetMapping("/buscarfuncionario")
	@ResponseBody
	public List<LabelValue> listarfuncionario(
			@RequestParam(value = "term", required = false, defaultValue = "") String term) {
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();
		List<Funcionario> allFuncionarios = fservice.ListarFuncionarioNome(term);
		for (Funcionario funcionario : allFuncionarios) {
			LabelValue lv = new LabelValue();
			lv.setLabel(funcionario.getNome());
			lv.setValue(funcionario.getId());
			suggeestions.add(lv);
		}
		return suggeestions;
	}

	@GetMapping("/buscarlab/{id}")
	@ResponseBody
	public LabFlag listarlab(@PathVariable("id") Long id) {
		LabFlag listLabFlag = new LabFlag();
		listLabFlag.setListLab(lservice.ListarLabTConv(id));
		if (!listLabFlag.getListLab().isEmpty()) {
			listLabFlag.setFlag(1);
			return listLabFlag;
		} else {
			listLabFlag.setListLab(lservice.findAll());
			listLabFlag.setFlag(0);
			return listLabFlag;
		}
	}

	@PostMapping("/buscarlista")
	public String buscarlista(ModelMap model, @RequestParam("id_paciente") Long id,
			@RequestParam("nome_exame") String exame, @RequestParam("coleta_data") String data) {

		if (exame == "" && data == "") {
			model.addAttribute("exame", service.ListaExamePacienteId(id));
		} else if (id == null && data == "") {
			model.addAttribute("exame", service.ListarExameNome(exame));
		} else if (id == null && exame == "") {
			model.addAttribute("exame", service.ListarExameData(data));
		} else if (data == "") {
			model.addAttribute("exame", service.ListarExamePacienteExame(id, exame));
		} else if (exame == "") {
			model.addAttribute("exame", service.ListarExamePacienteData(id, data));
		} else if (id == null) {
			model.addAttribute("exame", service.ListarExameExameData(exame, data));
		} else if (id != null && exame != "" && data != "") {
			model.addAttribute("exame", service.ListarExameTudo(id, exame, data));
		}
		return "exame/buscar_lista";
	}

	@ModelAttribute("usuarioLogado")
	public String usuarioLogado(@AuthenticationPrincipal User user, ModelMap model) {
		Login l = loginservice.BuscarPorCPF(user.getUsername());
		String pattern = "\\S+";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(l.getFuncionario_id().getNome());
		String retorno = "";
		if (m.find()) {
	         retorno = m.group(0);
			
			model.addAttribute("usuario",  m.group(0));
	         
	      } else {
	         // mensagem de erro
	    	  retorno = l.getFuncionario_id().getNome();
	      }
		
		return retorno;
	}
}
