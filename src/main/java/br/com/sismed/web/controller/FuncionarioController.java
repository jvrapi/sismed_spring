package br.com.sismed.web.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sismed.domain.FuncTConv;
import br.com.sismed.domain.Funcionario;
import br.com.sismed.domain.LabelValue;
import br.com.sismed.domain.Log;
import br.com.sismed.domain.Login;
import br.com.sismed.domain.Perfil;
import br.com.sismed.domain.TConvenio;
import br.com.sismed.service.ConvenioService;
import br.com.sismed.service.FuncTConvService;
import br.com.sismed.service.FuncionarioService;
import br.com.sismed.service.LogService;
import br.com.sismed.service.LoginService;
import br.com.sismed.service.TConvenioService;
import groovyjarjarpicocli.CommandLine.Model;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	@Autowired
	private ConvenioService cService;

	@Autowired
	private LoginService lservice;

	@Autowired
	private TConvenioService tcService;

	@Autowired
	private FuncTConvService ftcService;

	@Autowired
	private LogService logservice;

	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		PageRequest pagerequest = PageRequest.of(page - 1, 2, Sort.by("id").ascending());
		Page<Funcionario> funcionario = service.buscarTodos(pagerequest);
		model.addAttribute("funcionario", funcionario);
		int lastPage = funcionario.getTotalPages();

		if (lastPage == 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (lastPage == 2) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, lastPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page == 2 && lastPage == 3) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, page + 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page == 1 || page == 2) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, page + 2).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page > 2 && page < lastPage - 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(page - 2, page + 2).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page == lastPage - 1) {
			List<Integer> pageNumbers = IntStream.rangeClosed(page - 2, lastPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} else if (page == lastPage) {
			List<Integer> pageNumbers = IntStream.rangeClosed(lastPage - 2, lastPage).boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "/funcionario/lista";
	}

	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}

	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr, @RequestParam("password") String senha) {
		try {
			service.salvar(funcionario);
			Login login = new Login();
			String cpf = funcionario.getCpf();
			login.setFuncionario_id(funcionario);
			login.setCpf(cpf);
			login.setSenha(new BCryptPasswordEncoder().encode(senha));

			Integer crm = funcionario.getCrm();
			Perfil perfil = new Perfil();

			if (crm == null) {

				perfil.setId(2L);
				login.setPerfis(perfil);
			} else {
				perfil.setId(1L);
				login.setPerfis(perfil);
			}

			lservice.salvar(login);
			attr.addFlashAttribute("success", "Funcionário(a) cadastrado(a) com sucesso");
		} catch (DataIntegrityViolationException ex) {
			attr.addFlashAttribute("fail", "Cadastro não realizado, CPF já existente");
		}

		return "redirect:/funcionario/listar";
	}

	@RequestMapping(value = "/funcionario", method = RequestMethod.POST)
	public String contactSubmit(@ModelAttribute Funcionario person, BindingResult bindingResult, Model model) {

		return "result";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model,
			@ModelAttribute("FuncTConv") FuncTConv functconv) {
		model.addAttribute("funcionario", service.buscarporId(id));
		model.addAttribute("convenios", cService.BuscarConvFunc(id));
		model.addAttribute("allconvenios", cService.findAll());
		return "/funcionario/editar";
	}

	@GetMapping("/buscar/{id}")
	@ResponseBody
	public List<LabelValue> buscar(@PathVariable("id") Integer id,
			@RequestParam(value = "term", required = false, defaultValue = "") String term) {
		List<LabelValue> suggeestions = new ArrayList<LabelValue>();

		if (id == 1) {
			Funcionario allFuncionario = service.ListarFuncionarioId(term);

			LabelValue lv = new LabelValue();
			lv.setLabel(allFuncionario.getNome());
			lv.setValue(allFuncionario.getId());
			suggeestions.add(lv);
		}

		else if (id == 2) {
			List<Funcionario> allFuncionario = service.ListarFuncionarioNome(term);
			for (Funcionario funcionario : allFuncionario) {
				LabelValue lv = new LabelValue();
				lv.setLabel(funcionario.getNome());
				lv.setValue(funcionario.getId());
				suggeestions.add(lv);
			}
		}

		else if (id == 3) {
			List<Funcionario> allFuncionario = service.ListarFuncionarioCPF(term);
			for (Funcionario funcionario : allFuncionario) {
				LabelValue lv = new LabelValue();
				lv.setLabel(funcionario.getNome());
				lv.setValue(funcionario.getId());
				suggeestions.add(lv);
			}
		}

		else if (id == 4) {
			List<Funcionario> allFuncionario = service.ListarFuncionarioCelular(term);
			for (Funcionario funcionario : allFuncionario) {
				LabelValue lv = new LabelValue();
				lv.setLabel(funcionario.getNome());
				lv.setValue(funcionario.getId());
				suggeestions.add(lv);
			}
		}

		else if (id == 5) {
			List<Funcionario> allFuncionario = service.ListarFuncionarioCRM(term);
			for (Funcionario funcionario : allFuncionario) {
				LabelValue lv = new LabelValue();
				lv.setLabel(funcionario.getNome());
				lv.setValue(funcionario.getId());
				suggeestions.add(lv);
			}
		}

		else if (id == 6) {
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
	public String editar(@Valid Funcionario funcionario, RedirectAttributes attr, @AuthenticationPrincipal User user) {

		Funcionario f = service.buscarporId(funcionario.getId());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if (!funcionario.getData_inicio().isEqual(f.getData_inicio())) {
			// se a data de inicio editada for diferente da do banco, gera uma linha na
			// tabela de log
			Login login = lservice.BuscarPorCPF(user.getUsername());
			Log l = new Log();
			l.setData(LocalDate.now());
			l.setFuncionario_id(login.getFuncionario_id());
			l.setHora(LocalTime.now());
			
			l.setDescricao("EDIÇÃO DE DATA DE CONTRATAÇÃO: NOME DO FUNCIONARIO: " + f.getNome() + ". DE "
					+ f.getData_inicio().format(formatter) + " PARA " + funcionario.getData_inicio().format(formatter));
			logservice.salvar(l);
		}
		
		if( f.getData_termino() != null && !funcionario.getData_termino().isEqual(f.getData_termino())) {
			// se a data de termino editada for diferente da do banco, gera uma linha na
			// tabela de log
			Login login = lservice.BuscarPorCPF(user.getUsername());
			Log l = new Log();
			l.setData(LocalDate.now());
			l.setFuncionario_id(login.getFuncionario_id());
			l.setHora(LocalTime.now());
			
			l.setDescricao("EDIÇÃO DE DATA DE TERMINO: NOME DO FUNCIONARIO: " + f.getNome() + ". DE "
					+ f.getData_termino().format(formatter) + " PARA " + funcionario.getData_termino().format(formatter));
			logservice.salvar(l);
		}
			
		attr.addFlashAttribute("success", "Funcionario(a) alterado(a) com sucesso");
		service.salvar(funcionario);
		return "redirect:/funcionario/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, @AuthenticationPrincipal User user) {
		Funcionario f = service.buscarporId(id);
		Login login = lservice.BuscarPorCPF(user.getUsername());
		Log l = new Log();
		l.setData(LocalDate.now());
		l.setFuncionario_id(login.getFuncionario_id());
		l.setHora(LocalTime.now());
		l.setDescricao("EXCLUSÃO DO FUNCIONARIO " + f.getNome());
		logservice.salvar(l);
		service.excluir(id);
		model.addAttribute("success", "Funcionario(a) excluído(a) com sucesso");
		return "redirect:/funcionario/listar";
	}

	@PostMapping("/trocarSenha")
	public String editarSenha(@RequestParam("senha1") String s1, @RequestParam("senha2") String s2,
			@RequestParam("senha3") String s3, @RequestParam("funcionario_id") Long funcionario_id,
			@AuthenticationPrincipal User user, RedirectAttributes attr) {

		if (!s1.equals(s2)) {
			attr.addFlashAttribute("fail", "Senhas não conferem, tente novamente");
			return "redirect:/funcionario/editar/" + funcionario_id;
		}

		Login l = lservice.BuscarPorCPF(user.getUsername());

		if (!LoginService.isSenhaCorreta(s3, l.getSenha())) {
			attr.addFlashAttribute("fail", "Senha atual não confere, tente novamente");

			return "redirect:/funcionario/editar/" + funcionario_id;
		}

		lservice.alterarSenha(l, s1);
		attr.addFlashAttribute("success", "Senha alterado com sucesso!");
		return "redirect:/funcionario/editar/" + funcionario_id;
	}

	@GetMapping("/convenio/{id}/{funcId}")
	public @ResponseBody List<TConvenio> listTipoConvenio(@PathVariable("id") Long id,
			@PathVariable("funcId") Long funcId) {
		return tcService.BuscarTConvenioFunc(id, funcId);
	}

	@GetMapping("/allconvenios/{id}/{funcId}")
	public @ResponseBody List<TConvenio> listAllTipoConvenio(@PathVariable("id") Long id,
			@PathVariable("funcId") Long funcId) {
		return tcService.ListaComboBoxFunc(id, funcId);
	}

	@PostMapping("/salvarTConv/{funcId}")
	public String salvarTConv(Funcionario func, @PathVariable("funcId") Long funcId,
			@RequestParam("tconvenio") List<TConvenio> tconvenio) {
		List<FuncTConv> functconv = new ArrayList<FuncTConv>();
		for (TConvenio tconvenios : tconvenio) {
			FuncTConv ftc = new FuncTConv();
			ftc.setFuncionario(func);
			ftc.setTconvenio(tconvenios);
			functconv.add(ftc);
		}
		ftcService.salvarList(functconv);
		return "redirect:/funcionario/editar/" + funcId;
	}

	@PostMapping("exlcuirTConv/{funcId}")
	public String excluirTConvenio(Funcionario func, @PathVariable("funcId") Long funcId,
			@RequestParam("tconvenio") List<TConvenio> tconvenio) {
		for (TConvenio tconvenios : tconvenio) {
			Long id = tconvenios.getId();
			ftcService.deleteTConvFunc(id, funcId);
		}
		return "redirect:/funcionario/editar/" + funcId;
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
