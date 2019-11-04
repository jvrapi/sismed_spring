package br.com.sismed.dao;

import java.util.List;

import br.com.sismed.domain.Convenio;

public interface ConvenioDao {

	void save(Convenio convenio);
	
	void update(Convenio convenio);
	
	void delete(Long id);
	
	Convenio findById(Long id);
	
	List<Convenio> findAll();
	
	List<Convenio> ListarConvenio();
	
	List<Convenio> ListaComboBox(Long id);
	
}
