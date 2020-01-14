package br.com.sismed.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.sismed.domain.Login;
import br.com.sismed.service.LoginService;

@Controller
public class HomeController {
	
	@Autowired
	LoginService service;
	
	// abrir pagina home
		@GetMapping({"/", "/home"})
		public String home(@AuthenticationPrincipal User user, ModelMap model) {
			Login l = service.BuscarPorCPF(user.getUsername());
			/* \\S faz o match com caracteres que não sejam espaços e o + serve para ir pegando os caracteres até que a condição não seja mais satisfeita. 
			 \\p{L}+ ou \\p{IsLatin}+ também funcionam */
			String pattern = "\\S+";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(l.getFuncionario_id().getNome());
			
			if (m.find()) {
		         // escreva o grupo encontrado
				model.addAttribute("usuario",  m.group(0));
		         
		      } else {
		         // mensagem de erro
		    	  model.addAttribute("usuario",  l.getFuncionario_id().getNome());
		      }
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
			return "login/primeiroAcesso";
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
				retorno = "login/cadastrarSenha";
				
			}
		}catch(NullPointerException e) {
			model.addAttribute("alerta", "erro");
			model.addAttribute("titulo", "CPF não é cadastrado no sistema");
			model.addAttribute("texto", "Não foi encontrado nenhum dado com o CPF informado");
			model.addAttribute("subtexto", "Utilize um CPF valido ou entre em contato.");
			
			retorno = "login/primeiroAcesso";
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
				return "login/cadastrarSenha";
			}
		
			
			service.CadastrarSenha(id, s1);
			model.addAttribute("alerta", "sucesso");
			model.addAttribute("titulo", "Sua senha foi cadastrada com sucesso");
			model.addAttribute("texto", "Você agora possui acesso ao nosso sistema");
			model.addAttribute("subtexto", "Utilize o seu CPF e a sua nova senha para realizar o login");
			return "login";
		}
		
		//abre a pagina para inserir email e cpf
		@GetMapping("/redefinir/senha")
		public String pedidoRedefirSenha() {
			return "usuario/pedido-recuperar-senha";
		}
		
		//recupera os dados para a verificação
		@GetMapping("/recuperar/senha")
		public String redefinirSenha(String email, String cpf, ModelMap model) throws MessagingException {
			//chamando o metodo no controller 
			service.pedidoRedefinirSenha(cpf, email);
			Login l = service.buscarPorCpfEAtivo(cpf) .orElseThrow(() -> new UsernameNotFoundException("CPF " + cpf + " não possui mais acesso ao nosso sistema."));;
			Long funcionario = l.getFuncionario_id().getId();
			Long perfil = l.getPerfis().getId();
			System.out.println("Aqui");
			model.addAttribute("success", "Em instantes, você receberá um email para redefinir a sua senha" );
			
			model.addAttribute("login", new Login(cpf));
			model.addAttribute("funcionario", funcionario);
			model.addAttribute("perfil", perfil);
			
			return "usuario/recuperar-senha";
		}
		
		//salva a senha
		@PostMapping("/nova/senha")
		public String novaSenha(Login login, ModelMap model) {
			Login l = service.BuscarPorCPF(login.getCpf());
			
			if(!login.getCodigoVerificador().equals(l.getCodigoVerificador())) {
				model.addAttribute("fail", "codigo verificador não confere" );
				return "usuario/recuperar-senha";
			}
			
		
			l.setCodigoVerificador(null);
			service.alterarSenha(l, login.getSenha());
			
			
			model.addAttribute("alerta", "sucesso" );
			model.addAttribute("titulo", "Senha redefinida" );
			model.addAttribute("texto", "Você já pode realizar o login" );
			return "login";
		}
		
		/*@ModelAttribute("usuario")
		public String usuarioLogado(@AuthenticationPrincipal User user, ModelMap model) {
			Login l = service.BuscarPorCPF(user.getUsername());
			String pattern = "\\S+";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(l.getFuncionario_id().getNome());
			if (m.find()) {
		         // escreva o grupo encontrado
		         System.out.println("Olá, " + m.group(0) );
		      } else {
		         // mensagem de erro
		         System.out.println("Você não tem mais de um nome?");
		      }
			return l.getFuncionario_id().getNome();
		}*/
}
