package br.com.sismed.service;

import java.util.Optional;

import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.sismed.domain.Login;
import br.com.sismed.repository.LoginRepository;

@Service
public class LoginService implements UserDetailsService{

	@Autowired
	LoginRepository repository;
	
	@Autowired
	EmailService emailService;
	
	@Transactional(readOnly = true)
	public Login BuscarPorCPF(String cpf){
		return repository.findByCpf(cpf);
	}
	
	@Transactional(readOnly = false)
	public void salvar(Login login) {
		repository.save(login);
	}
	
	@Override @Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login login = BuscarPorCPF(username);
		
		return new User(
				login.getCpf(),
				login.getSenha(),
				AuthorityUtils.createAuthorityList(login.getPerfis().getDesc())
				
			);
		}

	public static boolean isSenhaCorreta(String senhaDigitada, String senhaArmazenada) {
		
		return new BCryptPasswordEncoder().matches(senhaDigitada, senhaArmazenada) ;
	}

	@Transactional(readOnly = false)
	public void alterarSenha(Login login, String senha) {
		login.setSenha(new BCryptPasswordEncoder().encode(senha));
		repository.save(login);
	}

	@Transactional(readOnly = false)
	public void CadastrarSenha(Long id, String s1) {
		Login l = repository.getOne(id);
		l.setSenha(new BCryptPasswordEncoder().encode(s1));
		repository.save(l);
		
	}
	
	@Transactional(readOnly = true)
	public Optional<Login> buscarPorCpfEAtivo(String cpf) {
		return repository.findByCpfEAtivo(cpf);
	}

	@Transactional(readOnly = false)
	public void pedidoRedefinirSenha(String cpf, String email) throws MessagingException {
		/*busca pelo login a partir do cpf e testa se o mesmo ainda possui acesso a o sistema;
		o funcionario só tera acesso a o sistema caso a data de termino dele seja nula. Se a data de termino dele é nula, isso indica que o mesmo ainda possui vinculo ao SISMED;
		 Caso não encontre nenhum, ele lançara uma excessão dizendo que o funcionario não possui acesso ao sistema*/
		Login login = buscarPorCpfEAtivo(cpf)
				.orElseThrow(() -> new UsernameNotFoundException("CPF " + cpf + " não possui mais acesso ao nosso sistema."));;
		
		//gera um codgio verificador aleatorio com 6 digitos
		String verificador  = RandomStringUtils.randomAlphanumeric(6);
		
		//seta o codigo verificador na tabela para uma verificação na hora em que o usuario informar o codigo
		login.setCodigoVerificador(verificador);
		// chama o metodo responsavel por enviar o email informado em tela, junto com o codigo verificador
		emailService.enviarPedidoRedefinicaoSenha(email, verificador);
	}

	@Transactional(readOnly = true)
	public Login buscarPorId(Long id) {
		return repository.getOne(id);
	}

	

	
}
