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

import br.com.sismed.domain.Convenio;
import br.com.sismed.domain.LabelValue;
import br.com.sismed.domain.TConvenio;
import br.com.sismed.service.ConvenioService;
import br.com.sismed.service.TConvenioService;

@Controller
@RequestMapping("/tconvenios")
public class TConvenioController {

	@Autowired
	private ConvenioService service;

	@Autowired
	private TConvenioService tservice;

	
	//paginação
	@GetMapping("/listar/{id}") // segunda parte do href
	public String listar(ModelMap model, @RequestParam(value = "page", required=false, defaultValue="1") int page, @PathVariable("id") Long id) {
		PageRequest pagerequest = PageRequest.of(page-1, 5, Sort.by("nome").ascending());
		Page<TConvenio> tconvenio = tservice.ListarTipoConvenio(id, pagerequest);
		model.addAttribute("convenios", service.buscarPorId(id));
		model.addAttribute("tconvenio", tconvenio);
		int totalPages = tconvenio.getTotalPages();
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
		return "/tconvenio/lista";
	}
	
	@GetMapping("/buscar/{id}")
	@ResponseBody
	public List<LabelValue> buscar(@PathVariable("id") Long id, @RequestParam (value="term", required=false, defaultValue="") String term) {
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();
		
			List<TConvenio> allTipos = tservice.ListarPorNome(term, id);
			for (TConvenio tconvenio : allTipos) {
				LabelValue lv = new LabelValue();
				lv.setLabel(tconvenio.getNome());
				lv.setValue(tconvenio.getId());
				suggeestions.add(lv);
			}
			return suggeestions;	
		
		
		
	}
	

	
	@GetMapping("/cadastrar/{id2}") // segunda parte do href
	public String cadastrar(@PathVariable("id2") Long id, ModelMap model, @ModelAttribute("tconvenio") TConvenio tconvenio) {
		model.addAttribute("convenio", service.buscarPorId(id));
		return "/tconvenio/cadastro"; // retorna o caminho do arquivo

	}

	@PostMapping("/salvar")
	public String salvar(@Valid TConvenio tconvenio, BindingResult result,RedirectAttributes attr) {
		Long id = tconvenio.getConvenio().getId() ;
		if(result.hasErrors()) {
			
			return "redirect:/tconvenios/cadastrar/" + id;
		}
		tservice.salvar(tconvenio);
		attr.addFlashAttribute("success", "Tipo de Convenio cadastrado com sucesso");
		return "redirect:/tconvenios/listar/" + id;
	}

	@GetMapping("/editar/{id}") // ID do convenio que vem pela URL
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		/*
		 * @PathVariable = recupera da url o id enviado pela URL como um path o objeto
		 * model serve para enviar para a pagina de cadastro o convenio como uma
		 * variavel
		 */
		model.addAttribute("tconvenio", tservice.buscarPorId(id));
		return "/tconvenio/editar";
	}

	@PostMapping("/editar")
	public String editar(TConvenio tconvenio, RedirectAttributes attr) {
		Long id = tconvenio.getId();
		tservice.salvar(tconvenio);
		attr.addFlashAttribute("success", "Tipo de Convenio editado com sucesso");
		return "redirect:/tconvenios/editar/" + id;
	}

	@GetMapping("/excluir/{id}/{id2}")
	public String excluir(@PathVariable("id") Long id, @PathVariable("id2") Long id2,RedirectAttributes attr) {
		tservice.excluir(id);
		attr.addFlashAttribute("success", "Tipo de Convenio excluido com sucesso");

		return "redirect:/tconvenios/listar/" + id2;
	}

	@ModelAttribute("convenios")
	public List<Convenio> listConvenio() {
		return service.findAll();
	}
}