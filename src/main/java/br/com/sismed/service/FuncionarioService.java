package br.com.sismed.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sismed.domain.Funcionario;

@Service
public interface FuncionarioService {
	
	void salvar(Funcionario funcionario);
	void editar(Funcionario funcionario);
	void excluir(Long id);
	
	Funcionario buscarporId(Long id);
	
	List<Funcionario> buscarTodos();
	
	List<Funcionario> ListarFuncionarioId(String dado);

}
