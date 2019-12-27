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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sismed.domain.Exame;
import br.com.sismed.domain.Funcionario;
import br.com.sismed.domain.LabFlag;
import br.com.sismed.domain.LabelValue;
import br.com.sismed.domain.Paciente;
import br.com.sismed.service.ExameService;
import br.com.sismed.service.FuncionarioService;
import br.com.sismed.service.LaboratorioService;
import br.com.sismed.service.PacienteService;
import br.com.sismed.service.TConvenioService;
import ch.qos.logback.core.net.SyslogOutputStream;

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
	
	@GetMapping("/listar")
	
		public String listar(ModelMap model, @RequestParam(value = "page", required=false, defaultValue="1") int page) {
		PageRequest pagerequest = PageRequest.of(page-1, 5, Sort.by("id").descending());
		Page<Exame> exame = service.buscarTodos(pagerequest);
		model.addAttribute("exame", exame);
		int lastPage = exame.getTotalPages();
		
		if (lastPage == 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		else if(lastPage == 2) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, lastPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		else if (page == 2 && lastPage == 3) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, page + 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		else if(page == 1 || page == 2) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, page + 2).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		else if(page > 2 && page < lastPage - 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(page - 2, page + 2).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		else if(page == lastPage - 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(page-2, lastPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		else if(page == lastPage) {
			List<Integer> pageNumbers = IntStream.rangeClosed(lastPage - 2, lastPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		return "/exame/lista"; 
	}
		
	@GetMapping("/cadastrar") 
	public String cadastrar (ModelMap model, Exame exame) {
		model.addAttribute("paciente",pservice.findAll());
		model.addAttribute("funcionario",fservice.findAll());
		return "/exame/cadastro"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(Exame exame, RedirectAttributes attr) {
	attr.addFlashAttribute("success","Exame cadastrado com sucesso");
	service.salvar(exame);
	return "redirect:/exame/listar";
	}
		
	@GetMapping("/editar/{id}/{tcid}") 
	public String preEditar(@PathVariable("id") Long id, @PathVariable("tcid") Long tcid, ModelMap model) {
	model.addAttribute("exame", service.buscarporId(id));
	model.addAttribute("laboratorio", lservice.ListarLabTConv(tcid));
	return "/exame/editar";
	}
	
	@GetMapping("/buscar/{id}")
	@ResponseBody
	public List<LabelValue> buscar (@PathVariable("id")Integer id, @RequestParam (value="term", required=false, defaultValue="") String term){
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();
	
		if(id == 2) {
			List<Paciente> allPacientes = pservice.ListarPacNome(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue(paciente.getId());
				suggeestions.add(lv);
			}
		}
		
		else if(id == 3) {
			List<Exame> allExame = service.ListarExameNome(term);
			for (Exame exame : allExame) {
				LabelValue lv = new LabelValue();
				lv.setLabel(exame.getNome());
				suggeestions.add(lv);
			}
		}
		
		else if(id == 4) {
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
	public String editar(Exame exame, RedirectAttributes attr) {
	attr.addFlashAttribute("success","Exame alterado com sucesso");
	service.salvar(exame);
	return "redirect:/exame/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
	model.addAttribute("success", "Exame excluído com sucesso");
	service.excluir(id);
		
	return "redirect:/exame/listar";
	}
		
	/*@ModelAttribute("tipoconvenio")
	public List<TConvenio> getTConvenio(){
	return tservice.BuscarTodos();
	}*/
	
	@ModelAttribute("paciente")
	public List<Paciente> getPaciente(){
	return pservice.findAll();
	}
		
	@ModelAttribute("funcionario")
	public List<Funcionario> getFuncionario(){
	return fservice.findAll();
	}
	
	/*@GetMapping("/pacientes/{id}")
	public @ResponseBody Paciente paciente(@PathVariable("id") Long id) {
	return pservice.buscarporId(id);
	}*/
	
	@GetMapping("/buscarpaciente")
	@ResponseBody
	public List<LabelValue> listarpaciente(@RequestParam (value="term", required=false, defaultValue="") String term) {
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
	public List<LabelValue> listarfuncionario(@RequestParam (value="term", required=false, defaultValue="") String term) {
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
		if(!listLabFlag.getListLab().isEmpty()) {
			listLabFlag.setFlag(1);
			return listLabFlag;
		}
		else {
			listLabFlag.setListLab(lservice.findAll());
			listLabFlag.setFlag(0);
			return listLabFlag;
		}
	}
	
	@PostMapping("/buscarlista") 
	public String buscarlista(ModelMap model, @RequestParam("id_paciente") Long id, @RequestParam("nome_exame") String exame, @RequestParam("coleta_data") String data) {

		if(exame == "" && data == "") {
			model.addAttribute("exame", service.ListaExamePacienteId(id));
		}
		else if(id == null && data == "") {
			model.addAttribute("exame", service.ListarExameNome(exame));
		}
		else if(id == null && exame == "") {
			model.addAttribute("exame", service.ListarExameData(data));
		}
		else if(data == "") {
			model.addAttribute("exame", service.ListarExamePacienteExame(id, exame));
		}
		else if(exame == "") {
			model.addAttribute("exame", service.ListarExamePacienteData(id, data));
		}
		else if(id == null) {
			model.addAttribute("exame", service.ListarExameExameData(exame, data));
		}
		else if(id != null && exame != "" && data != "") {
			model.addAttribute("exame", service.ListarExameTudo(id, exame, data));
		}
		return "/exame/buscar_lista";
	}
	
	
	
}

