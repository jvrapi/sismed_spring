package br.com.sismed.dao;

import java.util.List;

import br.com.sismed.domain.Paciente;
import br.com.sismed.domain.RegistroClinico;

public interface PacienteDao {

	void save(Paciente paciente);
	void update(Paciente paciente);
	void delete(Long id);
	
	Paciente findById(Long id);
	List<Paciente> findAll();

	List<Paciente> ListarRegPacienteAgen(String dado);
	List<Paciente> ListarRegPaciente(String dado);
	List<Paciente> PesquisarCPF(String dado);
	List<Paciente> PesquisarTelefone(String dado);
}
