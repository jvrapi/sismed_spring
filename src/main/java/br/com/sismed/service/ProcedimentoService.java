package br.com.sismed.service;

import java.util.List;

import br.com.sismed.domain.Procedimento;

public interface ProcedimentoService {

	void salvar(Procedimento procedimento);
	
	void editar(Procedimento procedimento);
	
	void deletar(Long id);
	
	Procedimento findById(Long id);
	
	List<Procedimento> findAll();
}
