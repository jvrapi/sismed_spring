package br.com.sismed.dao;

import java.util.List;

import br.com.sismed.domain.Agenda;

public interface AgendaDao {

	void save(Agenda agenda);
	void update(Agenda agenda);
	void delete(Long id);
	
	Agenda findById(Long id);
	List<Agenda> findAll();
	List<Agenda> ListarAgendamentos();
}
