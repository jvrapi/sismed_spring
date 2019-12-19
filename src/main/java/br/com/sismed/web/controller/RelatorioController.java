package br.com.sismed.web.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sismed.domain.*;
import br.com.sismed.service.*;


@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

	@Autowired
	private CustosService service;
	
	@Autowired 
	private PacienteService pservice;
	
	@Autowired
	private ConvenioService cservice;
	
	@Autowired
	private FuncionarioService fservice;
	
	@GetMapping("/listar")
	public String listar() {
		return "custos/lista";
	}
	
	@GetMapping("/buscar/{id}")
	@ResponseBody
	public List<LabelValue> listar(@PathVariable("id") Integer id, @RequestParam (value="term", required=false, defaultValue="") String term) {
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();
		if(id == 1) {
			List<Paciente> allPacientes = pservice.ListarPacNome(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue(paciente.getId() );
				suggeestions.add(lv);
			}
		}
		else if(id == 2) {
			List<Convenio> allConvenios = cservice.ListarPorNome(term);
			for (Convenio convenio : allConvenios) {
				LabelValue lv = new LabelValue();
				lv.setLabel(convenio.getNome());
				lv.setValue(convenio.getId());
				suggeestions.add(lv);
			}
		}
		
		else if(id == 3) {
			List<Funcionario> allFunc = fservice.ListarFuncionarioNome(term);
			for (Funcionario f: allFunc) {
				LabelValue lv = new LabelValue();
				lv.setLabel(f.getNome());
				lv.setValue(f.getId());
				suggeestions.add(lv);
			}
		}
		
		
		return suggeestions;
		
	}
	
	@PostMapping("/gerar")
	public String gerarRelatorio(@RequestParam("paciente") Long paciente, @RequestParam("convenio") Long convenio, @RequestParam("dataInicio") String dataInicio, 
			@RequestParam("dataFim") String dataFim, @RequestParam("funcionario") Long funcionario ,RedirectAttributes attr, ModelMap model) {
		
		
			if(convenio == null && dataInicio == "" && funcionario == null) {
				//so por paciente
				model.addAttribute("resultado", service.buscarPorPaciente(paciente));
				model.addAttribute("receita", service.buscarPorPaciente(paciente));
				
				
				
			}
			
			else if(paciente == null && dataInicio == "" && funcionario == null) {
				//so por convenio
				model.addAttribute("resultado", service.buscarPorConvenio(convenio));
				model.addAttribute("receita",  service.BuscarReceitaPorConvenio(convenio));
				
				
			}
			
			else if(paciente == null && convenio == null && dataInicio == "") {
				//so por funcionario
				model.addAttribute("resultado", service.buscarPorFuncionario(funcionario));
				model.addAttribute("receita", service.buscarReceitaPorFuncionario(funcionario));
				
				
			}
			
			else if(paciente == null && convenio == null && funcionario == null) {
				//entre um periodo
				model.addAttribute("resultado", service.buscarPorDatas(dataInicio, dataFim));
				
				
				/* TESTE */
				for(Custos c : service.buscarPorDatas(dataInicio, dataFim)) {
					System.out.println(c.getData());
				}
				System.out.println(service.buscarReceitaPorFuncionario(funcionario));
				
			}
			
		return "redirect:/relatorio/listar";
	}
}
