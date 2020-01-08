package br.com.sismed.web.controller;

import java.time.LocalDate;
import java.time.LocalTime;
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

import br.com.sismed.domain.Convenio;
import br.com.sismed.domain.LabelValue;
import br.com.sismed.domain.Log;
import br.com.sismed.domain.Login;
import br.com.sismed.domain.Paciente;
import br.com.sismed.domain.TConvenio;
import br.com.sismed.service.ConvenioService;
import br.com.sismed.service.LogService;
import br.com.sismed.service.LoginService;
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
	
	@Autowired
	private LogService logservice;
	
	@Autowired
	private LoginService lservice;

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
	public String editar(Paciente paciente, RedirectAttributes attr) {
		long id = paciente.getId();
		long cid = paciente.getTipo_convenio().getConvenio().getId();
		service.salvar(paciente);
		attr.addFlashAttribute("success","Paciente alterado com sucesso");
		return "redirect:/pacientes/editar/" + id + "/" + cid;
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr, @AuthenticationPrincipal User user) {
		Paciente p = service.buscarporId(id);
		Login login = lservice.BuscarPorCPF(user.getUsername());
		String retorno = "";
		try {
			service.excluir(id);
			Log l = new Log();
			l.setData(LocalDate.now());
			l.setFuncionario_id(login.getFuncionario_id());
			l.setHora(LocalTime.now());
			l.setDescricao("EXCLUSÃO DO PACIENTE " +p.getNome() );
			logservice.salvar(l);
			attr.addFlashAttribute("success","Paciente excluido com sucesso");
			retorno = "redirect:/pacientes/listar";
		}
		catch (DataIntegrityViolationException error){
			attr.addFlashAttribute("fail","Não foi possível excluir. Paciente possui agendamento(s) cadastrado(s)");
			retorno = "redirect:/pacientes/listar";
		}
		return retorno;
	}
	
	@GetMapping("/convenio/{id}")
	public @ResponseBody List<TConvenio> listTipoConvenio(@PathVariable("id") Long id, Paciente paciente) {
		return tipoConvenioService.ListaComboBox(id);
	}
	
	@ModelAttribute("convenio")
	public List<Convenio> listConvenio() {
		return convenioService.findAll();
	}
	
	@GetMapping("/buscar/{id}")
	@ResponseBody
	public List<LabelValue> listar(@PathVariable("id") Integer id, @RequestParam (value="term", required=false, defaultValue="") String term) {
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();
		if(id == 1) {
			List<Paciente> allPacientes = service.ListarPacId(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue2(paciente.getId() + "/" + paciente.getTipo_convenio().getConvenio().getId());
				suggeestions.add(lv);
			}
		}
		else if(id == 2) {
			List<Paciente> allPacientes = service.ListarPacNome(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue2(paciente.getId() + "/" + paciente.getTipo_convenio().getConvenio().getId());
				suggeestions.add(lv);
			}
		}
		else if(id == 3) {
			List<Paciente> allPacientes = service.PesquisarCPF(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue2(paciente.getId() + "/" + paciente.getTipo_convenio().getConvenio().getId());
				suggeestions.add(lv);
			}
		}
		else if(id == 4) {
			List<Paciente> allPacientes = service.PesquisarCelular(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue2(paciente.getId() + "/" + paciente.getTipo_convenio().getConvenio().getId());
				suggeestions.add(lv);
			}
		}
		return suggeestions;
	}
	
	@ModelAttribute("usuarioLogado")
	public String usuarioLogado(@AuthenticationPrincipal User user, ModelMap model) {
		Login l = lservice.BuscarPorCPF(user.getUsername());
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
