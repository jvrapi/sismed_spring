package br.com.sismed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.domain.Funcionario;
import br.com.sismed.domain.Login;
import br.com.sismed.repository.FuncionarioRepository;
import br.com.sismed.repository.LogRepository;

@Service
public class LogService {
	
	@Autowired
	private FuncionarioRepository fRepository;
	
	@Autowired
	private LogRepository lrepository;
	
	@Transactional(readOnly = false)
	public void salvar(Funcionario funcionario) {
		fRepository.save(funcionario);
	}
	
	@Transactional(readOnly = true)
	public Login BuscarPorCPF(String cpf){
		return lrepository.findByCpf(cpf);
	}
	
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login login = BuscarPorCPF(username);
		
		return new User(
				login.getCpf(),
				login.getSenha(),
				AuthorityUtils.createAuthorityList(login.getPerfis().getDesc())
				
			);
		}
}
