package br.com.sismed.service;

import java.util.List;

import br.com.sismed.domain.Paciente;
import br.com.sismed.domain.RegistroClinico;

public interface PacienteService {
	
	void salvar(Paciente paciente);
	void editar(Paciente paciente);
	void excluir(Long id);
	
	Paciente buscarporId(Long id);
	
	List<Paciente> buscarTodos();
	
	List<Paciente> ListarRegPacienteAgen(String dado);
	List<Paciente> ListarRegPaciente(String dado);
	List<Paciente> PesquisarCPF(String dado);
	List<Paciente> PesquisarTelefone(String dado);
}
