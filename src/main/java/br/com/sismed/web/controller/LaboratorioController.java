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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sismed.domain.LabelValue;
import br.com.sismed.domain.Laboratorio;
import br.com.sismed.service.LaboratorioService;

@Controller
@RequestMapping("/laboratorio")
public class LaboratorioController {
	
	@Autowired
	private LaboratorioService service;
	
	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam(value = "page", required=false, defaultValue="1") int page) {
		
		PageRequest pagerequest = PageRequest.of(page-1, 1, Sort.by("nome").ascending());
		Page<Laboratorio> laboratorio = service.buscarTodos(pagerequest);
		model.addAttribute("laboratorio", laboratorio);
		
		int lastPage = laboratorio.getTotalPages();
		
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
		
		return "/laboratorio/lista"; 
	}
	
	@GetMapping("/cadastrar") 
	public String cadastrar(Laboratorio laboratorio) {
		return "/laboratorio/cadastro"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(Laboratorio laboratorio, RedirectAttributes attr) {
		attr.addFlashAttribute("success","Laboratório cadastrado com sucesso");
		service.salvar(laboratorio);
		return "redirect:/laboratorio/listar";
	}
	
	@GetMapping("/editar/{id}") 
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("laboratorio", service.buscarporId(id));
		return "/laboratorio/editar";
	}
	
	@GetMapping("/buscar/{id}")
	@ResponseBody
	public List<LabelValue> buscar (@PathVariable("id")Integer id, @RequestParam (value="term", required=false, defaultValue="") String term){
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();
		
		if(id == 2) {
			List<Laboratorio> allLaboratorio = service.ListarLaboratorioNome(term);
			for (Laboratorio laboratorio : allLaboratorio) {
				LabelValue lv = new LabelValue();
				lv.setLabel(laboratorio.getNome());
				lv.setValue(laboratorio.getId());
				suggeestions.add(lv);
			}
		}
		
		else if(id == 3) {
			List<Laboratorio> allLaboratorio = service.ListarLaboratorioTelefone(term);
			for (Laboratorio laboratorio : allLaboratorio) {
				LabelValue lv = new LabelValue();
				lv.setLabel(laboratorio.getNome());
				lv.setValue(laboratorio.getId());
				suggeestions.add(lv);
			}
		}
		
		else if(id == 4) {
			List<Laboratorio> allLaboratorio = service.ListarLaboratorioBairro(term);
			for (Laboratorio laboratorio : allLaboratorio) {
				LabelValue lv = new LabelValue();
				lv.setLabel(laboratorio.getNome());
				lv.setValue(laboratorio.getId());
				suggeestions.add(lv);
			}
		}
		
		return suggeestions;
	}
	
	@PostMapping("/editar")
	public String editar(Laboratorio laboratorio, RedirectAttributes attr) {
		attr.addFlashAttribute("success","Laboratório alterado com sucesso");
		service.salvar(laboratorio);
		return "redirect:/laboratorio/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("success", "Laboratório excluído com sucesso");
		service.excluir(id);
		
		return "redirect:/funcionario/listar";
	}
}
