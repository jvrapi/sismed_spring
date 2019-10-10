package br.com.sismed.dao;

import java.util.List;

import br.com.sismed.domain.Paciente;

public interface PacienteDao {

	void save(Paciente paciente);
	void update(Paciente paciente);
	void delete(Long id);
	
	Paciente findById(Long id);
	List<Paciente> findAll();
}
