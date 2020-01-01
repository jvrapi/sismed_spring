package br.com.sismed.web.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
	public List<LabelValue> listar(@PathVariable("id") Integer id,
			@RequestParam(value = "term", required = false, defaultValue = "") String term) {
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();
		if (id == 1) {
			List<Paciente> allPacientes = pservice.ListarPacNome(term);
			for (Paciente paciente : allPacientes) {
				LabelValue lv = new LabelValue();
				lv.setLabel(paciente.getNome());
				lv.setValue(paciente.getId());
				suggeestions.add(lv);
			}
		} else if (id == 2) {
			List<Convenio> allConvenios = cservice.ListarPorNome(term);
			for (Convenio convenio : allConvenios) {
				LabelValue lv = new LabelValue();
				lv.setLabel(convenio.getNome());
				lv.setValue(convenio.getId());
				suggeestions.add(lv);
			}
		}

		else if (id == 3) {
			List<Funcionario> allFunc = fservice.ListarFuncionarioNome(term);
			for (Funcionario f : allFunc) {
				LabelValue lv = new LabelValue();
				lv.setLabel(f.getNome());
				lv.setValue(f.getId());
				suggeestions.add(lv);
			}
		}

		return suggeestions;

	}

	@PostMapping("/gerar")
	public String gerarRelatorio(@RequestParam("paciente") Long paciente, @RequestParam("convenio") Long convenio,
			@RequestParam("dataInicio") String dataInicio, @RequestParam("dataFim") String dataFim,
			@RequestParam("funcionario") Long funcionario, RedirectAttributes attr, ModelMap model) {
		
		

		if (convenio == null && dataInicio == "" && funcionario == null) {
			// so por paciente
			Paciente p = pservice.buscarporId(paciente);
			model.addAttribute("resultado", service.buscarPorPaciente(paciente));
			model.addAttribute("receita", "O paciente " + p.getNome() + " Gerou uma receita de R$ " + service.buscarReceitaPorPaciente(paciente));
		}

		else if (paciente == null && dataInicio == "" && funcionario == null) {
			// so por convenio
			if(convenio != 0) {
			Convenio c = cservice.buscarPorId(convenio);
			model.addAttribute("resultado", service.buscarPorConvenio(convenio));
			model.addAttribute("receita", "O convênio " + c.getNome() + " Gerou uma receita de R$ " + service.BuscarReceitaPorConvenio(convenio));
			}
			if (convenio == 0) {
				model.addAttribute("resultado", service.buscarTodosConvenios());
				model.addAttribute("receita", "Os convênios geraram uma receita de R$: " + service.receitaTodosConvenios());
				
			}
		}

		else if (paciente == null && convenio == null && dataInicio == "") {
			// so por funcionario
			Funcionario f = fservice.buscarporId(funcionario);
			model.addAttribute("resultado", service.buscarPorFuncionario(funcionario));
			model.addAttribute("receita", "O funcionario " + f.getNome() + " gerou uma receita de R$: " + service.buscarReceitaPorFuncionario(funcionario));
		}

		else if (paciente == null && convenio == null && funcionario == null) {
			// entre um periodo
			LocalDate data1 = LocalDate.parse(dataInicio);
			LocalDate data2 = LocalDate.parse(dataFim);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataInicioFormatada = data1.format(formatter);
			String dataFimFormatada = data2.format(formatter);
			model.addAttribute("resultado", service.buscarPorDatas(dataInicio, dataFim));
			model.addAttribute("receita", "Entre o periodo de " + dataInicioFormatada + " a " + dataFimFormatada + " foi gerada uma receita de R$: " + service.buscarReceitaPorDatas(dataInicio, dataFim));

		} else if (funcionario == null && convenio == null) {
			// Paciente e Periodo
			Paciente p = pservice.buscarporId(paciente);
			LocalDate data1 = LocalDate.parse(dataInicio);
			LocalDate data2 = LocalDate.parse(dataFim);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataInicioFormatada = data1.format(formatter);
			String dataFimFormatada = data2.format(formatter);
			
			model.addAttribute("resultado", service.PacientePeriodo(paciente, dataInicio, dataFim));
			model.addAttribute("receita", "O paciente "+ p.getNome() + " no periodo de "+ dataInicioFormatada + " a " + dataFimFormatada + " gerou uma receita de R$: " +service.ReceitaPacientePeriodo(paciente, dataInicio, dataFim));
		} else if (funcionario == null && paciente == null) {
			// convenio periodo
			LocalDate data1 = LocalDate.parse(dataInicio);
			LocalDate data2 = LocalDate.parse(dataFim);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataInicioFormatada = data1.format(formatter);
			String dataFimFormatada = data2.format(formatter);
			
			if(convenio != 0){
			Convenio c = cservice.buscarPorId(convenio);
			model.addAttribute("resultado", service.ConvenioPeriodo(convenio, dataInicio, dataFim));
			model.addAttribute("receita", "O convenio "+ c.getNome() + 
					" no periodo de " + dataInicioFormatada + " a " + dataFimFormatada  + " gerou uma receita de R$: " + 
					service.ReceitaConvenioPeriodo(convenio, dataInicio, dataFim));
			}
			
			if (convenio == 0) {
				model.addAttribute("resultado", service.TodosConvenioPeriodo(dataInicio, dataFim));
				model.addAttribute("receita", "Entre o periodo de " + dataInicioFormatada + " a " + dataFimFormatada + " os convenios geraram uma receita de R$: " 
				+  service.ReceitaTodosConvenioPeriodo(dataInicio, dataFim));
			}
		} else if (paciente == null && convenio == null) {
			// funcionario e periodo
			
			LocalDate data1 = LocalDate.parse(dataInicio);
			LocalDate data2 = LocalDate.parse(dataFim);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataInicioFormatada = data1.format(formatter);
			String dataFimFormatada = data2.format(formatter);
			
			Funcionario f = fservice.buscarporId(funcionario);
			model.addAttribute("resultado", service.FuncionarioPeriodo(funcionario, dataInicio, dataFim));
			model.addAttribute("receita", "O medico "+ f.getNome() + " no periodo de "+ dataInicioFormatada + " a " + dataFimFormatada + " gerou uma receita de R$: " +service.ReceitaFuncionarioPeriodo(funcionario, dataInicio, dataFim));

		}

		return "custos/lista";
	}

	@ModelAttribute("convenios")
	public List<Convenio> allConvenios() {
		return cservice.findAll();
	}
}