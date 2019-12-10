package br.com.sismed.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sismed.domain.Login;
import br.com.sismed.service.LoginService;

@Controller
public class HomeController {
	
	@Autowired
	LoginService service;
	
	// abrir pagina home
		@GetMapping({"/", "/home"})
		public String home() {
			return "home";
		}		


		// abrir pagina login
		@GetMapping({"/login"})
		public String login() {
			
			return "login";
		}	
		
		// login invalido
		@GetMapping({"/login-error"})
		public String loginError(ModelMap model) {
			model.addAttribute("alerta", "erro");
			model.addAttribute("titulo", "Crendenciais inválidas!");
			model.addAttribute("texto", "Login ou senha incorretos, tente novamente.");
			model.addAttribute("subtexto", "Acesso permitido apenas para cadastros já ativados.");
			return "login";
		}	
		
		//acesso negado
		@GetMapping({"/acesso-negado"})
		public String acessoNegado(ModelMap model, HttpServletResponse resp) {
			model.addAttribute("status", resp.getStatus()); // codigo do status
			model.addAttribute("error", "Acesso Negado");
			model.addAttribute("message", "Você não tem acesso a está area ou ação");
			
			return "error";
		}
		
		//primeiro acesso de usuario cadastrado
		@GetMapping("/primeiroAcesso")
		public String primeiroAcesso() {
			return "/login/primeiroAcesso";
		}
		
		
		
		@PostMapping("/verificarCPF")
		public String pesquisarUsuario(@RequestParam("inputCPF") String cpf, ModelMap model) {
		System.out.println(cpf);
		String retorno = "";
		Login l = service.BuscarPorCPF(cpf);
		try {
			if(!l.getSenha().isEmpty()) {
				
				model.addAttribute("alerta", "erro");
				model.addAttribute("titulo", "CPF já possui senha");
				model.addAttribute("texto", "CPF informado já possui uma senha cadastrada");
				model.addAttribute("subtexto", "Utilize um CPF que não tenha uma senha, ou entre em contato.");
				retorno = "/login/primeiroAcesso";
			}else{
				model.addAttribute("login", l);
				retorno = "/login/cadastrarSenha";
				
			}
		}catch(NullPointerException e) {
			model.addAttribute("alerta", "erro");
			model.addAttribute("titulo", "CPF não é cadastrado no sistema");
			model.addAttribute("texto", "Não foi encontrado nenhum dado com o CPF informado");
			model.addAttribute("subtexto", "Utilize um CPF valido ou entre em contato.");
			
			retorno = "/login/primeiroAcesso";
		}
		
		
		
		return retorno;
		}
		
		@PostMapping("/cadastrarSenha")
		public String cadastrarSenha(@RequestParam("senha1") String s1, @RequestParam("senha2") String s2, @RequestParam("id") Long id, ModelMap model) {
			if(!s1.equals(s2)) {
				model.addAttribute("alerta", "erro");
				model.addAttribute("titulo", "Senhas não conferem");
				model.addAttribute("texto", "As senhas digitas não são iguais!");
				model.addAttribute("subtexto", "Por favor, insira as senhas novamente");
				return "/login/cadastrarSenha";
			}
		
			
			service.CadastrarSenha(id, s1);
			model.addAttribute("alerta", "sucesso");
			model.addAttribute("titulo", "Sua senha foi cadastrada com sucesso");
			model.addAttribute("texto", "Você agora possui acesso ao nosso sistema");
			model.addAttribute("subtexto", "Utilize o seu CPF e a sua nova senha para realizar o login");
			return "login";
		}
}
