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

	@GetMapping("/listar/{id}") // segunda parte do href
	public String listar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("tconvenio", tservice.ListarTipoConvenio(id));
		return "/tconvenio/lista"; // retorna o caminho do arquivo
	}

	@GetMapping("/cadastrar/{id}") // segunda parte do href
	public String cadastrar(@PathVariable("id") Long id, ModelMap model, @ModelAttribute("tconvenio") TConvenio tconvenio) {
		model.addAttribute("convenio", service.buscarPorId(id));
		return "/tconvenio/cadastro"; // retorna o caminho do arquivo

	}

	@PostMapping("/salvar")
	public String salvar(TConvenio tconvenio, RedirectAttributes attr) {
		tservice.salvar(tconvenio);
		attr.addFlashAttribute("success", "Tipo de Convenio cadastrado com sucesso");
		return "redirect:/tconvenios/cadastrar";
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

	@PostMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id,TConvenio tconvenio, RedirectAttributes attr) {
		tservice.editar(tconvenio);
		attr.addFlashAttribute("success", "Tipo de Convenio editado com sucesso");
		return "redirect:/tconvenios/listar/" + id;
	}

	@GetMapping("/excluir/{id}/{id2}")
	public String excluir(@PathVariable("id") Long id, @PathVariable("id2") Long id2,RedirectAttributes attr) {
		tservice.excluir(id);
		attr.addFlashAttribute("success", "Tipo de Convenio excluido com sucesso");

		return "redirect:/tconvenios/listar/" + id2;
	}

	@ModelAttribute("convenios")
	public List<Convenio> listConvenio() {
		return service.BuscarTodos();
	}
}