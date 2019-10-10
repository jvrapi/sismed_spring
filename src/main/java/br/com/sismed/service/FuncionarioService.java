package br.com.sismed.service;

import java.util.List;

import br.com.sismed.domain.Funcionario;

public interface FuncionarioService {
	
	void salvar(Funcionario funcionario);
	void editar(Funcionario funcionario);
	void excluir(Long id);
	
	Funcionario buscarporId(Long id);
	
	List<Funcionario> buscarTodos();

}
