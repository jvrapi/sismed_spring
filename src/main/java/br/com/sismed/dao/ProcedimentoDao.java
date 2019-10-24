package br.com.sismed.dao;

import java.util.List;

import br.com.sismed.domain.Procedimento;

public interface ProcedimentoDao {

	void save(Procedimento procedimento);
	void update(Procedimento procedimento);
	void delete(Long id);
	Procedimento findById(Long id);
	List<Procedimento> findAll();
	List<Procedimento> ListarProcedimento(Long id);
}
