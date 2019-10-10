package br.com.sismed.dao;

import java.util.List;


import br.com.sismed.domain.TipoConvenio;

public interface TipoConvenioDao {

	void save(TipoConvenio convenio);
	
	void update(TipoConvenio convenio);
	
	void delete(Long id);
	
	TipoConvenio findById(Long id);
	
	List<TipoConvenio> findAll();
}
