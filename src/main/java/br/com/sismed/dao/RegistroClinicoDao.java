package br.com.sismed.dao;

import java.util.List;

import br.com.sismed.domain.RegistroClinico;

public interface RegistroClinicoDao {

	void save(RegistroClinico registro_clinico);
	void update(RegistroClinico registro_clinico);
	void delete(Long id);
	
	RegistroClinico findById(Long id);
	List<RegistroClinico> findAll();
	
	List<RegistroClinico> ListarRegPaciente(Long id);
	List<RegistroClinico> ListarRegAgenda(Long id);
}
