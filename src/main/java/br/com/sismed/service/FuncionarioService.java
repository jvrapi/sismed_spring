package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import br.com.sismed.domain.Funcionario;
import br.com.sismed.repository.FuncionarioRepository;

@Repository
@Transactional(readOnly = false)
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository fRepository;

	
	
	
	public void salvar(Funcionario funcionario) {
		fRepository.save(funcionario);
	}

	
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
	
	
	public List<Funcionario> ListarFuncionarioNome(String dado) {
		return fRepository.ListarFuncionarioNome(dado);
	}
	
	public Funcionario ListarFuncionarioId(String dado) {
		return fRepository.ListarFuncionarioId(dado);
	}

}
