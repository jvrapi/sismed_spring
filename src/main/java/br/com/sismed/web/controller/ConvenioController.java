package br.com.sismed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sismed.domain.Convenio;

import br.com.sismed.service.ConvenioService;




@Controller
@RequestMapping("/convenios") // primeira parte do href 
public class ConvenioController {
	
	@Autowired	
	private ConvenioService service;
	
	
	
	
		@GetMapping("/listar") // segunda parte do href
		public String listar(ModelMap model) {
			model.addAttribute("convenios", service.BuscarTodos());
			return "/convenio/lista"; // retorna o caminho do arquivo
		}
		
		@GetMapping("/cadastrar") // segunda parte do href
		public String cadastrar (Convenio convenio) {
			return "/convenio/cadastro"; // retorna o caminho do arquivo
		
		}
		
		
		@PostMapping("/salvar")
		public String salvar(Convenio convenio, RedirectAttributes attr) {
			service.salvar(convenio);
			attr.addFlashAttribute("success","Convenio cadastrado com sucesso");
			return  "redirect:/convenios/cadastrar";
		}
		
		@GetMapping("/editar/{id}") //ID do convenio que vem pela URL
		public String preEditar(@PathVariable("id") Long id, ModelMap model) {
			/* @PathVariable = recupera da url o id enviado pela URL como um path
			 	o objeto model serve para enviar para a pagina de cadastro o convenio como uma variavel*/ 
			model.addAttribute("convenio", service.buscarPorId(id));
			return "/convenio/cadastro";
		}
		
		@PostMapping("/editar")
		public String editar(Convenio convenio, RedirectAttributes attr) {
			service.editar(convenio);
			attr.addFlashAttribute("success","Convenio editado com sucesso");
			return "redirect:/convenios/listar";
		}
		
		
		@GetMapping("/excluir/{id}")
		public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
			service.excluir(id);
			attr.addFlashAttribute("success", "Convenio excluido com sucesso");
			
			return "redirect:/convenios/listar";
		}
}
