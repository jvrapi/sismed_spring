package br.com.sismed.service;

import java.util.List;

import br.com.sismed.domain.Exame;

public interface ExameService {
	
	void salvar(Exame exame);
	void editar(Exame exame);
	void excluir(Long id);
	
	Exame buscarporId(Long id);
	
	List<Exame> buscarTodos();
	
	
}
