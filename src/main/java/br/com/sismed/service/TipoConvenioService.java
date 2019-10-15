package br.com.sismed.service;

import java.util.List;

import br.com.sismed.domain.TipoConvenio;

public interface TipoConvenioService {

	void salvar(TipoConvenio tconvenio);
	void editar(TipoConvenio tconvenio);
	void excluir(Long id);
	
	
	TipoConvenio findById(Long id);
	
	List<TipoConvenio> findAll();
}
