package br.com.sismed.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sismed.domain.Convenio;
import br.com.sismed.domain.LabelValue;
import br.com.sismed.domain.Login;
import br.com.sismed.domain.TConvenio;

import br.com.sismed.service.ConvenioService;
import br.com.sismed.service.LoginService;
import br.com.sismed.service.TConvenioService;




@Controller
@RequestMapping("/convenios") // primeira parte do href 
public class ConvenioController {
	
	@Autowired	
	private ConvenioService service;
	
	@Autowired
	private TConvenioService tservice;
	
	@Autowired
	private LoginService lservice;
	
	
		@GetMapping("/listar") // segunda parte do href
		public String listar(ModelMap model, @RequestParam(value = "page", required=false, defaultValue="1") int page) {
			PageRequest pagerequest = PageRequest.of(page-1, 3, Sort.by("nome").ascending());
			Page<Convenio> convenio = service.BuscarTodos(pagerequest);
			
			model.addAttribute("convenio", convenio);
			int totalPages = convenio.getTotalPages();
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
			return "convenio/lista";
		}
		
		@GetMapping("/buscar/{id}")
		@ResponseBody
		public List<LabelValue> listar(@PathVariable("id") Integer id, @RequestParam (value="term", required=false, defaultValue="") String term) {
			List<LabelValue> suggeestions = new ArrayList<LabelValue>();
			if(id == 1) {
				List<Convenio> allConvenios = service.ListarPorNome(term);
				for (Convenio convenio : allConvenios) {
					LabelValue lv = new LabelValue();
					lv.setLabel(convenio.getNome());
					lv.setValue(convenio.getId());
					suggeestions.add(lv);
				}
			}
			else if(id == 2) {
				List<Convenio> allConvenios = service.ListarPorCNPJ(term);
				for (Convenio convenio: allConvenios) {
					LabelValue lv = new LabelValue();
					lv.setLabel(convenio.getNome());
					lv.setValue(convenio.getId());
					suggeestions.add(lv);
				}
			}
			else if(id == 3) {
				List<Convenio> allConvenios = service.ListarPorANS(term);
				for (Convenio convenio: allConvenios) {
					LabelValue lv = new LabelValue();
					lv.setLabel(convenio.getNome());
					lv.setValue(convenio.getId());
					suggeestions.add(lv);
				}
			}
			
			return suggeestions;	
		}
		
		@GetMapping("/cadastrar") // segunda parte do href
		public String cadastrar (Convenio convenio) {
			return "convenio/cadastro"; // retorna o caminho do arquivo
		
		}
		
		
		@PostMapping("/salvar")
		public String salvar( Convenio convenio,  RedirectAttributes attr) {
			
			service.salvar(convenio);
			attr.addFlashAttribute("success","Convenio cadastrado com sucesso");
			return  "redirect:/convenios/listar";
		}
		
		@GetMapping("/editar/{id}") //ID do convenio que vem pela URL
		public String preEditar(@PathVariable("id") Long id, ModelMap model) {
			/* @PathVariable = recupera da url o id enviado pela URL como um path
			 	o objeto model serve para enviar para a pagina de cadastro o convenio como uma variavel*/ 
			model.addAttribute("convenio", service.buscarPorId(id));
			return "convenio/editar";
		}
		
		@PostMapping("/editar")
		public String editar(@Valid Convenio convenio,  BindingResult result ,RedirectAttributes attr) {
			Long id = convenio.getId();
			
			if(result.hasErrors()) {
				return "/convenio/editar/" + id;
			}
			
			service.salvar(convenio);
			attr.addFlashAttribute("success","Convenio alterado com sucesso");
			return "redirect:/convenios/editar/" + id;
		}
		
		
		@GetMapping("/excluir/{id}")
		public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
			String retorno = "";
			try {
				service.excluir(id);
				attr.addFlashAttribute("success", "Convenio excluido com sucesso");
				retorno = "redirect:/convenios/listar";
			}
			catch (DataIntegrityViolationException error) {
				attr.addFlashAttribute("fail", "N??o foi poss??vel excluir");
				retorno = "redirect:/convenios/listar";
			}	
			return retorno;
		}
		
		@ModelAttribute("TiposConvenios")
		public List<TConvenio> listTConvenio() {
			return tservice.findAll();
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
