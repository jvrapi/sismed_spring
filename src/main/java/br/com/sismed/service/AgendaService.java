package br.com.sismed.service;

import java.util.List;

import br.com.sismed.domain.Agenda;

public interface AgendaService {

	void salvar(Agenda agenda);
	void editar(Agenda agenda);
	void excluir(Long id);
	Agenda buscarPorId(Long id);
	
	List<Agenda> BuscarTodos();
}
