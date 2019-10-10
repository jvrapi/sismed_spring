package br.com.sismed.service;

import java.util.List;

import br.com.sismed.domain.Laboratorio;

public interface LaboratorioService {
	
	void salvar(Laboratorio laboratorio);
	void editar(Laboratorio laboratorio);
	void excluir(Long id);
	
	Laboratorio buscarporId(Long id);
	
	List<Laboratorio> buscarTodos();

}
