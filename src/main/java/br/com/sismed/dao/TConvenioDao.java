package br.com.sismed.dao;

import java.util.List;


import br.com.sismed.domain.TConvenio;

public interface TConvenioDao {

void save(TConvenio tconvenio);
	
	void update(TConvenio tconvenio);
	
	void delete(Long id);
	
	TConvenio findById(Long id);
	
	List<TConvenio> findAll();
	
	List<TConvenio> ListarTipoConvenio(Long id);
	
}
