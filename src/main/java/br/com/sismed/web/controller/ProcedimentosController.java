package br.com.sismed.web.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.com.sismed.domain.Convenio;
import br.com.sismed.domain.Procedimento;
import br.com.sismed.domain.TConvenio;
import br.com.sismed.service.ConvenioService;
import br.com.sismed.service.ProcedimentoService;
import br.com.sismed.service.TConvenioService;

@Controller
@RequestMapping("/procedimentos")
public class ProcedimentosController {
	
	@Autowired
	private ProcedimentoService service;
	
	@Autowired
	private ConvenioService ConvenioService;
	
	@GetMapping("/cadastrar")
	public String Cadastrar(Procedimento procedimento) {
		
		return "/procedimentos/cadastro";
	}
	
	@GetMapping("/listar/{id}")
	public String listar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("procedimento", service.ListarProcedimento(id));
		System.out.println(id);
		return "/procedimentos/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Procedimento procedimento, RedirectAttributes attr) {
		service.salvar(procedimento);
		attr.addFlashAttribute("success","Procedimento cadastrado com sucesso");
		return  "redirect:/procedimentos/cadastrar";
	}
	
	@GetMapping("/editar/{id}") //ID do convenio que vem pela URL
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		/* @PathVariable = recupera da url o id enviado pela URL como um path
		 	o objeto model serve para enviar para a pagina de cadastro o convenio como uma variavel*/ 
		model.addAttribute("procedimento", service.BuscarPorId(id));
		return "/procedimentos/editar";
	}
	
	@PostMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id,Procedimento procedimentos, RedirectAttributes attr) {
		service.editar(procedimentos);
		attr.addFlashAttribute("success","Procedimento editado com sucesso");
		return "redirect:/procedimentos/listar/" + id;
	}
	
	
	@GetMapping("/excluir/{id}/{id2}")
	public String excluir(@PathVariable("id") Long id, @PathVariable("id2") Long id2,RedirectAttributes attr) {
		service.excluir(id);
		attr.addFlashAttribute("success", "Procedimento excluido com sucesso");
		return "redirect:/procedimentos/listar/"+ id2;
	}
	
	@ModelAttribute("convenios")
	public List<Convenio> listConvenio() {
		return ConvenioService.BuscarTodos();
	}
}
