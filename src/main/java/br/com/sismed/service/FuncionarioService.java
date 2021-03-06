package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.domain.Funcionario;

import br.com.sismed.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository fRepository;
	
	@Transactional(readOnly = false)
	public void salvar(Funcionario funcionario) {
		fRepository.save(funcionario);
	}

	@Transactional(readOnly = false)
	public void excluir(Long id) {
		fRepository.deleteById(id);
	}

	
	@Transactional(readOnly = true)
	public Funcionario buscarporId(Long id) {
		return fRepository.getOne(id);
	}

	
	@Transactional(readOnly = true)
	public Page<Funcionario> buscarTodos(Pageable pageable) {
		return fRepository.findAll(pageable);
	}
	
	
	@Transactional(readOnly = true)
	public List<Funcionario> findAll() {
		return fRepository.findAll();
	}
	
	
	@Transactional(readOnly = true)
	public List<Funcionario> ListarFuncionarioNome(String dado) {
		return fRepository.ListarFuncionarioNome(dado);
	}
	
	@Transactional(readOnly = true)
	public Funcionario ListarFuncionarioId(String dado) {
		return fRepository.ListarFuncionarioId(dado);
	}
	
	@Transactional(readOnly = true)
	public List<Funcionario> ListarFuncionarioCPF(String dado) {
		return fRepository.ListarFuncionarioCPF(dado);
	}
	
	@Transactional(readOnly = true)
	public List<Funcionario> ListarFuncionarioCRM(String dado) {
		return fRepository.ListarFuncionarioCRM(dado);
	}
	
	@Transactional(readOnly = true)
	public List<Funcionario> ListarFuncionarioEspecialidade(String dado) {
		return fRepository.ListarFuncionarioEspecialidade(dado);
	}
	
	@Transactional(readOnly = true)
	public List<Funcionario>ListarFuncionarioCelular(String dado){
		return fRepository.ListarFuncionarioCelular(dado);
	}
	
	@Transactional(readOnly = true)
	public List<Funcionario>ListarMedicos(){
		return fRepository.ListarMedicos();
	}
	
	

	
		
		
	}
	
	
	
	



