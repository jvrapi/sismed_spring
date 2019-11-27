package br.com.sismed.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.sismed.domain.Funcionario;

@Service
public interface FuncionarioService {
	
	void salvar(Funcionario funcionario);
	void excluir(Long id);
	
	Funcionario buscarporId(Long id);
	
	Page<Funcionario> buscarTodos(Pageable pageable);
	List<Funcionario> findAll();
	
	List<Funcionario> ListarFuncionarioNome(String dado);
	
	Funcionario ListarFuncionarioId(String dado);
}
