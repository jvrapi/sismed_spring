package br.com.sismed.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sismed.domain.Funcionario;
import br.com.sismed.domain.LabelValue;
import br.com.sismed.domain.Perfil;
import br.com.sismed.service.FuncionarioService;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService service;
	
	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam(value = "page", required=false, defaultValue="1") int page){
		PageRequest pagerequest = PageRequest.of(page-1,12,Sort.by("id").ascending());
		Page<Funcionario> funcionario = service.buscarTodos(pagerequest);
		model.addAttribute("funcionario", funcionario);
		int lastPage = funcionario.getTotalPages();
		
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
		
		return "/funcionario/lista"; 
	}
	
	@GetMapping("/cadastrar") 
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar( Funcionario funcionario, RedirectAttributes attr) {
		String crm = funcionario.getCrm();
		if(crm == null) {
			Perfil perfil = new Perfil();
			perfil.setId(2L);
			funcionario.setPerfil(perfil);
		}else {
			Perfil perfil = new Perfil();
			perfil.setId(1L);
			funcionario.setPerfil(perfil);
		}
		
		attr.addFlashAttribute("success","Funcionário(a) cadastrado(a) com sucesso");
		service.salvar(funcionario);
		return "redirect:/funcionario/listar";
	}
	
	@GetMapping("/editar/{id}") 
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario", service.buscarporId(id));
		return "/funcionario/editar";
	}
	
	@GetMapping("/buscar/{id}")
	@ResponseBody
	public List<LabelValue> buscar (@PathVariable("id")Integer id, @RequestParam (value="term", required=false, defaultValue="") String term){
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();
		
		if(id == 1) {
			Funcionario allFuncionario = service.ListarFuncionarioId(term);
			
				LabelValue lv = new LabelValue();
				lv.setLabel(allFuncionario.getNome());
				lv.setValue(allFuncionario.getId());
				suggeestions.add(lv);
			}
		
		else if(id == 2) {
			List<Funcionario> allFuncionario = service.ListarFuncionarioNome(term);
			for (Funcionario funcionario : allFuncionario) {
				LabelValue lv = new LabelValue();
				lv.setLabel(funcionario.getNome());
				lv.setValue(funcionario.getId());
				suggeestions.add(lv);
			}
		}
		
		else if(id == 3) {
			List<Funcionario> allFuncionario = service.ListarFuncionarioCPF(term);
			for (Funcionario funcionario : allFuncionario) {
				LabelValue lv = new LabelValue();
				lv.setLabel(funcionario.getNome());
				lv.setValue(funcionario.getId());
				suggeestions.add(lv);
			}
		}
	
		else if(id == 4) {
			List<Funcionario> allFuncionario = service.ListarFuncionarioCelular(term);
			for (Funcionario funcionario : allFuncionario) {
				LabelValue lv = new LabelValue();
				lv.setLabel(funcionario.getNome());
				lv.setValue(funcionario.getId());
				suggeestions.add(lv);
			}
		}
		
		else if(id == 5) {
			List<Funcionario> allFuncionario = service.ListarFuncionarioCRM(term);
			for (Funcionario funcionario : allFuncionario) {
				LabelValue lv = new LabelValue();
				lv.setLabel(funcionario.getNome());
				lv.setValue(funcionario.getId());
				suggeestions.add(lv);
			}
		}
		
		else if(id == 6) {
			List<Funcionario> allFuncionario = service.ListarFuncionarioEspecialidade(term);
			for (Funcionario funcionario : allFuncionario) {
				LabelValue lv = new LabelValue();
				lv.setLabel(funcionario.getNome());
				lv.setValue(funcionario.getId());
				suggeestions.add(lv);
			}
		}
		
		return suggeestions;
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Funcionario funcionario, RedirectAttributes attr) {
		String crm = funcionario.getCrm();
		if(crm == null) {
			Perfil perfil = new Perfil();
			perfil.setId(2L);
			funcionario.setPerfil(perfil);
		}else {
			Perfil perfil = new Perfil();
			perfil.setId(1L);
			funcionario.setPerfil(perfil);
		}
		
		attr.addFlashAttribute("success","Funcionario(a) alterado(a) com sucesso");
		service.salvar(funcionario);
		return "redirect:/funcionario/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("success", "Funcionario(a) excluído(a) com sucesso");
		service.excluir(id);
		
		return "redirect:/funcionario/listar";
	}
	
	
	
}
