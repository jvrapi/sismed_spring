package br.com.sismed.web.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.sismed.domain.Procedimento;
import br.com.sismed.domain.TConvenio;
import br.com.sismed.service.ConvenioService;
import br.com.sismed.service.ProcedimentoService;


@Controller
@RequestMapping("/procedimentos")
public class ProcedimentosController {
	
	@Autowired
	private ProcedimentoService service;
	
	@Autowired
	private ConvenioService ConvenioService;
	
	@GetMapping("/cadastrar/{id2}")
	public String Cadastrar(@PathVariable("id2") Long id, ModelMap model, Procedimento procedimento) {
		model.addAttribute("convenio", ConvenioService.buscarPorId(id));
		return "/procedimentos/cadastro";
	}
	
	@GetMapping("/listar/{id}")
	public String listar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("procedimento", service.ListarProcedimento(id));
		model.addAttribute("convenio", ConvenioService.buscarPorId(id));
		System.out.println(id);
		return "/procedimentos/lista";
	}
	
	@GetMapping("/buscar/{id}")
	@ResponseBody
	public List<LabelValue> buscar(@PathVariable("id") Long id, @RequestParam (value="term", required=false, defaultValue="") String term) {
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();
		
			List<Procedimento> allProcedimentos = service.ListarPorDescricao(term, id);
			for (Procedimento procedimento : allProcedimentos) {
				LabelValue lv = new LabelValue();
				lv.setLabel(procedimento.getDescricao());
				lv.setValue(procedimento.getId());
				suggeestions.add(lv);
			}
		
		return suggeestions;	
	}
	
	@PostMapping("/salvar")
	public String salvar(Procedimento procedimento, RedirectAttributes attr) {
		Long id = procedimento.getConvenio().getId();
		service.salvar(procedimento);
		attr.addFlashAttribute("success","Procedimento cadastrado com sucesso");
		return  "redirect:/procedimentos/listar/" + id;
	}
	
	@GetMapping("/editar/{id}") //ID do convenio que vem pela URL
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		/* @PathVariable = recupera da url o id enviado pela URL como um path
		 	o objeto model serve para enviar para a pagina de cadastro o convenio como uma variavel*/ 
		model.addAttribute("procedimento", service.BuscarPorId(id));
		return "/procedimentos/editar";
	}
	
	@PostMapping("/editar")
	public String editar(Procedimento procedimentos, RedirectAttributes attr) {
		Long id = procedimentos.getId();
		service.salvar(procedimentos);
		attr.addFlashAttribute("success","Procedimento editado com sucesso");
		return "redirect:/procedimentos/editar/" + id;
	}
	
	
	@GetMapping("/excluir/{id}/{id2}")
	public String excluir(@PathVariable("id") Long id, @PathVariable("id2") Long id2,RedirectAttributes attr) {
		service.excluir(id);
		attr.addFlashAttribute("success", "Procedimento excluido com sucesso");
		return "redirect:/procedimentos/listar/"+ id2;
	}
	
	@ModelAttribute("convenios")
	public List<Convenio> listConvenio() {
		return ConvenioService.findAll();
	}
}
