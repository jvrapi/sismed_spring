package br.com.sismed.service;

import java.util.List;

import br.com.sismed.domain.Paciente;

public interface PacienteService {
	
	void salvar(Paciente paciente);
	void editar(Paciente paciente);
	void excluir(Long id);
	
	Paciente buscarporId(Long id);
	
	List<Paciente> buscarTodos();

}
