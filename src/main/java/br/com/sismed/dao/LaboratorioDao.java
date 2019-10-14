package br.com.sismed.dao;

import java.util.List;

import br.com.sismed.domain.Laboratorio;

public interface LaboratorioDao {

	void save (Laboratorio laboratorio);
	void update (Laboratorio laboratorio);
	void delete (Long id);
	
	Laboratorio findById (Long id);
	
	List<Laboratorio> findAll();

}
