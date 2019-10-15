package br.com.sismed.dao;

import java.util.List;

import br.com.sismed.domain.TipoConvenio;

public interface TipoConvenioDao {

void save(TipoConvenio tconvenio);
	
	void update(TipoConvenio tconvenio);
	
	void delete(Long id);
	
	TipoConvenio findById(Long id);
	
	List<TipoConvenio> findAll();
}
